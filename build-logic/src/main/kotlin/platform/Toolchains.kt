package platform

import org.gradle.api.Project

/**
 * Legacy toolchain detection object.
 *
 * **DEPRECATED**: This object executes external processes during Gradle configuration phase,
 * which is incompatible with Gradle's configuration cache feature.
 *
 * Use [ToolchainProviders] instead via `project.toolchains` extension for configuration cache compatibility.
 *
 * Example migration:
 * ```
 * // Old (configuration cache incompatible):
 * if (Toolchains.hasZig) { ... }
 *
 * // New (configuration cache compatible):
 * if (project.toolchains.hasZig.get()) { ... }
 * ```
 */
@Deprecated(
    "Use project.toolchains instead for configuration cache compatibility",
    ReplaceWith("project.toolchains", "platform.toolchains"),
    DeprecationLevel.WARNING
)
object Toolchains {

    /**
     * Check if a command exists on the system.
     *
     * **DEPRECATED**: Executes external process during configuration time.
     * Use `project.toolchains.commandExists(cmd).get()` instead.
     */
    @Deprecated("Use project.toolchains.commandExists() instead")
    fun commandExists(cmd: String, vararg args: String): Boolean {
        val versionArgs = if (args.isNotEmpty()) args else arrayOf("--version")
        return runCatching {
            ProcessBuilder(cmd, *versionArgs)
                .redirectErrorStream(true)
                .start()
                .waitFor() == 0
        }.getOrDefault(false)
    }

    @Deprecated("Use project.toolchains.hasZig.get() instead")
    val hasZig: Boolean = commandExists("zig", "version")

    @Deprecated("Use project.toolchains.nativeLinux.get() instead")
    val nativeLinux: Boolean = Host.isLinux

    @Deprecated("Use project.toolchains.linuxX64OnMac.get() instead")
    val linuxX64OnMac: Boolean = Host.isMac && (
            commandExists("x86_64-linux-gnu-gcc") ||
            commandExists("x86_64-linux-musl-gcc") ||
            hasZig ||
            commandExists("clang") ||
            commandExists("aarch64-unknown-linux-gnu-gcc")
    )

    @Deprecated("Use project.toolchains.mingwX64.get() instead")
    val mingwX64: Boolean =
        Host.isWindows || commandExists("x86_64-w64-mingw32-gcc") || hasZig

    @Deprecated("Use project.toolchains.mingwX86.get() instead")
    val mingwX86: Boolean =
        Host.isWindows || commandExists("i686-w64-mingw32-gcc") || hasZig

    @Deprecated("Use project.toolchains.linuxArm32.get() instead")
    val linuxArm32: Boolean =
        Host.isLinux || commandExists("arm-linux-gnueabihf-gcc") || hasZig

    @Deprecated("Use project.toolchains.hasEmscripten.get() instead")
    val hasEmscripten: Boolean = commandExists("emcc", "--version")

    @Deprecated("Use project.toolchains.getEmscriptenRoot().get() instead")
    fun getEmscriptenRoot(): String? {
        if (!hasEmscripten) return null
        return runCatching {
            val process = ProcessBuilder("which", "emcc")
                .redirectErrorStream(true)
                .start()
            val output = process.inputStream.bufferedReader().readText().trim()
            process.waitFor()
            if (output.isNotEmpty()) {
                // emcc is at EMSCRIPTEN_ROOT/emcc, so get parent directory
                java.io.File(output).parentFile?.absolutePath
            } else null
        }.getOrNull()
    }

    @Deprecated("Use project.toolchains.findEmscriptenToolchain().get() instead")
    fun findEmscriptenToolchain(): String? {
        val emRoot = getEmscriptenRoot() ?: return null
        val toolchainFile = java.io.File(emRoot, "cmake/Modules/Platform/Emscripten.cmake")

        if (toolchainFile.exists()) {
            return toolchainFile.absolutePath
        }

        // Fallback search
        return runCatching {
            val process = ProcessBuilder("find", "$emRoot/../..", "-name", "Emscripten.cmake")
                .redirectErrorStream(true)
                .start()
            val output = process.inputStream.bufferedReader().readLines().firstOrNull()
            process.waitFor()
            output
        }.getOrNull()
    }

    @Deprecated("Use project.toolchains.getLlvmNm().get() instead")
    fun getLlvmNm(): String? {
        val emRoot = getEmscriptenRoot() ?: return null
        val llvmNm = java.io.File(emRoot, "llvm-nm")

        if (llvmNm.exists()) {
            return llvmNm.absolutePath
        }

        // Try system llvm-nm
        return if (commandExists("llvm-nm", "--version")) "llvm-nm" else null
    }
}

/**
 * Configuration cache-compatible helper to get toolchain boolean value.
 * This evaluates the provider but allows Gradle to cache the result.
 */
internal fun Project.getToolchainBoolean(provider: org.gradle.api.provider.Provider<Boolean>): Boolean {
    return provider.get()
}