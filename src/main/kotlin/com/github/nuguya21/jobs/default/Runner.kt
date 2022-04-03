package com.github.nuguya21.jobs.default

import com.github.nuguya21.jobs.JobsPlugin
import com.github.nuguya21.jobs.PersonManager
import com.github.nuguya21.jobs.api.Job
import com.github.nuguya21.jobs.api.Person
import org.bukkit.Bukkit
import org.bukkit.Particle
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerToggleSprintEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class Runner(
    override val player: Player
): Person {
    override var job: Job? = RunnerJob()
}

class RunnerJob: Job {
    override val id: String = "runner"

    override fun onEnable(jobsPlugin: JobsPlugin) {
        Bukkit.getPluginManager().registerEvents(RunnerListener(), jobsPlugin)
    }

    override fun onTick(person: Person, jobsPlugin: JobsPlugin) {
        person.player.spawnParticle(Particle.CLOUD, person.player.location, 10, 0.1, 0.1, 0.1, 0.0)
    }
}

class RunnerListener: Listener {
    @EventHandler
    fun onSprint(event: PlayerToggleSprintEvent) {
        val person = PersonManager.getPerson(event.player)
        val job = person.job
        if (job != null) {
            if (job is RunnerJob) {
                if (event.isSprinting) {
                    person.player.addPotionEffect(PotionEffect(PotionEffectType.SPEED, 1000000, 3, false, false, false))
                } else person.player.removePotionEffect(PotionEffectType.SPEED)
            }
        }
    }
}