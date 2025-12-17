package ir.alisalimik.kapstone.exp.aarch64

import ir.alisalimik.kapstone.exp.INumericEnum

expect enum class AArch64Shifter : INumericEnum {
  INVALID,
  LSL,
  MSL,
  LSR,
  ASR,
  ROR,
  LSL_REG,
  MSL_REG,
  LSR_REG,
  ASR_REG,
  ROR_REG,
}
