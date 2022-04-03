package com.github.nuguya21.jobs.command

import com.github.nuguya21.jobs.JobManager
import com.github.nuguya21.jobs.PersonManager
import com.github.nuguya21.jobs.api.Job
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.CommandSender
import org.bukkit.command.defaults.BukkitCommand
import org.bukkit.entity.Player

class JobsCommand : BukkitCommand("jobs") {

    companion object {
        const val fallbackPrefix: String = "jobs_plugin"
    }

    init {
        this.description = "Manage Jobs Plugin"
        this.usageMessage = "/jobs"
    }

    override fun execute(sender: CommandSender, commandLabel: String, args: Array<out String>): Boolean {
        try {
            val target: Player? = Bukkit.getPlayer(args[0])
            if (target == null) {
                sender.sendMessage("${ChatColor.RED}${args[0]} 을(를) 찾지 못했습니다")
                return true
            }
            if (args.size > 1) {
                val job: Job? = JobManager.getJob(args[1])
                if (job != null) {
                    PersonManager.getPerson(target).job = job
                    sender.sendMessage("${target.name} 의 직업을 ${job.id} (으)로 변경했습니다")
                }
            } else {
                if (PersonManager.getPerson(target).job == null) sender.sendMessage("${target.name} 의 직업은 없습니다")
                else sender.sendMessage("${target.name} 의 직업은 없습니다")
            }
        } catch (e: ArrayIndexOutOfBoundsException) {
            sender.sendMessage("${ChatColor.RED}필요한 요소가 포함되어 있지 않습니다")
        }
        return true
    }

    override fun tabComplete(sender: CommandSender, alias: String, args: Array<out String>): MutableList<String> {
        val options: MutableList<String> = mutableListOf()
        when (args.size) {
            1 -> {
                for (onlinePlayer in Bukkit.getOnlinePlayers()) {
                    options.add(onlinePlayer.name)
                }
            }
            2 -> {
                for (job in JobManager.getJobs()) {
                    options.add(job.id)
                }
            }
        }
        return options
    }
}