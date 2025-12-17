package ir.alisalimik.kapstone.exp.arm

import ir.alisalimik.kapstone.exp.INumericEnum

/** Capstone ARM operand type. */
expect enum class ArmOpType : INumericEnum {
  INVALID,
  REG,
  IMM,
  FP,
  PRED,
  CIMM,
  PIMM,
  SETEND,
  SYSREG,
  BANKEDREG,
  SPSR,
  CPSR,
  SYSM,
  VPRED_R,
  VPRED_N,
  MEM
}
