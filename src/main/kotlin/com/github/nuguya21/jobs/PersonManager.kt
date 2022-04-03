package com.github.nuguya21.jobs

import com.github.nuguya21.jobs.api.Person
import org.bukkit.entity.Player

class PersonManager {
    internal companion object {

        private val player: MutableSet<Player> = mutableSetOf()
        private val person: MutableMap<Player, Person> = mutableMapOf()

        fun getPerson(player: Player): Person {
            if (!exists(player)) {
                val person = UserPerson(player)
                this.player.add(player)
                this.person[player] = person
            }
            return this.person[player]!!
        }

        private fun exists(player: Player): Boolean {
            return player in this.player
        }

        private fun exists(person: UserPerson): Boolean {
            return exists(person.player)
        }
    }
}