package ru.virvar.apps.magneticBall2App

import kotlin.properties.Delegates
import ru.virvar.apps.magneticBallCore.IBlocksGenerator
import ru.virvar.apps.magneticBallCore.ILevelGenerator
import ru.virvar.apps.magneticBallCore.ITurnHandler
import ru.virvar.apps.magneticBallDrawing.Drawer

public class GameConfig {
    public var levelGenerator: ILevelGenerator by Delegates.notNull()
    public var blocksGenerator: IBlocksGenerator by Delegates.notNull()
    public var turnHandler: ITurnHandler by Delegates.notNull()
    public var drawer: Drawer by Delegates.notNull()
}
