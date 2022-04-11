package com.github.nuguya21.jobs.util

import org.bukkit.util.Vector
import kotlin.math.cos
import kotlin.math.sin

class Direction(var yaw: Float, var pitch: Float) {
    fun toVector(): Vector {
        val vector = Vector()

        val rotX: Double = this.yaw.toDouble()
        val rotY: Double = this.pitch.toDouble()

        vector.y = -sin(Math.toRadians(rotY))

        val xz = cos(Math.toRadians(rotY))

        vector.x = -xz * sin(Math.toRadians(rotX))
        vector.z = xz * cos(Math.toRadians(rotX))

        return vector
    }
}