package ir.alisalimik.kapstone.exp.aarch64

import ir.alisalimik.kapstone.exp.INumericEnum

/** Capstone AArch64 instruction synchronization barrier. */
expect enum class AArch64Isb : INumericEnum {

  // generated content <AArch64GenCSSystemOperandsEnum.inc:GET_ENUM_VALUES_ISB> begin
  // clang-format off
  SY,

  // clang-format on
  // generated content <AArch64GenCSSystemOperandsEnum.inc:GET_ENUM_VALUES_ISB> end
  ENDING,
}
