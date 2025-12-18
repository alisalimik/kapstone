plugins {
    `kotlin-dsl`
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xcontext-parameters")
    }

    sourceSets {
        all {
            languageSettings {
                optIn("org.jetbrains.kotlin.gradle.ExperimentalWasmDsl")
                optIn("org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi")
            }
        }
    }
}

gradlePlugin {
    plugins {
        register("kotstone-project") {
            id = "kotstone.project"
            implementationClass = "KotstoneProjectPlugin"
        }
    }
}

dependencies {
    implementation(libs.kotlin.multiplatform)
    implementation(libs.maven.publish)
    implementation(libs.ktfmt.gradle)
    implementation(libs.dokka.gradle)
    implementation(libs.android.gradle)
    implementation(libs.detekt.gradle)
    implementation(libs.binary.validator)
}