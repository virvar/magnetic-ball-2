package ru.virvar.apps.magneticBall2.blocksGenerators

import ru.virvar.apps.magneticBall2.MagneticBallLevel
import ru.virvar.apps.magneticBall2.blocks.SquareBlock
import ru.virvar.apps.magneticBall2.blocks.TriangleBlock
import ru.virvar.apps.magneticBallCore.Block
import ru.virvar.apps.magneticBallCore.IBlocksGenerator
import ru.virvar.apps.magneticBallCore.Level
import ru.virvar.apps.magneticBallCore.Point2D
import java.util.*

class OnLineBlocksGenerator(val blocksCount: Int = 20) : IBlocksGenerator {
    override fun generateInitialBlocks(level: Level) {
        generateBlocks(level, blocksCount)
    }

    private fun generateBlocks(level: Level, count: Int) {
        for (i in 0..count - 1) {
            if (level.freeCells.size != 0) {
                val block = createRandomBlock()
                val positionIndex = random.nextInt(level.freeCells.size)
                val position = level.freeCells[positionIndex]
                block.x = position % level.fieldSize
                block.y = Math.floor((position.toDouble() / level.fieldSize)).toInt()
                level.addBlock(block)
            }
        }
    }

    override fun generateBlocks(level: Level) {
        val onLineFreeCells = getOnLineFreeCells(level)
        while (level.blocks.size <= blocksCount) {
            if (onLineFreeCells.size == 0) {
                generateBlocks(level, blocksCount - level.blocks.size + 1)
            } else {
                val block = createRandomBlock()
                val positionIndex = random.nextInt(onLineFreeCells.size)
                val position = onLineFreeCells[positionIndex]
                onLineFreeCells.remove(position)
                block.x = position.x
                block.y = position.y
                level.addBlock(block)
            }
        }
    }

    private fun getOnLineFreeCells(level: Level): LinkedList<Point2D> {
        val player = (level as MagneticBallLevel).player
        val onLineFreeCells = LinkedList<Point2D>()
        for (i in 0..level.fieldSize - 1) {
            if (level.getBlock(player.x, i) == null) {
                onLineFreeCells.add(Point2D(player.x, i))
            }
        }
        for (i in 0..level.fieldSize - 1) {
            if (level.getBlock(i, player.y) == null) {
                onLineFreeCells.add(Point2D(i, player.y))
            }
        }
        return onLineFreeCells
    }

    private fun createRandomBlock(): Block {
        var block: Block? = null
        var blockType = random.nextInt(2)
        when (blockType) {
            0 ->
                block = SquareBlock()
            1 -> {
                val direction = random.nextInt(4)
                block = TriangleBlock(TriangleBlock.ReflectionDirection.values().get(direction))
            }
        }
        return block!!
    }
}
