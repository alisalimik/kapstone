package ir.alisalimik.kapstone.exp.aarch64

import ir.alisalimik.kapstone.exp.INumericEnum
import ir.alisalimik.kapstone.internal.*

actual enum class AArch64Ic(override val value: UInt) : INumericEnum {

  // generated content <AArch64GenCSSystemOperandsEnum.inc:GET_ENUM_VALUES_IC> begin
  // clang-format off
  IALLU(AARCH64_IC_IALLU),
  IALLUIS(AARCH64_IC_IALLUIS),
  IVAU(AARCH64_IC_IVAU),

  // clang-format on
  // generated content <AArch64GenCSSystemOperandsEnum.inc:GET_ENUM_VALUES_IC> end
  ENDING(AARCH64_IC_ENDING),
}
