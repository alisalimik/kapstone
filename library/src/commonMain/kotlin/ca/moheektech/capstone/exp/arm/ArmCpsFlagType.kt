package ca.moheektech.capstone.exp.arm

import ca.moheektech.capstone.exp.INumericEnum
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport

/** Capstone ARM CPS flag type. */
expect enum class ArmCpsFlagType : INumericEnum {
  INVALID,
  F,
  I,
  A,
  NONE
}
