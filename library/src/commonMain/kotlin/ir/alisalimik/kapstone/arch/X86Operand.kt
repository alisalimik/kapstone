package ir.alisalimik.kapstone.arch

import ir.alisalimik.kapstone.enums.AccessType
import ir.alisalimik.kapstone.exp.x86.X86AvxBroadcast
import ir.alisalimik.kapstone.exp.x86.X86OpType
import ir.alisalimik.kapstone.internal.ExportedApi
import ir.alisalimik.kapstone.model.Register

/** X86 instruction operand. */
@ExportedApi
data class X86Operand(
    val type: X86OpType,
    val access: AccessType = AccessType.INVALID,
    val size: Int = 0,

    /** Register (for REG type) */
    val reg: Register? = null,

    /** Immediate value (for IMM type) */
    val imm: Long? = null,

    /** Memory operand (for MEM type) */
    val mem: X86MemoryOperand? = null,

    /** AVX broadcast type */
    val avxBcast: X86AvxBroadcast = X86AvxBroadcast.BCAST_INVALID,

    /** AVX zero opmask {z} */
    val avxZeroOpmask: Boolean = false
)
