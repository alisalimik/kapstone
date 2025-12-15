package external.tasks

import org.gradle.api.DefaultTask

/**
 * Task to build Capstone for all native targets.
 * This is an aggregator task that depends on all individual build tasks.
 * Dependencies are configured at configuration time in registerCapstoneBuildTasks().
 */
abstract class BuildAllCapstoneTask : DefaultTask() {
    // No task action needed - this is just an aggregator task
    // Dependencies are configured at configuration time
}