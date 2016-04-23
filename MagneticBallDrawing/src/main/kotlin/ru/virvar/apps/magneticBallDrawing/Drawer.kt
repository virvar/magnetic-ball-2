package ru.virvar.apps.magneticBallDrawing

import java.awt.Graphics
import ru.virvar.apps.magneticBallCore.Point2D
import ru.virvar.apps.magneticBallCore.Block

interface Drawer {
    fun draw(g: Graphics, width: Int, height: Int, fieldSize: Point2D, blocks: List<Block>)
}
