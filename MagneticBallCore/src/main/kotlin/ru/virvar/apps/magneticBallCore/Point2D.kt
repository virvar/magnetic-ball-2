package ru.virvar.apps.magneticBallCore

data class Point2D(var x: Int = 0, var y: Int = 0) {
    fun isEmpty(): Boolean {
        return (x == 0) && (y == 0)
    }
}
