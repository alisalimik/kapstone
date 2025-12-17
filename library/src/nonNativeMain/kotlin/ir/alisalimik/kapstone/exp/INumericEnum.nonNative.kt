package ir.alisalimik.kapstone.exp

internal actual interface INumericEnum {
  val value: Int

  actual fun toInt(): Int {
    return value
  }
}
