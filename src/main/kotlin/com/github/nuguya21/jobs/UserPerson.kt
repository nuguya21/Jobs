package com.github.nuguya21.jobs

import com.github.nuguya21.jobs.api.Job
import com.github.nuguya21.jobs.api.Person
import org.bukkit.entity.Player

class UserPerson(
    override val player: Player
): Person {

    override var job: Job? = null
}