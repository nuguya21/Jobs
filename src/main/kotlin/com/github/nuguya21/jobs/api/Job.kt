package com.github.nuguya21.jobs.api

import com.github.nuguya21.jobs.JobsPlugin

interface Job {

    val id: String
    val description: String?

    fun onEnable(jobsPlugin: JobsPlugin) {}
    fun onDisable() {}
    fun onLoad(jobsPlugin: JobsPlugin) {}
    fun onTick(person: Person, jobsPlugin: JobsPlugin) {}
}