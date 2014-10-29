package ru.virvar.apps.magneticBallCore

import ru.virvar.apps.magneticBallCore.gameActions.*

public open class Level(
        public val fieldSize: Int,
        public val moveBehavior: IMoveBehavior,
        public val moveHelper: IMoveHelper) {
    private val field: Field
    private val stateManager: LevelStateManager
    public var score: Int = 0
    public var gameState: GameState = GameState.LOOSE
    public val freeCells: List<Int>
        get () = field.freeCells
    public val blocks: Map<Int, Block>
        get() = field.blocks

    {
        field = Field(fieldSize)
        stateManager = LevelStateManager()
    }

    public fun getBlock(x: Int, y: Int, depth: Int = 0): Block? = field.getBlock(x, y, depth)

    public fun isOutOfField(point: Point2D): Boolean = field.isOutOfField(point)

    public fun addBlock(block: Block) {
        field.addBlock(block)
        stateManager.addAction(AddAction(block))
    }

    public fun removeBlock(block: Block) {
        field.removeBlock(block)
        stateManager.addAction(RemoveAction(block))
    }

    public fun moveBlock(block: Block, newPosition: Point2D) {
        field.moveBlock(block, newPosition)
        stateManager.addAction(MoveAction(block, newPosition))
    }

    public fun commitChanges() {
        // todo: Implement changes logic.
    }

    public fun resetChanges() {
        // todo: Implement changes logic.
    }

    public fun nextState(updateInterval: Long): List<Block> {
        return stateManager.nextState(updateInterval)
    }
}
