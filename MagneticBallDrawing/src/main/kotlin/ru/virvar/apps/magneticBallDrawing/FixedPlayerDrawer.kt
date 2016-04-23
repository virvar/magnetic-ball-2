package ru.virvar.apps.magneticBallDrawing

import ru.virvar.apps.magneticBall2.blocks.*
import ru.virvar.apps.magneticBallCore.Block
import ru.virvar.apps.magneticBallCore.Point2D
import java.awt.*

class FixedPlayerDrawer(val pallet: Pallet, val isPointersVisible: Boolean) : Drawer {
    private val pointerArcAngleInDegrees = 10
    private val visibleFieldSize = Point2D(5, 5)

    protected var offsetX: Int
        private set
    protected var offsetY: Int
        private set
    protected var blockWidth: Int
        private set
    protected var blockHeight: Int
        private set

    init {
        offsetX = 0
        offsetY = 0
        blockWidth = 0
        blockHeight = 0
    }

    override fun draw(g: Graphics, width: Int, height: Int, fieldSize: Point2D, blocks: List<Block>) {
        calcOffsetAndBlockSize(width, height)
        g.color = pallet.fieldBrush
        g.fillRect(offsetX, offsetY, width - offsetX * 2, height - offsetY * 2)
        val playerLocation = blocks.firstOrNull() { block -> block is Player }?.location
        if (playerLocation != null) {
            for (block in blocks) {
                drawBlock(g, block, playerLocation, fieldSize)
            }
            if (isPointersVisible) {
                blocks.forEach { block ->
                    if ((block is PointBlock) || (block is TargetBlock))
                        drawPointerTo(g, block, playerLocation, fieldSize)
                }
            }
        }
    }

    private fun drawBlock(g: Graphics, block: Block, playerLocation: Point2D, fieldSize: Point2D) {
        var blockX = block.x - playerLocation.x + (visibleFieldSize.x - 1) / 2
        blockX += if (blockX < 0) fieldSize.x else if (blockX >= fieldSize.x) -fieldSize.x else 0
        var blockY = block.y - playerLocation.y + (visibleFieldSize.y - 1) / 2
        blockY += if (blockY < 0) fieldSize.y else if (blockY >= fieldSize.y) -fieldSize.y else 0
        when (block) {
            is Player -> {
                g.color = pallet.playerBlockBrush
                g.fillRect(offsetX + blockWidth * blockX + 1, offsetY + blockHeight * blockY + 1, blockWidth - 2, blockHeight - 2)
            }
            is TriangleBlock -> {
                var direction = block.reflectionDirection
                val points = arrayListOf(
                        Point(offsetX + blockWidth * blockX + 1, offsetY + blockHeight * blockY + 1),
                        Point(offsetX + blockWidth * blockX + 1 + blockWidth - 2, offsetY + blockHeight * blockY + 1),
                        Point(offsetX + blockWidth * blockX + 1 + blockWidth - 2, offsetY + blockHeight * blockY + 1 + blockHeight - 2),
                        Point(offsetX + blockWidth * blockX + 1, offsetY + blockHeight * blockY + 1 + blockHeight - 2))
                when (direction) {
                    TriangleBlock.ReflectionDirection.UP_RIGHT ->
                        points.removeAt(1)
                    TriangleBlock.ReflectionDirection.BOTTOM_RIGHT ->
                        points.removeAt(2)
                    TriangleBlock.ReflectionDirection.BOTTOM_LEFT ->
                        points.removeAt(3)
                    TriangleBlock.ReflectionDirection.UP_LEFT ->
                        points.removeAt(0)
                }
                var polygon = Polygon()
                for (point in points) {
                    polygon.addPoint(point.x, point.y)
                }
                g.color = pallet.triangleBlockBrush
                g.fillPolygon(polygon)
            }
            is SquareBlock -> {
                g.color = pallet.squareBlockBrush
                g.fillRect(offsetX + blockWidth * blockX + 1, offsetY + blockHeight * blockY + 1, blockWidth - 2, blockHeight - 2)
            }
            is TargetBlock -> {
                g.color = pallet.targetBlockBrush
                g.fillRect(offsetX + blockWidth * blockX + 1, offsetY + blockHeight * blockY + 1, blockWidth - 2, blockHeight - 2)
            }
            is PortalBlock -> {
                val brush = pallet.portalBrushes[block.groupId]
                g.color = brush
                g.fillRect(offsetX + blockWidth * blockX + 1, offsetY + blockHeight * blockY + 1, blockWidth - 2, blockHeight - 2)
            }
            is PointBlock -> {
                g.color = pallet.pointBlockBrush
                g.fillRect(offsetX + blockWidth * blockX + 2, offsetY + blockHeight * blockY + 2, blockWidth - 4, blockHeight - 4)
            }
        }
    }

    private fun drawPointerTo(g: Graphics, block: Block, playerLocation: Point2D, fieldSize: Point2D) {
        val g2 = g as Graphics2D
        var blockX = block.x - playerLocation.x + (visibleFieldSize.x - 1) / 2
        blockX += if (blockX < 0) fieldSize.x else if (blockX >= fieldSize.x) -fieldSize.x else 0
        var blockY = block.y - playerLocation.y + (visibleFieldSize.y - 1) / 2
        blockY += if (blockY < 0) fieldSize.y else if (blockY >= fieldSize.y) -fieldSize.y else 0
        var color: Color
        when (block) {
            is TargetBlock ->
                color = pallet.targetBlockBrush
            is PointBlock ->
                color = pallet.pointBlockBrush
            else ->
                throw Exception("Unexpected block type")
        }
        g2.color = Color(color.getRed(), color.getGreen(), color.getBlue(), 100)
        g2.stroke = BasicStroke(30F)
        val blockCenter = Point2D((offsetX + blockWidth * (blockX + 0.5F)).toInt(),
                (offsetY + blockHeight * (blockY + 0.5F)).toInt())
        val center = Point2D((offsetX + blockWidth * (visibleFieldSize.x.toFloat() / 2)).toInt(),
                (offsetY + blockHeight * (visibleFieldSize.y.toFloat() / 2)).toInt())
        val angleCenter = Math.toDegrees(Math.atan2((center.y - blockCenter.y).toDouble(),
                (blockCenter.x - center.x).toDouble()))
        val startAngle = (angleCenter - pointerArcAngleInDegrees / 2).toInt()
        val ovalSize = Point2D(blockWidth * visibleFieldSize.x / 3, blockHeight * visibleFieldSize.y / 3)
        g2.drawArc(center.x - ovalSize.x,
                center.y - ovalSize.y,
                ovalSize.x * 2,
                ovalSize.y * 2,
                startAngle,
                pointerArcAngleInDegrees
        )
    }

    private fun calcOffsetAndBlockSize(width: Int, height: Int) {
        val fieldSize = visibleFieldSize
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
