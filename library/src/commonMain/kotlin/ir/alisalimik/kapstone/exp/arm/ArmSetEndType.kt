package ir.alisalimik.kapstone.exp.arm

import ir.alisalimik.kapstone.exp.INumericEnum

/** Capstone ARM setend type. */
expect enum class ArmSetEndType : INumericEnum {

  INVALID, /// < Uninitialized.
  BE, /// < BE operand.
  LE, /// < LE operand
}
