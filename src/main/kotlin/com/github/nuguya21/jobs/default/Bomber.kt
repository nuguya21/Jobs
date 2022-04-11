package com.github.nuguya21.jobs.default

import com.github.nuguya21.jobs.JobsPlugin
import com.github.nuguya21.jobs.PersonManager
import com.github.nuguya21.jobs.api.Job
import com.github.nuguya21.jobs.api.Person
import com.github.nuguya21.jobs.jobject.BlockJobject
import com.github.nuguya21.jobs.util.Direction
import com.github.nuguya21.jobs.util.GravityUtil
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerToggleSneakEvent
import org.bukkit.scheduler.BukkitRunnable

class Bomber(
    override val player: Player
): Person {

    override var job: Job? = BomberJob()
}

class BomberJob: Job {
    override val id: String = "bomber"
    override val description: String = "Bomb!"

    override fun onEnable(jobsPlugin: JobsPlugin) {
        Bukkit.getPluginManager().registerEvents(BomberListener(), jobsPlugin)
    }
}

class BomberListener: Listener {
    private val jobsPlugin: JobsPlugin = Bukkit.getPluginManager().getPlugin("Jobs")!! as JobsPlugin

    @EventHandler
    fun onSneak(event: PlayerToggleSneakEvent) {
        val player = event.player
        val person = PersonManager.getPerson(player)
        if (event.isSneaking) {
            if (person.job is BomberJob) {
                BlockJobject(jobsPlugin, player.eyeLocation, Bukkit.createBlockData(Material.TNT)).apply {
                    this.gravity = GravityUtil(this.location, Direction(player.location.yaw, player.location.pitch), 25.0, 9.8 * 1.25)

                    object : BukkitRunnable() {
                        override fun run() {
                            this@apply.remove = true
                        }
                    }.runTaskLater(jobsPlugin, 100L)
                }
            }
        }
    }
}