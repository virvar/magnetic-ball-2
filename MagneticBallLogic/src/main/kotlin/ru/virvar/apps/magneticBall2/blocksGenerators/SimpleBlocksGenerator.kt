package ru.virvar.apps.magneticBall2.blocksGenerators

import java.util.Random
import ru.virvar.apps.magneticBallCore.*
import ru.virvar.apps.magneticBall2.blocks.SquareBlock

public class SimpleBlocksGenerator : IBlocksGenerator {
    private class object {
        val random = Random()
    }

    public val initBlocksCount: Int
    public val blocksCountPerTurn: Int

    {
        initBlocksCount = 10
        blocksCountPerTurn = 1
    }

    override fun generateInitialBlocks(level: Level) {
        generateBlocks(level, initBlocksCount)
    }

    override fun generateBlocks(level: Level) {
        generateBlocks(level, blocksCountPerTurn)
    }

    private fun generateBlocks(level: Level, count: Int) {
        for (i in 0..count - 1) {
            if (level.freeCells.size != 0) {
                val block = SquareBlock()
                val positionIndex = random.nextInt(level.freeCells.size)
                val position = level.freeCells[positionIndex]
                block.x = position % level.fieldSize
                block.y = Math.floor((position.toDouble() / level.fieldSize)).toInt()
                level.addBlock(block)
            }
        }
    }
}
