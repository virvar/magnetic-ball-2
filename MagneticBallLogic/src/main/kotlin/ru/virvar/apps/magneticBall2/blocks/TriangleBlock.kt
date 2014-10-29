package ru.virvar.apps.magneticBall2.blocks

import ru.virvar.apps.magneticBallCore.*
import ru.virvar.apps.magneticBall2.blocks.TriangleBlock.ReflectionDirection

public class TriangleBlock(direction: ReflectionDirection) : SquareBlock() {
    public var reflectionDirection: ReflectionDirection
        private set

    {
        $reflectionDirection = direction
    }

    override fun blockEnter(level: Level, block: Block, direction: Point2D): Point2D {
        // There must be free cells or another triangles on both sides of this triangle,
        // else this shouldn't be triangle. Consequence: can move from triangle at all directions.
        // There must not be cycles.
        super<SquareBlock>.blockEnter(level, block, direction)
        val newDirection = Point2D(0, 0)
        when (reflectionDirection) {
            ReflectionDirection.UP_RIGHT -> {
                if (direction.x == -1) {
                    newDirection.y = -1;
                } else if (direction.y == 1) {
                    newDirection.x = 1;
                }
            }
            ReflectionDirection.BOTTOM_RIGHT -> {
                if (direction.x == -1) {
                    newDirection.y = 1;
                } else if (direction.y == -1) {
                    newDirection.x = 1;
                }
            }
            ReflectionDirection.BOTTOM_LEFT ->
                if (direction.x == 1) {
                    newDirection.y = 1;
                } else if (direction.y == -1) {
                    newDirection.x = -1;
                }
            ReflectionDirection.UP_LEFT ->
                if (direction.x == 1) {
                    newDirection.y = -1;
                } else if (direction.y == 1) {
                    newDirection.x = -1;
                }
        }
        if (!newDirection.isEmpty()) {
            val newPosition = Point2D()
            newPosition.x = x
            newPosition.y = y
            level.moveHelper.move(level, block, direction, newPosition)
        }
        return newDirection
    }

    override fun clone(): Block {
        val block = TriangleBlock(reflectionDirection)
        block.initFrom(this)
        return block
    }

    override fun initFrom(original: Block) {
        super<SquareBlock>.initFrom(original)
        if (original !is TriangleBlock) {
            throw TypeCastException("Original must be TriangleBlock")
        }
        this.reflectionDirection = original.reflectionDirection
    }

    public enum class ReflectionDirection {
        UP_RIGHT
        BOTTOM_RIGHT
        BOTTOM_LEFT
        UP_LEFT
    }
}
