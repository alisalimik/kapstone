package ir.alisalimik.kapstone.exp.aarch64

import ir.alisalimik.kapstone.exp.INumericEnum

/** Capstone AArch64 trace synchronization barrier. */
expect enum class AArch64Tsb : INumericEnum {
  CSYNC,
  ENDING,
}
