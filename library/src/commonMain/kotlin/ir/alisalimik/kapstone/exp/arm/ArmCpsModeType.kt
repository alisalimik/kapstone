package ir.alisalimik.kapstone.exp.arm

import ir.alisalimik.kapstone.exp.INumericEnum

/** Capstone ARM CPS mode type. */
expect enum class ArmCpsModeType : INumericEnum {
  INVALID,
  IE,
  ID
}
