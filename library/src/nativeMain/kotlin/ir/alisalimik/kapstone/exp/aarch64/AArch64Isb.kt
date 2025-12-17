package ir.alisalimik.kapstone.exp.aarch64

import ir.alisalimik.kapstone.exp.INumericEnum
import ir.alisalimik.kapstone.internal.*

actual enum class AArch64Isb(override val value: UInt) : INumericEnum {

  // generated content <AArch64GenCSSystemOperandsEnum.inc:GET_ENUM_VALUES_ISB> begin
  // clang-format off
  SY(AARCH64_ISB_SY),

  // clang-format on
  // generated content <AArch64GenCSSystemOperandsEnum.inc:GET_ENUM_VALUES_ISB> end
  ENDING(AARCH64_ISB_ENDING),
}
