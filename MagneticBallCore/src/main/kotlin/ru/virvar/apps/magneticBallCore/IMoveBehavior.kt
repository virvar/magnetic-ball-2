package ru.virvar.apps.magneticBallCore

interface IMoveBehavior {
    fun getNextPosition(level: Level, currentPosition: Point2D, direction: Point2D): Point2D
}
