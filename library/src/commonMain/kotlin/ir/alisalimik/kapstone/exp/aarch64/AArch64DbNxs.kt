package ir.alisalimik.kapstone.exp.aarch64

import ir.alisalimik.kapstone.exp.INumericEnum

/** Capstone AArch64 data barrier (nXS). */
expect enum class AArch64DbNxs : INumericEnum {

  // generated content <AArch64GenCSSystemOperandsEnum.inc:GET_ENUM_VALUES_DBnXS> begin
  // clang-format off
  ISHNXS,
  NSHNXS,
  OSHNXS,
  SYNXS,

  // clang-format on
  // generated content <AArch64GenCSSystemOperandsEnum.inc:GET_ENUM_VALUES_DBnXS> end
  ENDING,
}
