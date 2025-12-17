package ir.alisalimik.kapstone.exp.arm

import ir.alisalimik.kapstone.exp.INumericEnum
import ir.alisalimik.kapstone.internal.*

@ExportedApi
actual enum class ArmCpsFlagType(override val value: Int) : INumericEnum {
  INVALID(ARM_CPSFLAG_INVALID),
  F(ARM_CPSFLAG_F),
  I(ARM_CPSFLAG_I),
  A(ARM_CPSFLAG_A),
  NONE(ARM_CPSFLAG_NONE)
}
