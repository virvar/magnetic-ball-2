package ru.virvar.apps.magneticBallCore

public abstract class Block {
    private class object {
        var currentId = 1
    }

    public var id: Int
        private set
    public var location: Point2D = Point2D(0, 0)
    public var x: Int
        get() = location.x
        set(value){
            location.x = value
        }
    public var y: Int
        get() = location.y
        set(value){
            location.y = value
        }

    {
        $id = Block.currentId++
    }

    public abstract fun clone(): Block

    protected open fun initFrom(original: ru.virvar.apps.magneticBallCore.Block) {
        id = original.id
        location = Point2D(original.location.x, original.location.y)
    }
}
