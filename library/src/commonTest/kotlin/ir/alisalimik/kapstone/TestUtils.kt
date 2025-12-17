package ir.alisalimik.kapstone

import ir.alisalimik.kapstone.api.CapstoneEngine
import ir.alisalimik.kapstone.enums.Architecture

/**
 * Check if an architecture is supported on this platform. Returns true if supported, false
 * otherwise.
 */
fun isArchitectureSupported(arch: Architecture): Boolean {
  return try {
    CapstoneEngine.isSupported(arch)
  } catch (e: Exception) {
    false
  }
}
