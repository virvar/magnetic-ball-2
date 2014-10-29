package ru.virvar.apps.magneticBallCore

import java.util.LinkedList
import java.util.HashMap
import java.util.ArrayList

public class Field(val fieldSize: Int) {
    private val field: Array<Array<LinkedList<Block>?>>
    public val blocks: HashMap<Int, Block>
    public val freeCells: ArrayList<Int>

    {
        field = Array(fieldSize) { Array<LinkedList<Block>?>(fieldSize) { null } }
        blocks = HashMap<Int, Block>()
        val cellsCount: Int = fieldSize * fieldSize
        freeCells = ArrayList<Int>(cellsCount)
        for (i in 0..cellsCount - 1) {
            freeCells.add(i)
        }
    }

    public fun getBlock(x: Int, y: Int, depth: Int): Block? {
        var block: Block? = null
        val cell = field[x][y]
        if (cell != null) {
            if (cell.size > depth) {
                block = cell.elementAt(depth)
            }
        }
        return block
    }

    public fun isOutOfField(point: Point2D): Boolean {
        return (point.x < 0 ||
                point.y < 0 ||
                point.x >= fieldSize ||
                point.y >= fieldSize)
    }

    public fun addBlock(block: Block) {
        assert(!blocks.containsKey(block.id), "There already is a block with that Id.")
        if (field[block.x][block.y] == null) {
            field[block.x][block.y] = LinkedList<Block>()
        }
        field[block.x][block.y]!!.addFirst(block)
        removeFreeCell(block.location)
        blocks.put(block.id, block)
    }

    public fun removeBlock(block: Block) {
        if (!isOutOfField(block.location)) {
            field[block.x][block.y]!!.remove(block)
            updateFreeCell(block.location)
        }
        assert(blocks.containsKey(block.id), "There is no block with id to remove.")
        blocks.remove(block.id)
    }

    public fun moveBlock(block: Block, newPosition: Point2D) {
        field[block.x][block.y]!!.removeFirst()
        updateFreeCell(block.location)
        block.x = newPosition.x
        block.y = newPosition.y
        assert(!isOutOfField(block.location), "Block is out of field.")
        if (field[block.x][block.y] == null) {
            field[block.x][block.y] = LinkedList<Block>()
        }
        field[block.x][block.y]!!.addFirst(block)
        removeFreeCell(block.location)
    }

    private fun updateFreeCell(point: Point2D) {
        if (field[point.x][point.y]?.size == 0) {
            freeCells.add(point.y * fieldSize + point.x)
        }
    }

    private fun removeFreeCell(point: Point2D) {
        val cell: Int? = point.y * fieldSize + point.x
        freeCells.remove(cell)
    }
}
