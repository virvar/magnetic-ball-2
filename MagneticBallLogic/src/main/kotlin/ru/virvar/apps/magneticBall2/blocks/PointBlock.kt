package ru.virvar.apps.magneticBall2.blocks

import ru.virvar.apps.magneticBallCore.Block
import ru.virvar.apps.magneticBallCore.Level
import ru.virvar.apps.magneticBallCore.Point2D

class PointBlock(scoreBonus: Int = 1) : ActiveBlock() {
    private var scoreBonus = 1

    init {
        this.scoreBonus = scoreBonus
    }

    override fun blockEnter(level: Level, block: Block, direction: Point2D): Point2D {
        level.moveHelper.move(level, block, direction, location)
        level.removeBlock(this)
        level.score += scoreBonus
        return direction
    }

    override fun clone(): Block {
        val block = PointBlock()
        block.initFrom(this)
        return block
    }

    override fun initFrom(original: Block) {
        super.initFrom(original)
        if (original !is PointBlock) {
            throw TypeCastException("Original must be PointBlock")
        }
        scoreBonus = original.scoreBonus
    }
}
