package config

import com.vanniktech.maven.publish.MavenPublishBaseExtension
import external.CapstoneBuildConfigs
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.bundling.AbstractArchiveTask
import org.gradle.api.tasks.bundling.Jar
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.targets.js.dsl.KotlinJsTargetDsl
import org.jetbrains.kotlin.gradle.targets.js.npm.PackageJson
import java.io.File
import java.io.FileInputStream
import java.util.Properties

val publishProperties by lazy {
    loadProperties(File("publish.properties"))
}

fun Project.configureProjectMeta() {
    group = publishProperties["GROUP"].toString()
    version = publishProperties["VERSION"].toString()
}

fun Project.configurePublishing() {
    plugins.withId("com.vanniktech.maven.publish") {

        extensions.configure<MavenPublishBaseExtension> {

            publishToMavenCentral()

            signAllPublications()

            pom {
                name.set(publishProperties["POM_NAME"].toString())
                description.set(publishProperties["POM_DESCRIPTION"].toString())
                inceptionYear.set("2025")
                url.set(publishProperties["POM_URL"].toString())
                licenses {
                    license {
                        name.set(publishProperties["POM_LICENSE_NAME"].toString())
                        url.set(publishProperties["POM_LICENSE_URL"].toString())
                        distribution.set("repo")
                    }
                }

                developers {
                    developer {
                        id.set(publishProperties["POM_DEVELOPER_ID"].toString())
                        name.set(publishProperties["POM_DEVELOPER_NAME"].toString())
                        url.set(publishProperties["POM_DEVELOPER_URL"].toString())
                        email.set(publishProperties["POM_DEVELOPER_EMAIL"].toString())
                    }
                }

                scm {
                    url.set(publishProperties["POM_SCM_URL"].toString())
                    connection.set(publishProperties["POM_SCM_CONNECTION"].toString())
                    developerConnection.set(publishProperties["POM_SCM_DEV_CONNECTION"].toString())
                }
            }
        }

        // Ensure reproducible artifacts
        tasks.withType<AbstractArchiveTask>().configureEach {
            isPreserveFileTimestamps = false
            isReproducibleFileOrder = true
        }

        // Create platform-specific JVM artifacts with classifiers
        createPlatformSpecificJars()

        // Configure npm publishing tasks
        configureNpmPublishing()
    }
}

/**
 * Creates platform-specific JAR artifacts for JVM native libraries
 * These are published with Maven classifiers so consumers can download only the libraries they need
 *
 * The classifier format matches CapstoneBuildConfigs.getJvmPlatformClassifier()
 *
 * Directory structure:
 *   Build output: library/src/jvmMain/resources/libs/{classifier}/
 *   JAR content:  libs/{classifier}/
 *   Classifier:   {classifier} (e.g., "capstone-macos-x64")
 */
private fun Project.createPlatformSpecificJars() {
    // Get all JVM shared library targets from CapstoneBuildConfigs
    val jvmSharedTargets = CapstoneBuildConfigs.getAllJvmSharedTargets()

    jvmSharedTargets.forEach { targetName ->
        val classifier = CapstoneBuildConfigs.getJvmPlatformClassifier(targetName)

        if (classifier != null) {
            val taskName = "jvmJar${classifier.replace("-", "").replaceFirstChar { it.uppercase() }}"

            // Create a JAR task for each platform
            val jarTask = tasks.register<Jar>(taskName) {
                archiveClassifier.set(classifier)

                // Include native library from resources
                // Path matches CapstoneBuild.kt output directory
                from(File(projectDir, "library/src/jvmMain/resources/libs/$classifier")) {
                    into("libs/$classifier")
                    include("**/*")
                }

                // Optionally include JVM classes if needed (currently disabled)
                // from(tasks.named("compileKotlinJvm"))
            }

            // Register the JAR with Maven publishing
            afterEvaluate {
                extensions.configure<PublishingExtension> {
                    publications {
                        // Create or update the JVM publication to include platform-specific JARs
                        if (findByName("jvm") is MavenPublication) {
                            (findByName("jvm") as MavenPublication).artifact(jarTask)
                        }
                    }
                }
            }
        }
    }
}

