package ru.virvar.apps.magneticBallCore

import java.util.LinkedList

class LevelStateManager {
    private val actionsLocker: Any = Any()
    private val actions: LinkedList<GameAction>
    private val levelState: LevelState

    init {
        levelState = LevelState()
        actions = LinkedList<GameAction>()
    }

    fun nextState(updateInterval: Long): List<Block> {
        var action: GameAction? = null
        synchronized (actionsLocker) {
            if (actions.size != 0) {
                action = actions.removeFirst()
            }
        }
        try {
            Thread.sleep(action?.delay ?: updateInterval)
        } catch(e: Exception) {
        }
        action?.apply(levelState)
        var blocks: List<Block>? = null
        synchronized (actionsLocker) {
            blocks = levelState.getBlocks()
        }
        return blocks!!
    }

    fun addAction(action: GameAction) {
        synchronized (actionsLocker) {
            actions.add(action)
        }
    }

    fun addActions(actions: Collection<GameAction>) {
        synchronized (actionsLocker) {
            this.actions.addAll(actions)
        }
    }

    fun getActions(): List<GameAction>? {
        var actions: List<GameAction>? = null
        synchronized (actionsLocker) {
            actions = this.actions.toList()
        }
        return actions
    }
}
