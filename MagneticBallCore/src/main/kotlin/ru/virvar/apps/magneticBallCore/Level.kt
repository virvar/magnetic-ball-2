package ru.virvar.apps.magneticBallCore

import ru.virvar.apps.magneticBallCore.gameActions.AddAction
import ru.virvar.apps.magneticBallCore.gameActions.MoveAction
import ru.virvar.apps.magneticBallCore.gameActions.RemoveAction

open class Level(
        val fieldSize: Int,
        val moveBehavior: IMoveBehavior,
        val moveHelper: IMoveHelper) {
    private val field: Field
    private val stateManager: LevelStateManager
    var score: Int = 0
    var gameState: GameState = GameState.LOOSE
    val freeCells: List<Int>
        get () = this.field.freeCells
    val blocks: Map<Int, Block>
        get() = this.field.blocks

    init {
        field = Field(fieldSize)
        stateManager = LevelStateManager()
    }

    fun getBlock(x: Int, y: Int, depth: Int = 0): Block? = field.getBlock(x, y, depth)

    fun isOutOfField(point: Point2D): Boolean = field.isOutOfField(point)

    fun addBlock(block: Block) {
        field.addBlock(block)
        stateManager.addAction(AddAction(block))
    }

    fun removeBlock(block: Block) {
        field.removeBlock(block)
        stateManager.addAction(RemoveAction(block))
    }

    fun moveBlock(block: Block, newPosition: Point2D) {
        field.moveBlock(block, newPosition)
        stateManager.addAction(MoveAction(block, newPosition))
    }

    fun commitChanges() {
        // todo: Implement changes logic.
    }

    fun resetChanges() {
        // todo: Implement changes logic.
    }

    fun nextState(updateInterval: Long): List<Block> {
        return stateManager.nextState(updateInterval)
    }
}
