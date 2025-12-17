package ir.alisalimik.kapstone.exp.arm

import ir.alisalimik.kapstone.exp.INumericEnum
import ir.alisalimik.kapstone.internal.*

@ExportedApi
actual enum class ArmCpsModeType(override val value: Int) : INumericEnum {
  INVALID(ARM_CPSMODE_INVALID),
  IE(ARM_CPSMODE_IE),
  ID(ARM_CPSMODE_ID)
}
