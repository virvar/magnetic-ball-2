package ru.virvar.apps.magneticBallCore.gameActions

import ru.virvar.apps.magneticBallCore.*

class MoveAction (block: Block, newPosition: Point2D) : GameAction(block.id) {
    override val delay: Long = 100
    private val move: Point2D

    {
        move = Point2D(newPosition.x, newPosition.y)
    }

    override fun apply(levelState: LevelState) {
        levelState.moveBlock(blockId, move)
    }
}
