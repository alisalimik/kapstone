package ir.alisalimik.kapstone.api

import ir.alisalimik.kapstone.internal.platform.initializeCapstoneModule

actual suspend fun initializeCapstone() {
  initializeCapstoneModule()
}
