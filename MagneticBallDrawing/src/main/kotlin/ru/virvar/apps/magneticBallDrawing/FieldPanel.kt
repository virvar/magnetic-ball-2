package ru.virvar.apps.magneticBallDrawing

import ru.virvar.apps.magneticBallCore.Block
import ru.virvar.apps.magneticBallCore.Point2D
import java.awt.Graphics
import javax.swing.JPanel

abstract class FieldPanel(val drawer: Drawer) : JPanel() {
    protected abstract val fieldSize: Point2D
    protected abstract val blocks: List<Block>

    init {
        isDoubleBuffered = true
    }

    override fun paint(g: Graphics) {
        super.paint(g)
        drawer.draw(g, width, height, fieldSize, blocks)
    }
}
