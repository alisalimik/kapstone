package ir.alisalimik.kapstone.exp.arm

import ir.alisalimik.kapstone.exp.INumericEnum
import ir.alisalimik.kapstone.internal.*

actual enum class ArmCpsModeType(override val value: UInt) : INumericEnum {
  INVALID(ARM_CPSMODE_INVALID),
  IE(ARM_CPSMODE_IE),
  ID(ARM_CPSMODE_ID)
}
