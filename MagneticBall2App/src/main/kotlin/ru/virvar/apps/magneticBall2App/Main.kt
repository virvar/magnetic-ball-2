package ru.virvar.apps.magneticBall2App

import ru.virvar.apps.magneticBallCore.Game

fun main(args: Array<String>) {
    val configLoader = ConfigLoader("appProperties.cfg")
    val gameConfig = configLoader.readProperties()
    val game = Game(gameConfig.levelGenerator, gameConfig.blocksGenerator, gameConfig.turnHandler)
    val gameFrame = GameFrame(game, gameConfig.drawer)
    gameFrame.isVisible = true
    gameFrame.start()
}
