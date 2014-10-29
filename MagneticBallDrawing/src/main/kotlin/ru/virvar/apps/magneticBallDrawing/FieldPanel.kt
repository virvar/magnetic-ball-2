package ru.virvar.apps.magneticBallDrawing

import javax.swing.JPanel
import java.awt.Graphics
import ru.virvar.apps.magneticBallCore.*

public abstract class FieldPanel(val drawer: Drawer) : JPanel() {
    protected abstract val fieldSize: Point2D
    protected abstract val blocks: List<Block>

    {
        setDoubleBuffered(true)
    }

    override fun paint(g: Graphics) {
        super<JPanel>.paint(g)
        drawer.draw(g, getWidth(), getHeight(), fieldSize, blocks)
    }
}
