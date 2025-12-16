package ca.moheektech.capstone.exp.aarch64

import ca.moheektech.capstone.exp.INumericEnum
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport


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
