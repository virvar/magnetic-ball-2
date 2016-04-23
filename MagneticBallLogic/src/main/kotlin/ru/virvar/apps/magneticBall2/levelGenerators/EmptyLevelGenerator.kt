package ru.virvar.apps.magneticBall2.levelGenerators

import ru.virvar.apps.magneticBallCore.*
import ru.virvar.apps.magneticBall2.MagneticBallLevel
import ru.virvar.apps.magneticBall2.blocks.Player

class EmptyLevelGenerator (moveBehavior: IMoveBehavior) : ILevelGenerator {
    private val moveBehavior: IMoveBehavior

    init {
        this.moveBehavior = moveBehavior
    }

    override fun generate(): Level {
        val level = MagneticBallLevel(7, moveBehavior)
        val player = Player()
        player.x = level.fieldSize / 2
        player.y = level.fieldSize / 2
        level.addBlock(player)
        level.player = player
        return level
    }
}
