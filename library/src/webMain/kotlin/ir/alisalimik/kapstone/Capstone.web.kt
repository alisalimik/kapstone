package ir.alisalimik.kapstone

import ir.alisalimik.kapstone.internal.platform.initializeCapstoneModule

suspend fun initCapstone() {
  initializeCapstoneModule()
}