/**
 * Configures npm publishing tasks for publishing to npm registry and GitHub Packages
 */
private fun Project.configureNpmPublishing() {
    val jsDistDir = File(layout.buildDirectory.get().asFile, "dist/js/productionLibrary")

    // Task to publish to npm registry
    tasks.register("publishToNpm") {
        group = "publishing"
        description = "Publish Kotlin/JS library to npm registry"

        dependsOn("jsProductionLibraryCompileSync")

        doLast {
            val npmToken = System.getenv("NPM_TOKEN")
            if (npmToken.isNullOrEmpty()) {
                throw IllegalStateException("NPM_TOKEN environment variable is not set. Required for publishing to npm.")
            }

            if (!jsDistDir.exists()) {
                throw IllegalStateException("JS distribution directory does not exist: ${jsDistDir.absolutePath}")
            }

            // Create .npmrc file with authentication
            val npmrcFile = File(jsDistDir, ".npmrc")
            npmrcFile.writeText("//registry.npmjs.org/:_authToken=\${NPM_TOKEN}\n")

            // Publish to npm
            providers.exec {
                workingDir = jsDistDir
                environment("NPM_TOKEN", npmToken)
                commandLine("npm", "publish", "--access", "public")
            }

            // Clean up .npmrc
            npmrcFile.delete()
        }
    }

    // Task to publish to GitHub Packages
    tasks.register("publishToGitHubPackages") {
        group = "publishing"
        description = "Publish Kotlin/JS library to GitHub Packages npm registry"

        dependsOn("jsProductionLibraryCompileSync")

        doLast {
            val githubToken = System.getenv("GITHUB_TOKEN")
            if (githubToken.isNullOrEmpty()) {
                throw IllegalStateException("GITHUB_TOKEN environment variable is not set. Required for publishing to GitHub Packages.")
            }

            if (!jsDistDir.exists()) {
                throw IllegalStateException("JS distribution directory does not exist: ${jsDistDir.absolutePath}")
            }

            // Create .npmrc file with GitHub Packages authentication
            val npmrcFile = File(jsDistDir, ".npmrc")
            npmrcFile.writeText(
                $$"""
                @kapstone:registry=https://npm.pkg.github.com
                //npm.pkg.github.com/:_authToken=${GITHUB_TOKEN}
            """.trimIndent())

            // Publish to GitHub Packages
            providers.exec {
                workingDir = jsDistDir
                environment("GITHUB_TOKEN", githubToken)
                commandLine("npm", "publish", "--registry", "https://npm.pkg.github.com")
            }

            // Clean up .npmrc
            npmrcFile.delete()
        }
    }

    // Task to publish to both registries
    tasks.register("publishJsToAllRegistries") {
        group = "publishing"
        description = "Publish Kotlin/JS library to both npm and GitHub Packages registries"

        dependsOn("publishToNpm", "publishToGitHubPackages")
    }
}

private fun loadProperties(file: File): Properties {
    return Properties().apply {
        FileInputStream(file).use { load(it) }
    }
}

fun KotlinJsTargetDsl.configurePublishing() {
    compilations["main"].packageJson {
        name = "@${publishProperties["POM_DEVELOPER_ID"] ?: "alisalimik"}/kapstone-kt"
        main = "capstone-kt.mjs"
        types = "capstone-kt.d.mts"
        private = false
        version = publishProperties["VERSION"]?.toString() ?: "1.0.0-alpha01"
        customField("keywords", listOf("kapstone", "capstone", "disassembler", "decompiler"))
        customField("license", publishProperties["POM_LICENSE_NAME"])
        customField("description", publishProperties["POM_DESCRIPTION"])
        customField("repository", mapOf(
            "type" to "git",
            "url" to "git+" + publishProperties["POM_URL"]
        ))
        customField("author", mapOf(
            "name" to publishProperties["POM_DEVELOPER_NAME"],
            "email" to publishProperties["POM_DEVELOPER_EMAIL"],
            "url" to publishProperties["POM_DEVELOPER_URL"]
        ))
    }
}