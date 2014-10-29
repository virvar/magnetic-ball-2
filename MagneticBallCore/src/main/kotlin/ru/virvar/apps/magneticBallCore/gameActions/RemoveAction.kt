package ru.virvar.apps.magneticBallCore.gameActions

import ru.virvar.apps.magneticBallCore.GameAction
import ru.virvar.apps.magneticBallCore.LevelState
import ru.virvar.apps.magneticBallCore.Block

class RemoveAction(block: Block) : GameAction(block.id) {
    override val delay: Long = 100

    override fun apply(levelState: LevelState) {
        levelState.removeBlock(blockId)
    }
}
