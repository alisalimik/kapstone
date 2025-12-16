package ca.moheektech.capstone.exp.arm

import ca.moheektech.capstone.exp.INumericEnum
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport


expect enum class ArmConditionCode : INumericEnum {
  EQ,
  NE,
  HS,
  LO,
  MI,
  PL,
  VS,
  VC,
  HI,
  LS,
  GE,
  LT,
  GT,
  LE,
  AL,
  UNDEF,
  Invalid
}
