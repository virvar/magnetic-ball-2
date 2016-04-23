package ru.virvar.apps.magneticBall2App

import ru.virvar.apps.magneticBallDrawing.FieldPanel
import ru.virvar.apps.magneticBallCore.Point2D
import ru.virvar.apps.magneticBallCore.Block
import ru.virvar.apps.magneticBallCore.Game
import java.util.ArrayList
import ru.virvar.apps.magneticBallDrawing.Drawer

class GamePanel(drawer: Drawer) : FieldPanel(drawer) {
    var game: Game? = null
    override val fieldSize: Point2D
        get() {
            val fieldSize = game?.level?.fieldSize
            if (fieldSize != null) {
                return Point2D(fieldSize, fieldSize)
            }
            return Point2D(5, 5)
        }
    override val blocks: List<Block>
        get() {
            val blocksToDraw = game?.blocksToDraw
            if (blocksToDraw != null) {
                return blocksToDraw
            }
            return ArrayList()
        }
}
