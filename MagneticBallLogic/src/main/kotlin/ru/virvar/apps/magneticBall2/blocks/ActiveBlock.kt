package ru.virvar.apps.magneticBall2.blocks

import ru.virvar.apps.magneticBallCore.*

abstract class ActiveBlock : Block() {
    abstract fun blockEnter(level: Level, block: Block, direction: Point2D): Point2D
}
