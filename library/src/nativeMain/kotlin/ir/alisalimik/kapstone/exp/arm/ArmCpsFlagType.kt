package ir.alisalimik.kapstone.exp.arm

import ir.alisalimik.kapstone.exp.INumericEnum
import ir.alisalimik.kapstone.internal.*

actual enum class ArmCpsFlagType(override val value: UInt) : INumericEnum {
  INVALID(ARM_CPSFLAG_INVALID),
  F(ARM_CPSFLAG_F),
  I(ARM_CPSFLAG_I),
  A(ARM_CPSFLAG_A),
  NONE(ARM_CPSFLAG_NONE)
}
