package ru.virvar.apps.magneticBall2App

import ru.virvar.apps.magneticBall2.blocksGenerators.EmptyBlocksGenerator
import ru.virvar.apps.magneticBall2.blocksGenerators.PointBlocksGenerator
import ru.virvar.apps.magneticBall2.levelGenerators.FromFileLevelGenerator
import ru.virvar.apps.magneticBall2.moveBehaviors.PacmanMoveBehavior
import ru.virvar.apps.magneticBall2.moveBehaviors.SimpleMoveBehavior
import ru.virvar.apps.magneticBall2.turnHandlers.ImpulseTurnHandler
import ru.virvar.apps.magneticBall2.turnHandlers.PacmanTurnHandler
import ru.virvar.apps.magneticBallCore.IBlocksGenerator
import ru.virvar.apps.magneticBallCore.ILevelGenerator
import ru.virvar.apps.magneticBallCore.ITurnHandler
import ru.virvar.apps.magneticBallDrawing.*
import java.io.FileInputStream
import java.lang.Boolean.parseBoolean
import java.util.*

class ConfigLoader(configName: String) {
    val props: Properties

    init {
        val appProps = Properties()
        val fileName = getResourceFile(configName)
        with (FileInputStream(fileName)) {
            appProps.load(this)
            close()
        }
        props = appProps
    }

    fun getResourceFile(resourceName: String): String {
        val classLoader = javaClass.classLoader!!
        return classLoader.getResource(resourceName)!!.file!!
    }

    fun readProperties(): GameConfig {
        val gameConfig = GameConfig()
        gameConfig.levelGenerator = getLevelGenerator()
        gameConfig.blocksGenerator = getBlocksGenerator()
        gameConfig.turnHandler = getTurnHandler()
        gameConfig.drawer = getDrawer()
        return gameConfig
    }

    fun getLevelGenerator(): ILevelGenerator {
        val levelFileName = getResourceFile(props.getProperty("level")!!)
        val moveBehavior = when (props.getProperty("moveBehavior")) {
            "Pacman" -> PacmanMoveBehavior()
            "Simple" -> SimpleMoveBehavior()
            else -> throw Exception("Unexpected value")
        }
        val levelGenerator = FromFileLevelGenerator(levelFileName, moveBehavior)
        return levelGenerator
    }

    fun getBlocksGenerator(): IBlocksGenerator =
            when (props.getProperty("blocksGenerator")) {
                "Point" -> PointBlocksGenerator()
                "Empty" -> EmptyBlocksGenerator()
                else -> throw Exception("Unexpected value")
            }

    fun getTurnHandler(): ITurnHandler =
            when (props.getProperty("turnHandler")) {
                "Pacman" -> PacmanTurnHandler()
                "Impulse" -> ImpulseTurnHandler()
                else -> throw Exception("Unexpected value")
            }


    fun getDrawer(): Drawer {
        val pallet = getPallet()
        val isPointersVisible = parseBoolean(props.getProperty("levelFileName"))
        return when (props.getProperty("drawer")) {
            "FixedField" -> FixedFieldDrawer(pallet, isPointersVisible)
            "FixedPlayer" -> FixedPlayerDrawer(pallet, isPointersVisible)
            else -> throw Exception("Unexpected value")
        }
    }

    fun getPallet(): Pallet =
            when (props.getProperty("pallet")) {
                "Simple" -> SimplePallet()
                else -> throw Exception("Unexpected value")
            }
}
