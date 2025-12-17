package ir.alisalimik.kapstone.api

import ir.alisalimik.kapstone.internal.platform.CapstoneLibrary

actual suspend fun initializeCapstone() {
  CapstoneLibrary.INSTANCE
}
