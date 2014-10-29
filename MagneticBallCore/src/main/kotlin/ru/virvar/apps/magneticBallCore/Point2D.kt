package ru.virvar.apps.magneticBallCore

public data class Point2D(public var x: Int = 0, public var y: Int = 0) {
    public fun isEmpty(): Boolean {
        return (x == 0) && (y == 0)
    }
}
