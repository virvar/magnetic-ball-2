package ru.virvar.apps.magneticBallCore

private var currentBlockId: Int = 1
abstract class Block {
    var id: Int
        private set
    var location: Point2D = Point2D(0, 0)
    var x: Int
        get() = location.x
        set(value){
            location.x = value
        }
    var y: Int
        get() = location.y
        set(value){
            location.y = value
        }

    init {
        id = currentBlockId++
    }

    abstract fun clone(): Block

    protected open fun initFrom(original: ru.virvar.apps.magneticBallCore.Block) {
        id = original.id
        location = Point2D(original.location.x, original.location.y)
    }
}
