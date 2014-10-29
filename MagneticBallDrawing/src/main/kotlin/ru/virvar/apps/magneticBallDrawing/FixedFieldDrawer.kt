package ru.virvar.apps.magneticBallDrawing

import java.awt.Graphics
import java.awt.Point
import java.awt.Polygon
import ru.virvar.apps.magneticBallCore.Point2D
import ru.virvar.apps.magneticBallCore.Block
import ru.virvar.apps.magneticBall2.blocks.Player
import ru.virvar.apps.magneticBall2.blocks.TriangleBlock
import ru.virvar.apps.magneticBall2.blocks.SquareBlock
import ru.virvar.apps.magneticBall2.blocks.TargetBlock
import ru.virvar.apps.magneticBall2.blocks.PortalBlock
import ru.virvar.apps.magneticBall2.blocks.PointBlock

public class FixedFieldDrawer(val pallet: Pallet, val isGridVisible: Boolean) : Drawer {
    protected var offsetX: Int
        private set
    protected var offsetY: Int
        private set
    protected var blockWidth: Int
        private set
    protected var blockHeight: Int
        private set

    {
        $offsetX = 0
        $offsetY = 0
        $blockWidth = 0
        $blockHeight = 0
    }

    override fun draw(g: Graphics, width: Int, height: Int, fieldSize: Point2D, blocks: List<Block>) {
        calcOffsetAndBlockSize(width, height, fieldSize)
        g.setColor(pallet.fieldBrush)
        g.fillRect(offsetX, offsetY, width - offsetX * 2, height - offsetY * 2)
        if (isGridVisible) {
            drawGrid(g, width, height, fieldSize)
        }
        for (block in blocks) {
            drawBlock(g, block)
        }
    }

    private fun drawGrid(g: Graphics, width: Int, height: Int, fieldSize: Point2D) {
        g.setColor(pallet.gridPen)
        for (col in 0..fieldSize.x - 1) {
            g.drawLine(offsetX + blockWidth * col, offsetY, offsetX + blockWidth * col, height - offsetY)
        }
        for (row in 0..fieldSize.y - 1) {
            g.drawLine(offsetX, offsetY + blockHeight * row, width - offsetX, offsetY + blockHeight * row)
        }
    }


    protected fun drawBlock(g: Graphics, block: Block) {
        when (block) {
            is Player -> {
                g.setColor(pallet.playerBlockBrush)
                g.fillRect(offsetX + blockWidth * block.x + 1, offsetY + blockHeight * block.y + 1, blockWidth - 2, blockHeight - 2)
            }
            is TriangleBlock -> {
                var direction = (block as TriangleBlock).reflectionDirection
                val points = arrayListOf(
                        Point(offsetX + blockWidth * block.x + 1, offsetY + blockHeight * block.y + 1),
                        Point(offsetX + blockWidth * block.x + 1 + blockWidth - 2, offsetY + blockHeight * block.y + 1),
                        Point(offsetX + blockWidth * block.x + 1 + blockWidth - 2, offsetY + blockHeight * block.y + 1 + blockHeight - 2),
                        Point(offsetX + blockWidth * block.x + 1, offsetY + blockHeight * block.y + 1 + blockHeight - 2))
                when (direction) {
                    TriangleBlock.ReflectionDirection.UP_RIGHT ->
                        points.remove(1)
                    TriangleBlock.ReflectionDirection.BOTTOM_RIGHT ->
                        points.remove(2)
                    TriangleBlock.ReflectionDirection.BOTTOM_LEFT ->
                        points.remove(3)
                    TriangleBlock.ReflectionDirection.UP_LEFT ->
                        points.remove(0)
                }
                var polygon = Polygon()
                for (point in points) {
                    polygon.addPoint(point.x, point.y)
                }
                g.setColor(pallet.triangleBlockBrush)
                g.fillPolygon(polygon)
            }
            is SquareBlock -> {
                g.setColor(pallet.squareBlockBrush)
                g.fillRect(offsetX + blockWidth * block.x + 1, offsetY + blockHeight * block.y + 1, blockWidth - 2, blockHeight - 2)
            }
            is TargetBlock -> {
                g.setColor(pallet.targetBlockBrush)
                g.fillRect(offsetX + blockWidth * block.x + 1, offsetY + blockHeight * block.y + 1, blockWidth - 2, blockHeight - 2)
            }
            is PortalBlock -> {
                val brush = pallet.portalBrushes[(block as PortalBlock).groupId]
                g.setColor(brush)
                g.fillRect(offsetX + blockWidth * block.x + 1, offsetY + blockHeight * block.y + 1, blockWidth - 2, blockHeight - 2)
            }
            is PointBlock -> {
                g.setColor(pallet.pointBlockBrush)
                g.fillRect(offsetX + blockWidth * block.x + 2, offsetY + blockHeight * block.y + 2, blockWidth - 4, blockHeight - 4)
            }
        }
    }

    protected fun calcOffsetAndBlockSize(width: Int, height: Int, fieldSize: Point2D) {
        val diff: Float = (width.toFloat() / fieldSize.x - height.toFloat() / fieldSize.y).toFloat()
        if (diff > 0) {
            offsetX = (diff * fieldSize.x / 2).toInt()
            blockWidth = (height.toFloat() / fieldSize.y).toInt()
            blockHeight = blockWidth
        } else {
            offsetY = (-diff * fieldSize.y / 2).toInt()
            blockWidth = (width.toFloat() / fieldSize.x).toInt()
            blockHeight = blockWidth
        }
    }
}
