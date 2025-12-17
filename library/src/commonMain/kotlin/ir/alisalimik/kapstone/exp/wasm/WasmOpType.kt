package ir.alisalimik.kapstone.exp.wasm

import ir.alisalimik.kapstone.exp.INumericEnum

/** Capstone WASM operand type. */
expect enum class WasmOpType : INumericEnum {
  INVALID,
  IMM,
  NONE,
  INT7,
  VARUINT32,
  VARUINT64,
  UINT32,
  UINT64,
  BRTABLE,
}
