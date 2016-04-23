package ru.virvar.apps.magneticBall2.blocks

import ru.virvar.apps.magneticBallCore.*

class TargetBlock : ActiveBlock() {
    override fun blockEnter(level: Level, block: Block, direction: Point2D): Point2D {
        level.moveHelper.move(level, block, direction, location)
        level.gameState = GameState.WIN
        return Point2D(0, 0)
    }

    override fun clone(): Block {
        val block = TargetBlock()
        block.initFrom(this)
        return block
    }

    override fun initFrom(original: Block) {
        super.initFrom(original)
        if (original !is TargetBlock) {
            throw TypeCastException("Original must be TargetBlock")
        }
    }
}
