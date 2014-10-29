package ru.virvar.apps.magneticBall2.blocks

import ru.virvar.apps.magneticBallCore.*

public class PointBlock(scoreBonus: Int = 1) : ActiveBlock() {
    private var scoreBonus = 1

    {
        this.scoreBonus = scoreBonus
    }

    public override fun blockEnter(level: Level, block: Block, direction: Point2D): Point2D {
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
        super<ActiveBlock>.initFrom(original)
        if (original !is PointBlock) {
            throw TypeCastException("Original must be PointBlock")
        }
        scoreBonus = original.scoreBonus
    }
}
