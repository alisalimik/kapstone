package ir.alisalimik.kapstone.exp.aarch64

import ir.alisalimik.kapstone.exp.INumericEnum
import ir.alisalimik.kapstone.internal.AARCH64_EXT_INVALID
import ir.alisalimik.kapstone.internal.AARCH64_EXT_SXTB
import ir.alisalimik.kapstone.internal.AARCH64_EXT_SXTH
import ir.alisalimik.kapstone.internal.AARCH64_EXT_SXTW
import ir.alisalimik.kapstone.internal.AARCH64_EXT_SXTX
import ir.alisalimik.kapstone.internal.AARCH64_EXT_UXTB
import ir.alisalimik.kapstone.internal.AARCH64_EXT_UXTH
import ir.alisalimik.kapstone.internal.AARCH64_EXT_UXTW
import ir.alisalimik.kapstone.internal.AARCH64_EXT_UXTX
import ir.alisalimik.kapstone.internal.ExportedApi

@ExportedApi
actual enum class AArch64Extender(override val value: Int) : INumericEnum {
  INVALID(AARCH64_EXT_INVALID),
  UXTB(AARCH64_EXT_UXTB),
  UXTH(AARCH64_EXT_UXTH),
  UXTW(AARCH64_EXT_UXTW),
  UXTX(AARCH64_EXT_UXTX),
  SXTB(AARCH64_EXT_SXTB),
  SXTH(AARCH64_EXT_SXTH),
  SXTW(AARCH64_EXT_SXTW),
  SXTX(AARCH64_EXT_SXTX),
}
