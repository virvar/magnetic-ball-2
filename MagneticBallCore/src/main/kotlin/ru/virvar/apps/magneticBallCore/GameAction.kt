package ru.virvar.apps.magneticBallCore

abstract class GameAction(internal val blockId: Int) {
    internal abstract val delay: Long

    internal abstract fun apply(levelState: LevelState)
}
