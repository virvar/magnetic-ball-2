package ru.virvar.apps.magneticBall2.moveBehaviors

import ru.virvar.apps.magneticBallCore.*

public class SimpleMoveBehavior : IMoveBehavior {
    override fun getNextPosition(level: Level, currentPosition: Point2D, direction: Point2D): Point2D {
        currentPosition.x = currentPosition.x + direction.x
        currentPosition.y = currentPosition.y + direction.y
        return currentPosition
    }
}
