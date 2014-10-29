package ru.virvar.apps.magneticBall2

import ru.virvar.apps.magneticBallCore.*
import ru.virvar.apps.magneticBall2.helpers.SmoothMoving
import kotlin.properties.Delegates

class MagneticBallLevel (fieldSize: Int, moveBehavior: IMoveBehavior)
: Level(fieldSize, moveBehavior, SmoothMoving()) {
    public var player: Block by Delegates.notNull()
}
