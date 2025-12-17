package ir.alisalimik.kapstone.exp.arm

import ir.alisalimik.kapstone.exp.INumericEnum

/** Capstone ARM CPS flag type. */
expect enum class ArmCpsFlagType : INumericEnum {
  INVALID,
  F,
  I,
  A,
  NONE
}
