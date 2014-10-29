package ru.virvar.apps.magneticBall2App.Main

import ru.virvar.apps.magneticBall2App.GameFrame
import ru.virvar.apps.magneticBallCore.Game
import ru.virvar.apps.magneticBall2App.ConfigLoader

fun main(args: Array<String>) {
    val configLoader = ConfigLoader("resources/appProperties.cfg")
    val gameConfig = configLoader.readProperties()
    val game = Game(gameConfig.levelGenerator, gameConfig.blocksGenerator, gameConfig.turnHandler)
    val gameFrame = GameFrame(game, gameConfig.drawer)
    gameFrame.setVisible(true)
    gameFrame.start()
}
