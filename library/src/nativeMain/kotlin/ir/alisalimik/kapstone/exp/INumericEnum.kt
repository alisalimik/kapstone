package ir.alisalimik.kapstone.exp

internal actual interface INumericEnum {
  val value: UInt

  actual fun toInt(): Int {
    return value.toInt()
  }
}
