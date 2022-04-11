package com.github.nuguya21.jobs

import com.github.nuguya21.jobs.command.JobsCommand
import com.github.nuguya21.jobs.default.BomberJob
import com.github.nuguya21.jobs.default.NinjaJob
import com.github.nuguya21.jobs.default.RunnerJob
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class JobsPlugin: JavaPlugin() {

    override fun onEnable() {
        // Default Jobs
        JobManager.register(RunnerJob())
        JobManager.register(BomberJob())
        JobManager.register(NinjaJob())
        // Commands
        Bukkit.getCommandMap().register("jobs", JobsCommand.fallbackPrefix, JobsCommand())
        // Jobs onEnable/onTick
        for (jobClass in JobManager.getJobs()) {
            val job = jobClass.newInstance()
            job.onEnable(this)
        }
        Bukkit.getScheduler().runTaskTimer(this, Runnable {
            for (player in Bukkit.getOnlinePlayers()) {
                PersonManager.getPerson(player).apply {
                    this.job?.onTick(this, this@JobsPlugin)
                }
            }
        }, 0L, 1L)
    }

    override fun onDisable() {
        for (jobClass in JobManager.getJobs()) {
            val job = jobClass.newInstance()
            job.onDisable()
        }
    }

    override fun onLoad() {
        for (jobClass in JobManager.getJobs()) {
            val job = jobClass.newInstance()
            job.onLoad(this)
        }
    }

}