package ir.alisalimik.kapstone.exp.arm

import ir.alisalimik.kapstone.exp.INumericEnum

/** Capstone ARM memory barrier option. */
expect enum class ArmMemoryBarrierOption : INumericEnum {

  RESERVED_0,
  OSHLD,
  OSHST,
  OSH,
  RESERVED_4,
  NSHLD,
  NSHST,
  NSH,
  RESERVED_8,
  ISHLD,
  ISHST,
  ISH,
  RESERVED_12,
  LD,
  ST,
  SY,
}
