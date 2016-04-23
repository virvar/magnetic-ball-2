package ru.virvar.apps.magneticBall2.turnHandlers

import ru.virvar.apps.magneticBallCore.*
import ru.virvar.apps.magneticBall2.blocks.ActiveBlock
import ru.virvar.apps.magneticBall2.MagneticBallLevel

class PacmanTurnHandler : ITurnHandler {
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
        val newPosition = findFirstBlock(level, block.location, direction)
        val firstBlock = if (level.isOutOfField(newPosition)) null else getFirstActiveBlock(level, newPosition)
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

    private fun findFirstBlock(level: Level, from: Point2D, direction: Point2D): Point2D {
        var newPosition = Point2D(from.x, from.y)
        do {
            newPosition = level.moveBehavior.getNextPosition(level, newPosition, direction)
        } while ((newPosition != from) &&
                (level.getBlock(newPosition.x, newPosition.y) == null))
        return newPosition
    }

    private fun getFirstActiveBlock(level: Level, position: Point2D): ActiveBlock? {
        var block: Block?
        var depth = 0
        do {
            block = level.getBlock(position.x, position.y, depth)
            depth++
        } while ((block != null) && (block !is ActiveBlock))
        return block as ActiveBlock?
    }
}
