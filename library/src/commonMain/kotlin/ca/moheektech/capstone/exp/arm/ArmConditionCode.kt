package ca.moheektech.capstone.exp.arm

import ca.moheektech.capstone.exp.INumericEnum

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
