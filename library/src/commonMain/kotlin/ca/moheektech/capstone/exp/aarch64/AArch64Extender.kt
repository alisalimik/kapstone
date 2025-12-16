package ca.moheektech.capstone.exp.aarch64

import ca.moheektech.capstone.exp.INumericEnum
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport


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
