package ir.alisalimik.kapstone.exp.x86

import ir.alisalimik.kapstone.exp.INumericEnum

expect enum class X86OpType : INumericEnum {
  INVALID,
  REG,
  IMM,
  MEM;

  companion object {
    fun fromValue(value: Int): X86OpType
  }
}
