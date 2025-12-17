package ir.alisalimik.kapstone.exp.x86

import ir.alisalimik.kapstone.exp.INumericEnum

expect enum class X86AvxRoundingMode : INumericEnum {
  INVALID,
  RN,
  RD,
  RU,
  RZ;

  companion object {
    fun fromValue(value: Int): X86AvxRoundingMode
  }
}
