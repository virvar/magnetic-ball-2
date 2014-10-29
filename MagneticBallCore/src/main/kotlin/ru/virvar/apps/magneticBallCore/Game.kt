package ru.virvar.apps.magneticBallCore

import kotlin.concurrent.thread
import kotlin.properties.Delegates

public class Game (
        public val levelGenerator: ILevelGenerator,
        public val blocksGenerator: IBlocksGenerator,
        public val turnHandler: ITurnHandler) {
    private var updateThread: Thread by Delegates.notNull()
    private var updateInterval: Long = 200

    public var level: Level? = null
        private set
    public var blocksToDraw: List<Block>? = null
        private set

    public fun start() {
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

    public fun nextLevel() {
        val level = levelGenerator.generate()
        level.score = 0
        level.gameState = ru.virvar.apps.magneticBallCore.GameState.PROCESS
        blocksGenerator.generateInitialBlocks(level)
        blocksToDraw = level.nextState(0)
        this.level = level
    }

    public fun resetLevel() {
        // todo: Implement level reset logic.
        // temp:
        nextLevel()
    }

    public fun turn(direction: Point2D) {
        turnHandler.turn(level!!, direction)
        blocksGenerator.generateBlocks(level!!)
    }

    public fun stop() {
        updateThread.interrupt()
    }
}
