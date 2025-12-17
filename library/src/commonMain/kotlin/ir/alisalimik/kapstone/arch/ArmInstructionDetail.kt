package ir.alisalimik.kapstone.arch

import ir.alisalimik.kapstone.exp.arm.ArmConditionCode
import ir.alisalimik.kapstone.exp.arm.ArmCpsFlagType
import ir.alisalimik.kapstone.exp.arm.ArmCpsModeType
import ir.alisalimik.kapstone.exp.arm.ArmMemoryBarrierOption
import ir.alisalimik.kapstone.exp.arm.ArmVectorDataType
import ir.alisalimik.kapstone.internal.ExportedApi

/** ARM instruction details. */
@ExportedApi
data class ArmInstructionDetail(
    val usermode: Boolean = false,
    val vectorSize: Int = 0,
    val vectorData: ArmVectorDataType = ArmVectorDataType.INVALID,
    val cpsMode: ArmCpsModeType = ArmCpsModeType.INVALID,
    val cpsFlag: ArmCpsFlagType = ArmCpsFlagType.INVALID,
    val cc: ArmConditionCode = ArmConditionCode.Invalid,
    val updateFlags: Boolean = false,
    val writeback: Boolean = false,
    val memBarrier: ArmMemoryBarrierOption = ArmMemoryBarrierOption.RESERVED_0, // Default?
    val operands: List<ArmOperand> = emptyList()
)
