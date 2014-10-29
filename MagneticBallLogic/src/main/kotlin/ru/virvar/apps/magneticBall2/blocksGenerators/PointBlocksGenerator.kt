package ru.virvar.apps.magneticBall2.blocksGenerators

import java.util.Random
import ru.virvar.apps.magneticBallCore.*
import ru.virvar.apps.magneticBall2.blocks.PointBlock

public class PointBlocksGenerator : IBlocksGenerator {
    private class object {
        val random = Random()
    }

    public val pointBlocksCountMax: Int

    {
        pointBlocksCountMax = 1
    }

    override fun generateInitialBlocks(level: Level) {
        generateBlocks(level)
    }

    override fun generateBlocks(level: Level) {
        val blockToCreateCount = pointBlocksCountMax - getPointBlocksOnFieldCount(level)
        for (i in 0..blockToCreateCount - 1) {
            if (level.freeCells.size != 0) {
                val blockPositionIndex = random.nextInt(level.freeCells.size)
                val blockPosition = level.freeCells[blockPositionIndex]
                val block = PointBlock()
                block.location = Point2D(blockPosition % level.fieldSize, blockPosition / level.fieldSize)
                level.addBlock(block)
            }
        }
    }

    private fun getPointBlocksOnFieldCount(level: Level): Int {
        var count = 0
        for (block in level.blocks.values()) {
            if (block is PointBlock) {
                count++
            }
        }
        return count
    }
}
