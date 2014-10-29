package ru.virvar.apps.magneticBall2App

import javax.swing.JFrame
import java.awt.Color
import java.awt.Graphics
import java.awt.event.KeyListener
import java.awt.event.KeyEvent
import java.awt.event.WindowEvent
import java.awt.event.WindowAdapter
import ru.virvar.apps.magneticBallCore.Game
import ru.virvar.apps.magneticBallCore.GameState
import ru.virvar.apps.magneticBallCore.Point2D
import ru.virvar.apps.magneticBallDrawing.Drawer
import kotlin.concurrent.thread

public class GameFrame(val game: Game, drawer: Drawer) : JFrame(), KeyListener {
    private val drawInterval = 100L
    private var drawingThread: Thread? = null
    private val panel: GamePanel
    private val backColor = Color.BLACK
    private var keyReleased = true

    {
        initUi()
        panel = GamePanel(drawer)
        panel.game = game
        add(panel)
    }

    private fun initUi() {
        setBounds(100, 100, 600, 600)
        //        setBackground(backColor)
        addKeyListener(this)
        addWindowListener(object : WindowAdapter() {
            override fun windowClosing(e: WindowEvent) {
                stop()
            }
        })
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    }

    public fun start() {
        game.start()
        game.nextLevel()
        drawingThread = thread(start = true) { startDraw() }
    }

    private fun nextLevel() {
        game.nextLevel()
    }

    private fun resetLevel() {
        game.resetLevel()
    }

    override fun paint(g: Graphics) {
        super<JFrame>.paint(g)
        this.setTitle("Score: ${game.level?.score}")
    }

    override fun keyTyped(e: KeyEvent) {
        // do nothing
    }

    override fun keyPressed(e: KeyEvent) {
        if (keyReleased) {
            keyReleased = false
            if (e.getKeyCode() == KeyEvent.VK_Q) {
                nextLevel()
                return
            }
            val gameState = game.level?.gameState
            if (gameState == GameState.WIN) {
                nextLevel()
                return
            }
            if (gameState == GameState.LOOSE) {
                resetLevel()
                return
            }
            processTurn(e)
        }
    }

    override fun keyReleased(e: KeyEvent?) {
        keyReleased = true
    }

    private fun processTurn(e: KeyEvent) {
        val course = Point2D(0, 0)
        when (e.getKeyCode()) {
            KeyEvent.VK_LEFT ->
                course.x = -1
            KeyEvent.VK_RIGHT ->
                course.x = 1
            KeyEvent.VK_UP ->
                course.y = -1
            KeyEvent.VK_DOWN ->
                course.y = 1
        }
        if (!course.isEmpty()) {
            game.turn(course)
        }
    }

    private fun startDraw() {
        while (true) {
            repaint()
            try {
                Thread.sleep(drawInterval)
            } catch(e: Exception) {
            }
        }
    }

    private fun stop() {
        drawingThread?.interrupt()
        game.stop()
    }

    fun finalize() {
        stop()
    }
}
