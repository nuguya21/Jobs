package com.github.nuguya21.jobs.default

import com.destroystokyo.paper.event.player.PlayerJumpEvent
import com.github.nuguya21.jobs.JobsPlugin
import com.github.nuguya21.jobs.PersonManager
import com.github.nuguya21.jobs.api.Job
import com.github.nuguya21.jobs.api.Person
import org.bukkit.Bukkit
import org.bukkit.Particle
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerToggleFlightEvent
import org.bukkit.util.Vector

class Ninja(
    override val player: Player
) : Person {
    override var job: Job? = NinjaJob()
}

class NinjaJob: Job {
    override val id: String = "ninja"
    override val description: String = "Nin Nin!"

    var doubleJump: Boolean = false

    override fun onEnable(jobsPlugin: JobsPlugin) {
        Bukkit.getPluginManager().registerEvents(NinjaListener(), jobsPlugin)
    }

    override fun onTick(person: Person, jobsPlugin: JobsPlugin) {
        if (person.job is NinjaJob) {
            val ninjaJob = person.job as NinjaJob
            if (person.player.isOnGround) ninjaJob.doubleJump = false
            if (!person.player.allowFlight) person.player.allowFlight = ninjaJob.doubleJump
        }
    }
}

class NinjaListener: Listener {

    @EventHandler
    fun onJump(event: PlayerJumpEvent) {
        val player = event.player
        val person = PersonManager.getPerson(player)
        if (person.job is NinjaJob) {
            val ninjaJob = person.job as NinjaJob
            ninjaJob.doubleJump = true
        }
    }

    @EventHandler
    fun onFly(event: PlayerToggleFlightEvent) {
        val player = event.player
        val person = PersonManager.getPerson(player)
        if (person.job is NinjaJob) {
            val ninjaJob = person.job as NinjaJob
            if (ninjaJob.doubleJump) {
                event.isCancelled = true
                person.player.velocity = Vector(0.0,0.5,0.0)
                person.player.world.spawnParticle(Particle.CLOUD, person.player.location, 10,0.1,0.1,0.1,0.0)
                ninjaJob.doubleJump = false
            }
        }
    }
}