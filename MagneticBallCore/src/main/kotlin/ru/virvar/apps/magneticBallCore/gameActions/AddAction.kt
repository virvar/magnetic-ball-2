package ru.virvar.apps.magneticBallCore.gameActions

import ru.virvar.apps.magneticBallCore.*

class AddAction (block: Block) : GameAction(block.id) {
    override val delay: Long = 10
    private val blockCopy: Block

    {
        blockCopy = block.clone()
    }

    override fun apply(levelState: LevelState) {
        levelState.addBlock(blockCopy)
    }
}
