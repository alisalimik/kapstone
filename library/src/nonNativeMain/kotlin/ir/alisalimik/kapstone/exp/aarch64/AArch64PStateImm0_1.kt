package ir.alisalimik.kapstone.exp.aarch64

import ir.alisalimik.kapstone.exp.INumericEnum
import ir.alisalimik.kapstone.internal.*

@ExportedApi
actual enum class AArch64PStateImm0_1(override val value: Int) : INumericEnum {
  ALLINT(AARCH64_PSTATEIMM0_1_ALLINT),
  PM(AARCH64_PSTATEIMM0_1_PM),
  ENDING(AARCH64_PSTATEIMM0_1_ENDING),
}
