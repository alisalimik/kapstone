package ir.alisalimik.kapstone.exp.aarch64

import ir.alisalimik.kapstone.exp.INumericEnum

expect enum class AArch64Extender : INumericEnum {
  INVALID,
  UXTB,
  UXTH,
  UXTW,
  UXTX,
  SXTB,
  SXTH,
  SXTW,
  SXTX,
}
