package ru.virvar.apps.magneticBallCore

import java.util.HashMap

class LevelState {
    val blocks = HashMap<Int, Block>()

    internal fun addBlock(block: Block) {
        assert (!blocks.values().any({ b -> b.location == block.location }), "The cell to add is not empty.")
        assert(!blocks.containsKey(block.id), "There already is block with that Id.")
        blocks.put(block.id, block)
    }

    internal fun removeBlock(blockId: Int) {
        assert(blocks.containsKey(blockId), "There is no block with id to remove.")
        blocks.remove(blockId)
    }

    internal fun moveBlock(blockId: Int, newPosition: Point2D) {
        val block: Block = blocks[blockId]!!
        block.x = newPosition.x
        block.y = newPosition.y
    }

    internal fun getBlocks(): List<Block> {
        return blocks.values().toList()
    }
}
