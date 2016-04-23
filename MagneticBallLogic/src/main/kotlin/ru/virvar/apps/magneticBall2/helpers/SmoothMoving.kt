package ru.virvar.apps.magneticBall2.helpers

import ru.virvar.apps.magneticBallCore.*

class SmoothMoving : IMoveHelper {
    override fun move(level: Level, block: Block, direction: Point2D, newPosition: Point2D) {
        val prevPos = Point2D(block.x, block.y)
        val nextPos = Point2D(block.x, block.y)
        while (prevPos != newPosition) {
            level.moveBehavior.getNextPosition(level, nextPos, direction)
            if (!level.isOutOfField(nextPos)) {
                level.moveBlock(block, nextPos)
            } else {
                level.removeBlock(block)
            }
            prevPos.x = nextPos.x
            prevPos.y = nextPos.y
        }
    }
}
