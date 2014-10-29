package ru.virvar.apps.magneticBall2.blocks

import ru.virvar.apps.magneticBallCore.*

public abstract class ActiveBlock : Block() {
    public abstract fun blockEnter(level: Level, block: Block, direction: Point2D): Point2D
}
