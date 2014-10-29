package ru.virvar.apps.magneticBall2.blocks

import ru.virvar.apps.magneticBallCore.*

public open class SquareBlock : ActiveBlock() {
    override fun blockEnter(level: Level, block: Block, direction: Point2D): Point2D {
        var newPosition = Point2D(x, y)
        val reverseDirection = Point2D(-direction.x, -direction.y)
        newPosition = level.moveBehavior.getNextPosition(level, newPosition, reverseDirection)
        level.moveHelper.move(level, block, direction, newPosition)
        return Point2D(0, 0)
    }

    override fun clone(): Block {
        val block = SquareBlock()
        block.initFrom(this)
        return block
    }

    override fun initFrom(original: Block) {
        super<ActiveBlock>.initFrom(original)
        if (original !is SquareBlock) {
            throw TypeCastException("Original must be SquareBlock")
        }
    }
}
