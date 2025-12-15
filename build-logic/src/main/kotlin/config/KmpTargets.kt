package config

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import platform.Host
import platform.toolchains

fun KotlinMultiplatformExtension.configureTargets(project: Project) {
    jvm()
    androidTarget()

    // JS / WASM
    js(IR) {
        nodejs()
        browser()
        compilerOptions {
            freeCompilerArgs.addAll("-Xes-long-as-bigint", "-XXLanguage:+JsAllowLongInExportedDeclarations")
        }
        binaries.library()
    }

    wasmJs {
        nodejs()
        binaries.library()
    }

    wasmWasi {
        nodejs()
        binaries.library()
    }

    // Apple targets (macOS only)
    if (Host.isMac) {
        iosX64()
        iosArm64()
        iosSimulatorArm64()
        macosX64()
        macosArm64()
        watchosX64()
        watchosArm64()
        watchosArm32()
        watchosDeviceArm64()
        watchosSimulatorArm64()
        tvosX64()
        tvosArm64()
        tvosSimulatorArm64()
    }
    linuxX64()
    linuxArm64()

    // Windows - use configuration cache-compatible toolchain detection
    if (project.toolchains.mingwX64.get()) {
        mingwX64()
    }

    // Android Native
    androidNativeArm64()
    androidNativeArm32()
    androidNativeX64()
    androidNativeX86()
}