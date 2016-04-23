package ru.virvar.apps.magneticBallCore

import kotlin.concurrent.thread
import kotlin.properties.Delegates

class Game(
        val levelGenerator: ILevelGenerator,
        val blocksGenerator: IBlocksGenerator,
        val turnHandler: ITurnHandler) {
    private var updateThread: Thread by Delegates.notNull()
    private var updateInterval: Long = 200

    var level: Level? = null
        private set
    var blocksToDraw: List<Block>? = null
        private set

    fun start() {
        updateThread = thread(start = true) {
            startUpdate()
        }
    }

    private fun startUpdate() {
        while (true) {
            val level = this.level
            if (level != null) {
                blocksToDraw = level.nextState(updateInterval)
            } else {
                Thread.sleep(updateInterval)
            }
        }
    }

    fun nextLevel() {
        val level = levelGenerator.generate()
        level.score = 0
        level.gameState = ru.virvar.apps.magneticBallCore.GameState.PROCESS
        blocksGenerator.generateInitialBlocks(level)
        blocksToDraw = level.nextState(0)
        this.level = level
    }

    fun resetLevel() {
        // todo: Implement level reset logic.
        // temp:
        nextLevel()
    }

    fun turn(direction: Point2D) {
        turnHandler.turn(level!!, direction)
        blocksGenerator.generateBlocks(level!!)
    }

    fun stop() {
        updateThread.interrupt()
    }
}
