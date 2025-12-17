package ir.alisalimik.kapstone.exp.aarch64

import ir.alisalimik.kapstone.exp.INumericEnum
import ir.alisalimik.kapstone.internal.*

actual enum class AArch64ExactFpImm(override val value: UInt) : INumericEnum {

  HALF(AARCH64_EXACTFPIMM_HALF),
  ONE(AARCH64_EXACTFPIMM_ONE),
  TWO(AARCH64_EXACTFPIMM_TWO),
  ZERO(AARCH64_EXACTFPIMM_ZERO),

  // clang-format on
  // generated content <AArch64GenCSSystemOperandsEnum.inc:GET_ENUM_VALUES_ExactFPImm> end
  INVALID(AARCH64_EXACTFPIMM_INVALID),
  ENDING(AARCH64_EXACTFPIMM_ENDING),
}
