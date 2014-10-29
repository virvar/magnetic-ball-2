package ru.virvar.apps.magneticBall2.turnHandlers

import ru.virvar.apps.magneticBallCore.*
import ru.virvar.apps.magneticBall2.blocks.ActiveBlock
import ru.virvar.apps.magneticBall2.MagneticBallLevel

public class ImpulseTurnHandler : ITurnHandler {
    private var handleBlockCallDepth: Int = 0
    private val handleBlockCallDepthMax = 100

    override fun turn(level: Level, direction: Point2D) {
        val player = (level as MagneticBallLevel).player
        handleBlockCallDepth = 0
        handleBlock(level, player, direction)
        if (!level.blocks.containsKey(player.id)) {
            level.gameState = GameState.LOOSE
        }
    }

    private fun handleBlock(level: Level, block: Block, direction: Point2D) {
        handleBlockCallDepth++
        if (handleBlockCallDepth > handleBlockCallDepthMax) {
            return
        }
        val newPosition = findFirstBlock(level, block, direction)
        val firstBlock = if (level.isOutOfField(newPosition)) null else level.getBlock(newPosition.x, newPosition.y)as ActiveBlock?
        if (firstBlock != null) {
            val newDirection = firstBlock.blockEnter(level, block, direction)
            if (!newDirection.isEmpty()) {
                handleBlock(level, block, newDirection)
            }
        } else {
            // go out of field
            level.moveHelper.move(level, block, direction, newPosition)
        }
    }

    private fun findFirstBlock(level: Level, from: Block, direction: Point2D): Point2D {
        val newPosition = Point2D(from.x, from.y)
        while (newPosition.x >= 0 &&
                newPosition.y >= 0 &&
                newPosition.x < level.fieldSize &&
                newPosition.y < level.fieldSize &&
                ((level.getBlock(newPosition.x, newPosition.y) == null) ||
                        (level.getBlock(newPosition.x, newPosition.y) == from))) {
            newPosition.x += direction.x
            newPosition.y += direction.y
        }
        return newPosition
    }
}
