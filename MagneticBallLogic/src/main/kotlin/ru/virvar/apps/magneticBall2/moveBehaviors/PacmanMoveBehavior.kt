package ru.virvar.apps.magneticBall2.moveBehaviors

import ru.virvar.apps.magneticBallCore.*

class PacmanMoveBehavior : IMoveBehavior {
    override fun getNextPosition(level: Level, currentPosition: Point2D, direction: Point2D): Point2D {
        currentPosition.x = ((currentPosition.x + level.fieldSize) + direction.x) % level.fieldSize
        currentPosition.y = ((currentPosition.y + level.fieldSize) + direction.y) % level.fieldSize
        return currentPosition
    }
}
