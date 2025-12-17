package ir.alisalimik.kapstone.exp.aarch64

import ir.alisalimik.kapstone.exp.INumericEnum
import ir.alisalimik.kapstone.internal.*

@ExportedApi
actual enum class AArch64Rprfm(override val value: Int) : INumericEnum {

  // generated content <AArch64GenCSSystemOperandsEnum.inc:GET_ENUM_VALUES_RPRFM> begin
  // clang-format off
  PLDKEEP(AARCH64_RPRFM_PLDKEEP),
  PLDSTRM(AARCH64_RPRFM_PLDSTRM),
  PSTKEEP(AARCH64_RPRFM_PSTKEEP),
  PSTSTRM(AARCH64_RPRFM_PSTSTRM),

  // clang-format on
  // generated content <AArch64GenCSSystemOperandsEnum.inc:GET_ENUM_VALUES_RPRFM> end
  ENDING(AARCH64_RPRFM_ENDING),
}
