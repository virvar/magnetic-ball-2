package ru.virvar.apps.magneticBall2.levelGenerators

import java.util.HashMap
import ru.virvar.apps.magneticBallCore.*
import ru.virvar.apps.magneticBall2.MagneticBallLevel
import ru.virvar.apps.magneticBall2.blocks.*

class PreparedLevelGenerator(fieldSize: Int, blocks: List<Block>, moveBehavior: IMoveBehavior) : ILevelGenerator {
    private val fieldSize: Int
    private val blocks: List<Block>
    private val moveBehavior: IMoveBehavior

    init {
        this.fieldSize = fieldSize
        this.blocks = blocks
        this.moveBehavior = moveBehavior
    }

    override fun generate(): Level {
        val level = MagneticBallLevel(fieldSize, moveBehavior)
        val portals = HashMap<Char, PortalBlock>()
        for (block in blocks) {
            val blockCopy = block.clone()
            level.addBlock(blockCopy)
            if (blockCopy is Player) {
                level.player = blockCopy
            } else if (blockCopy is PortalBlock) {
                val portal = blockCopy
                if (portals.containsKey(portal.groupId)) {
                    portal.portalEnd = portals[portal.groupId]
                    portals[portal.groupId]!!.portalEnd = portal
                } else {
                    portals[portal.groupId] = portal
                }
            }
        }
        return level
    }
}
