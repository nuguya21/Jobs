package com.github.nuguya21.jobs.api

import org.bukkit.entity.Player

interface Person {

    val player: Player
    var job: Job?
}