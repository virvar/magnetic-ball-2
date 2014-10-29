package ru.virvar.apps.magneticBallCore

public trait IBlocksGenerator {
    fun generateInitialBlocks(level: Level)
    fun generateBlocks(level: Level)
}
