package ir.alisalimik.kapstone.exp.x86

import ir.alisalimik.kapstone.exp.INumericEnum
import ir.alisalimik.kapstone.internal.X86_OP_IMM
import ir.alisalimik.kapstone.internal.X86_OP_INVALID
import ir.alisalimik.kapstone.internal.X86_OP_MEM
import ir.alisalimik.kapstone.internal.X86_OP_REG

actual enum class X86OpType(override val value: UInt) : INumericEnum {
  INVALID(X86_OP_INVALID),
  REG(X86_OP_REG),
  IMM(X86_OP_IMM),
  MEM(X86_OP_MEM);

  actual companion object {
    actual fun fromValue(value: Int): X86OpType {
      return entries.firstOrNull { it.value == value.toUInt() } ?: INVALID
    }
  }
}
