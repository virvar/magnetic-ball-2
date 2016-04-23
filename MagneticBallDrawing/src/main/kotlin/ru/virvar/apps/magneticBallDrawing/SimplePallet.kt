package ru.virvar.apps.magneticBallDrawing

import java.awt.Color
import java.util.*

class SimplePallet : Pallet {
    override val fieldBrush: Color = Color.white
    override val squareBlockBrush: Color = Color(0x6495ED)
    override val playerBlockBrush: Color = Color.RED
    override val triangleBlockBrush: Color = Color.GREEN
    override val targetBlockBrush: Color = Color.CYAN
    override val pointBlockBrush: Color = Color(0xADFF2F)
    override val portalBrushes: Map<Char, Color>
    override val gridPen: Color = Color.GRAY

    init {
        portalBrushes = getPortalBrushes()
    }

    private fun getPortalBrushes(): HashMap<Char, Color> {
        return hashMapOf(
                'a' to Color(0x9932CC),
                'd' to Color(0xFF1493),
                'b' to Color(0xC71585),
                'c' to Color(0xFF00FF),
                'e' to Color(0xCC00CC),
                'f' to Color(0x9955BB))
    }
}
