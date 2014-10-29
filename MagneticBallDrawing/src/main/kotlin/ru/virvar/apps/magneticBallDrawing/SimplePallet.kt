package ru.virvar.apps.magneticBallDrawing

import java.awt.Color
import java.util.HashMap

public class SimplePallet : Pallet {
    public override val fieldBrush: Color = Color.white
    public override val squareBlockBrush: Color = Color(0x6495ED)
    public override val playerBlockBrush: Color = Color.RED
    public override val triangleBlockBrush: Color = Color.GREEN
    public override val targetBlockBrush: Color = Color.CYAN
    public override val pointBlockBrush: Color = Color(0xADFF2F)
    public override val portalBrushes: Map<Char, Color>
    public override val gridPen: Color = Color.GRAY

    {
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
