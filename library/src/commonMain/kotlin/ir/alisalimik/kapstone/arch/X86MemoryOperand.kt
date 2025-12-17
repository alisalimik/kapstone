package ir.alisalimik.kapstone.arch

import ir.alisalimik.kapstone.internal.ExportedApi
import ir.alisalimik.kapstone.model.Register

/** X86 memory operand. */
@ExportedApi
data class X86MemoryOperand(
    val segment: Register? = null,
    val base: Register? = null,
    val index: Register? = null,
    val scale: Int = 1,
    val disp: Long = 0
)
