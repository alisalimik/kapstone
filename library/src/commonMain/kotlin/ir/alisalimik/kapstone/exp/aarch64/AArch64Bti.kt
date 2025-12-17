package ir.alisalimik.kapstone.exp.aarch64

import ir.alisalimik.kapstone.exp.INumericEnum

/** Capstone AArch64 BTI. */
expect enum class AArch64Bti : INumericEnum {
  C,
  J,
  JC,
  ENDING,
}
