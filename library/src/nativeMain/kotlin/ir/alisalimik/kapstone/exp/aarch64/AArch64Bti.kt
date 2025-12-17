package ir.alisalimik.kapstone.exp.aarch64

import ir.alisalimik.kapstone.exp.INumericEnum
import ir.alisalimik.kapstone.internal.*

actual enum class AArch64Bti(override val value: UInt) : INumericEnum {
  C(AARCH64_BTI_C),
  J(AARCH64_BTI_J),
  JC(AARCH64_BTI_JC),
  ENDING(AARCH64_BTI_ENDING),
}
