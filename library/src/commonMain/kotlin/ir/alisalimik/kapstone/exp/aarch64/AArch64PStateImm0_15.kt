package ir.alisalimik.kapstone.exp.aarch64

import ir.alisalimik.kapstone.exp.INumericEnum

/** Capstone AArch64 PState immediate 0-15. */
expect enum class AArch64PStateImm0_15 : INumericEnum {
  DAIFCLR,
  DAIFSET,
  DIT,
  PAN,
  SPSEL,
  SSBS,
  TCO,
  UAO,
  ENDING,
}
