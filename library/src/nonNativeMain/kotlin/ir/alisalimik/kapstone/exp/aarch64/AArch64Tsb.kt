package ir.alisalimik.kapstone.exp.aarch64

import ir.alisalimik.kapstone.exp.INumericEnum
import ir.alisalimik.kapstone.internal.*

@ExportedApi
actual enum class AArch64Tsb(override val value: Int) : INumericEnum {
  CSYNC(AARCH64_TSB_CSYNC),
  ENDING(AARCH64_TSB_ENDING),
}
