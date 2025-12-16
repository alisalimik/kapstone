package ca.moheektech.capstone.model

import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport

/**
 * Represents a CPU register.
 *
 * @property id Register ID (architecture-specific)
 * @property name Register name (e.g., "rax", "x0", "r1")
 */
@OptIn(ExperimentalJsExport::class)
@JsExport
data class Register(val id: Int, val name: String? = null) {
  override fun toString(): String = name ?: "reg_$id"
}
