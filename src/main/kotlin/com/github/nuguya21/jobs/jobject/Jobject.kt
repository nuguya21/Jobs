package com.github.nuguya21.jobs.jobject

import com.github.nuguya21.jobs.JobsPlugin
import com.github.nuguya21.jobs.util.GravityUtil
import org.bukkit.Location

interface Jobject {
    val plugin: JobsPlugin
    var location: Location
    var gravity: GravityUtil?
    var remove: Boolean
}