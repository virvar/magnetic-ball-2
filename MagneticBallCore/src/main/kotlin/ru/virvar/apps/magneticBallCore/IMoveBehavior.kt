package ru.virvar.apps.magneticBallCore

public trait IMoveBehavior {
    fun getNextPosition(level: Level, currentPosition: Point2D, direction: Point2D): Point2D
}
