package ru.virvar.apps.magneticBallDrawing

import java.awt.Color

public trait Pallet {
    public val fieldBrush: Color
    public val squareBlockBrush: Color
    public val playerBlockBrush: Color
    public val triangleBlockBrush: Color
    public val targetBlockBrush: Color
    public val pointBlockBrush: Color
    public val portalBrushes: Map<Char, Color>
    public val gridPen: Color
}
