package ir.alisalimik.kapstone.exp.x86

import ir.alisalimik.kapstone.exp.INumericEnum

/** Capstone X86 XOP condition code. */
expect enum class X86XopConditionCode : INumericEnum {

  INVALID, /// < Uninitialized.
  LT,
  LE,
  GT,
  GE,
  EQ,
  NEQ,
  FALSE,
  TRUE,
}
