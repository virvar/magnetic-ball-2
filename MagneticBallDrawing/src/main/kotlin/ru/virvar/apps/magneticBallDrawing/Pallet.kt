package ru.virvar.apps.magneticBallDrawing

import java.awt.Color

interface Pallet {
    val fieldBrush: Color
    val squareBlockBrush: Color
    val playerBlockBrush: Color
    val triangleBlockBrush: Color
    val targetBlockBrush: Color
    val pointBlockBrush: Color
    val portalBrushes: Map<Char, Color>
    val gridPen: Color
}
