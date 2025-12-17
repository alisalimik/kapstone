package ca.moheektech.capstone.bit

import ca.moheektech.core.templates.bit.BitField

@BitFieldDsl
class BitFieldScope<T : Enum<T>> {
    private var bitField = BitField<T>()
    
    operator fun T.unaryPlus() {
        bitField = bitField.setFlag(this)
    }
    
    operator fun T.unaryMinus() {
        bitField = bitField.clearFlag(this)
    }
    
    operator fun BitField<T>.unaryPlus() {
        bitField = bitField.getCombined(this)
    }
    
    internal fun build(): BitField<T> = bitField
}