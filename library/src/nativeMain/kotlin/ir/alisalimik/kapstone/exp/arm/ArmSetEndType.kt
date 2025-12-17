package ir.alisalimik.kapstone.exp.arm

import ir.alisalimik.kapstone.exp.INumericEnum
import ir.alisalimik.kapstone.internal.*

actual enum class ArmSetEndType(override val value: UInt) : INumericEnum {

  INVALID(ARM_SETEND_INVALID), // /< Uninitialized.
  BE(ARM_SETEND_BE), // /< BE operand.
  LE(ARM_SETEND_LE), // /< LE operand
}
