package com.github.nuguya21.jobs

import com.github.nuguya21.jobs.command.JobsCommand
import com.github.nuguya21.jobs.default.RunnerJob
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.util.function.Consumer

class JobsPlugin: JavaPlugin() {

    override fun onEnable() {
        JobManager.register(RunnerJob())
        // Default Jobs
        Bukkit.getCommandMap().register("jobs", JobsCommand.fallbackPrefix, JobsCommand())
        for (job in JobManager.getJobs()) {
            job.onEnable(this)
            Bukkit.getScheduler().runTaskTimer(this, Consumer {
                for (player in Bukkit.getOnlinePlayers()) {
                    val person = PersonManager.getPerson(player)
                    if (person.job == job) job.onTick(person, this)
                }
            }, 0L, 1L)
        }
    }

    override fun onDisable() {
        for (job in JobManager.getJobs()) {
            job.onDisable()
        }
    }

    override fun onLoad() {
        for (job in JobManager.getJobs()) {
            job.onLoad(this)
        }
    }

}