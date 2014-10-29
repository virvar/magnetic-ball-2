package ru.virvar.apps.magneticBall2.blocks

import ru.virvar.apps.magneticBallCore.Block
import ru.virvar.apps.magneticBallCore.Point2D

public class Player : Block() {
    // not used yet
    public var direction: ru.virvar.apps.magneticBallCore.Point2D

    {
        direction = Point2D(0, 0)
    }

    override fun clone(): Block {
        val block = Player()
        block.initFrom(this)
        return block
    }

    override fun initFrom(original: Block) {
        super<Block>.initFrom(original)
        if (original !is Player) {
            throw TypeCastException("Original must be Player")
        }
        direction = ru.virvar.apps.magneticBallCore.Point2D(original.direction.x, original.direction.y)
    }
}
