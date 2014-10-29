package ru.virvar.apps.magneticBall2.blocks

import ru.virvar.apps.magneticBallCore.*

public class PortalBlock(groupId: Char) : ActiveBlock() {
    public val groupId: Char
    public var portalEnd: PortalBlock? = null

    {
        if (groupId >= 'a' && groupId <= 'z') {
            $groupId = groupId
        } else {
            throw Exception("groupId must be between 'a' and 'z'.")
        }
    }

    override fun blockEnter(level: Level, block: Block, direction: Point2D): Point2D {
        level.moveHelper.move(level, block, direction, location)
        level.moveBlock(block, portalEnd!!.location)
        return direction
    }

    override fun clone(): Block {
        val block = PortalBlock(this.groupId)
        block.initFrom(this)
        return block
    }

    override fun initFrom(original: Block) {
        super<ActiveBlock>.initFrom(original)
        if (original !is PortalBlock) {
            throw TypeCastException("Original must be PortalBlock")
        }
    }
}
