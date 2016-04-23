package ru.virvar.apps.magneticBall2App

import kotlin.properties.Delegates
import ru.virvar.apps.magneticBallCore.IBlocksGenerator
import ru.virvar.apps.magneticBallCore.ILevelGenerator
import ru.virvar.apps.magneticBallCore.ITurnHandler
import ru.virvar.apps.magneticBallDrawing.Drawer

class GameConfig {
    var levelGenerator: ILevelGenerator by Delegates.notNull()
    var blocksGenerator: IBlocksGenerator by Delegates.notNull()
    var turnHandler: ITurnHandler by Delegates.notNull()
    var drawer: Drawer by Delegates.notNull()
}
