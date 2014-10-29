package ru.virvar.apps.magneticBall2.levelGenerators

import ru.virvar.apps.magneticBallCore.*
import ru.virvar.apps.magneticBall2.MagneticBallLevel
import java.util.HashMap
import java.io.BufferedReader
import java.io.FileReader
import ru.virvar.apps.magneticBall2.blocks.*

public class FromFileLevelGenerator (fileName: String, moveBehavior: IMoveBehavior) : ILevelGenerator {
    private val fileName: String
    private val moveBehavior: IMoveBehavior

    {
        this.fileName = fileName
        this.moveBehavior = moveBehavior
    }

    override fun generate(): Level {
        val bReader = BufferedReader(FileReader(fileName))
        val lines = bReader.lines().toList()
        var lineNumber = 0
        val portals = HashMap<Char, PortalBlock>()
        val level = MagneticBallLevel(lines.first().length, moveBehavior)
        for (line in lines) {
            for (i in line.indices) {
                val cellValue = line[i]
                if (cellValue != '0') {
                    val block = createBlock(cellValue)
                    block.x = i
                    block.y = lineNumber
                    level.addBlock(block)
                    if (block is Player) {
                        level.player = block
                    } else if (block is PortalBlock) {
                        val portal = block as PortalBlock
                        if (portals.containsKey(portal.groupId)) {
                            portal.portalEnd = portals[portal.groupId]
                            portals[portal.groupId]!!.portalEnd = portal
                        } else {
                            portals[portal.groupId] = portal
                        }
                    }
                }
            }
            lineNumber++
        }
        bReader.close()
        return level
    }

    private fun createBlock(blockCode: Char): Block {
        val block: Block
        when (blockCode) {
            '1' ->
                block = Player()
            '2' ->
                block = SquareBlock()
            '3' ->
                block = TriangleBlock(TriangleBlock.ReflectionDirection.BOTTOM_LEFT)
            '4' ->
                block = TriangleBlock(TriangleBlock.ReflectionDirection.BOTTOM_RIGHT)
            '5' ->
                block = TriangleBlock(TriangleBlock.ReflectionDirection.UP_LEFT)
            '6' ->
                block = TriangleBlock(TriangleBlock.ReflectionDirection.UP_RIGHT)
            '7' ->
                block = TargetBlock()
            '8' -> // TODO: deprecated, destroy
                block = PortalBlock('a')
            in 'a'..'z' ->
                block = PortalBlock(blockCode)
            else ->
                throw IllegalArgumentException("Incorrect blockCode")
        }
        return block
    }
}
