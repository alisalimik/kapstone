package ir.alisalimik.kapstone.exp.x86

import ir.alisalimik.kapstone.exp.INumericEnum
import ir.alisalimik.kapstone.internal.CS_OP_IMM
import ir.alisalimik.kapstone.internal.CS_OP_INVALID
import ir.alisalimik.kapstone.internal.CS_OP_MEM
import ir.alisalimik.kapstone.internal.CS_OP_REG
import ir.alisalimik.kapstone.internal.ExportedApi

@ExportedApi
actual enum class X86OpType(override val value: Int) : INumericEnum {
  INVALID(CS_OP_INVALID),
  REG(CS_OP_REG),
  IMM(CS_OP_IMM),
  MEM(CS_OP_MEM);

  actual companion object {
    actual fun fromValue(value: Int): X86OpType {
      return entries.firstOrNull { it.value == value } ?: INVALID
    }
  }
}
