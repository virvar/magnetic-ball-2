package ru.virvar.apps.magneticBallCore

interface IBlocksGenerator {
    fun generateInitialBlocks(level: Level)
    fun generateBlocks(level: Level)
}
