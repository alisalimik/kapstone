package ir.alisalimik.kapstone.exp.wasm

import ir.alisalimik.kapstone.exp.INumericEnum

/** Capstone WASM instruction group. */
expect enum class WasmInstructionGroup : INumericEnum {

  INVALID, /// < = CS_GRP_INVALID
  NUMBERIC,
  PARAMETRIC,
  VARIABLE,
  MEMORY,
  CONTROL,
  ENDING, /// < <-- mark the end of the list of groups
}
