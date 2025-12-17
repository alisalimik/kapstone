package ir.alisalimik.kapstone.exp.aarch64

import ir.alisalimik.kapstone.exp.INumericEnum

/** Capstone AArch64 PState immediate 0-1. */
expect enum class AArch64PStateImm0_1 : INumericEnum {
  ALLINT,
  PM,
  ENDING,
}
