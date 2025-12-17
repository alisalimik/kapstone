package ir.alisalimik.kapstone.arch

import ir.alisalimik.kapstone.internal.ExportedApi
import ir.alisalimik.kapstone.model.Register

/** AArch64 memory operand. */
@ExportedApi
data class AArch64MemoryOperand(
    val base: Register? = null,
    val index: Register? = null,
    val disp: Long = 0
)
