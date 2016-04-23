package ru.virvar.apps.magneticBallCore

interface IMoveHelper {
    fun move(level: Level, block: ru.virvar.apps.magneticBallCore.Block, direction: Point2D, newPosition: Point2D)
}
