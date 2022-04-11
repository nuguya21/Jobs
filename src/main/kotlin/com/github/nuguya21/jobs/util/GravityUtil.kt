package com.github.nuguya21.jobs.util

import org.bukkit.Location


// m = m/s
class GravityUtil {

    constructor(origin: Location, direction: Direction, m: Double) {
        this.origin = origin
        this.now = origin
        this.direction = direction
        this.m = m
        g = 9.8
        this.nowG = g
    }

    constructor(origin: Location, direction: Direction, m: Double, g: Double) {
        this.origin = origin
        this.now = origin
        this.direction = direction
        this.m = m
        this.g = g
        this.nowG = g
    }

    private val origin: Location
    private var now: Location
    private var g: Double
    private var direction: Direction
    private var m: Double
    private var nowG: Double

    fun nextTick(): Location {
        if (!this.now.toCenterLocation().clone().subtract(0.0,0.5,0.0).block.isSolid) {
            this.now.add(this.direction.toVector().multiply(m / 20))
            this.now.subtract(0.0, nowG / 20, 0.0)
            this.nowG += g / 20
        } else {
            this.nowG = g
        }
        return this.now
    }
}