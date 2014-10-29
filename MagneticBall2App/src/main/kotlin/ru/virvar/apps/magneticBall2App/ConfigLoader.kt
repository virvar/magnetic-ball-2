package ru.virvar.apps.magneticBall2App

import java.util.Properties
import java.io.FileInputStream
import java.lang.Boolean.parseBoolean
import ru.virvar.apps.magneticBall2.levelGenerators.FromFileLevelGenerator
import ru.virvar.apps.magneticBallCore.ILevelGenerator
import ru.virvar.apps.magneticBallCore.IBlocksGenerator
import ru.virvar.apps.magneticBallCore.ITurnHandler
import ru.virvar.apps.magneticBallDrawing.Drawer
import ru.virvar.apps.magneticBall2.moveBehaviors.PacmanMoveBehavior
import ru.virvar.apps.magneticBall2.moveBehaviors.SimpleMoveBehavior
import ru.virvar.apps.magneticBall2.blocksGenerators.PointBlocksGenerator
import ru.virvar.apps.magneticBall2.blocksGenerators.EmptyBlocksGenerator
import ru.virvar.apps.magneticBall2.turnHandlers.PacmanTurnHandler
import ru.virvar.apps.magneticBall2.turnHandlers.ImpulseTurnHandler
import ru.virvar.apps.magneticBallDrawing.Pallet
import ru.virvar.apps.magneticBallDrawing.SimplePallet
import ru.virvar.apps.magneticBallDrawing.FixedFieldDrawer
import ru.virvar.apps.magneticBallDrawing.FixedPlayerDrawer

public class ConfigLoader  (fileName: String) {
    val props: Properties

    {
        val appProps = Properties()
        with (FileInputStream(fileName)) {
            appProps.load(this)
            close()
        }
        props = appProps
    }

    public fun readProperties(): GameConfig {
        val gameConfig = GameConfig()
        gameConfig.levelGenerator = getLevelGenerator()
        gameConfig.blocksGenerator = getBlocksGenerator()
        gameConfig.turnHandler = getTurnHandler()
        gameConfig.drawer = getDrawer()
        return gameConfig
    }

    fun getLevelGenerator(): ILevelGenerator {
        val levelFileName = props.getProperty("levelFileName")!!
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
