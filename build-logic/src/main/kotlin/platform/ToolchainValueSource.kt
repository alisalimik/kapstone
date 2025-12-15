package platform

import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property
import org.gradle.api.provider.ValueSource
import org.gradle.api.provider.ValueSourceParameters

/**
 * Configuration cache-compatible value source for checking command existence.
 * This executes the command check only when needed and caches the result.
 */
abstract class CommandExistsValueSource : ValueSource<Boolean, CommandExistsValueSource.Parameters> {
    interface Parameters : ValueSourceParameters {
        val command: Property<String>
        val args: ListProperty<String>
    }

    override fun obtain(): Boolean {
        val cmd = parameters.command.get()
        val args = parameters.args.getOrElse(listOf("--version"))

        return runCatching {
            ProcessBuilder(cmd, *args.toTypedArray())
                .redirectErrorStream(true)
                .start()
                .waitFor() == 0
        }.getOrDefault(false)
    }
}

/**
 * Value source for finding Emscripten root directory.
 * Returns empty string if not found (ValueSource cannot return null).
 */
abstract class EmscriptenRootValueSource : ValueSource<String, EmscriptenRootValueSource.Parameters> {
    interface Parameters : ValueSourceParameters {
        val hasEmscripten: Property<Boolean>
    }

    override fun obtain(): String {
        if (!parameters.hasEmscripten.get()) return ""

        return runCatching {
            val process = ProcessBuilder("which", "emcc")
                .redirectErrorStream(true)
                .start()
            val output = process.inputStream.bufferedReader().readText().trim()
            process.waitFor()
            if (output.isNotEmpty()) {
                // emcc is at EMSCRIPTEN_ROOT/emcc, so get parent directory
                java.io.File(output).parentFile?.absolutePath ?: ""
            } else ""
        }.getOrDefault("")
    }
}

/**
 * Value source for finding Emscripten toolchain file.
 * Returns empty string if not found (ValueSource cannot return null).
 */
abstract class EmscriptenToolchainValueSource : ValueSource<String, EmscriptenToolchainValueSource.Parameters> {
    interface Parameters : ValueSourceParameters {
        val emscriptenRoot: Property<String>
    }

    override fun obtain(): String {
        val emRoot = parameters.emscriptenRoot.orNull
        if (emRoot.isNullOrEmpty()) return ""

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
            output ?: ""
        }.getOrDefault("")
    }
}

/**
 * Value source for finding llvm-nm.
 * Returns empty string if not found (ValueSource cannot return null).
 */
abstract class LlvmNmValueSource : ValueSource<String, LlvmNmValueSource.Parameters> {
    interface Parameters : ValueSourceParameters {
        val emscriptenRoot: Property<String>
        val hasLlvmNm: Property<Boolean>
    }

    override fun obtain(): String {
        val emRoot = parameters.emscriptenRoot.orNull
        if (!emRoot.isNullOrEmpty()) {
            val llvmNm = java.io.File(emRoot, "llvm-nm")
            if (llvmNm.exists()) {
                return llvmNm.absolutePath
            }
        }

        // Try system llvm-nm
        return if (parameters.hasLlvmNm.get()) "llvm-nm" else ""
    }
}
