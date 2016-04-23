package ru.virvar.apps.magneticBall2.blocksGenerators

import ru.virvar.apps.magneticBall2.blocks.SquareBlock
import ru.virvar.apps.magneticBallCore.IBlocksGenerator
import ru.virvar.apps.magneticBallCore.Level

class SimpleBlocksGenerator : IBlocksGenerator {
    val initBlocksCount: Int
    val blocksCountPerTurn: Int

    init {
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
