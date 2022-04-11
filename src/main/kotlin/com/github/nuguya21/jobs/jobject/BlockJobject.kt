package com.github.nuguya21.jobs.jobject

import com.github.nuguya21.jobs.JobsPlugin
import com.github.nuguya21.jobs.util.GravityUtil
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.block.data.BlockData
import org.bukkit.scheduler.BukkitRunnable

class BlockJobject(override val plugin: JobsPlugin, override var location: Location, val data: BlockData): Jobject {

    val world: World = this.location.world

    init {
        object : BukkitRunnable() {
            override fun run() {
                if (!remove) {
                    world.spawnFallingBlock(this@BlockJobject.location, this@BlockJobject.data).apply {
                        this.ticksLived = 599
                        this.dropItem = false
                        this.setGravity(false)
                    }
                    gravity?.let {
                        location = it.nextTick()
                    }
                } else cancel()
            }

        }
    }

    override var gravity: GravityUtil? = null
    override var remove: Boolean = false
}