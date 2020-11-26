package de.nycode.gameoflife

import de.nycode.gameoflife.game.GameLoop
import kotlinx.browser.window

fun main() {
    val gameLoop = GameLoop(150, 150, 5.0)
    window.setTimeout({
        gameLoop.start()
    }, 1000)
}

/**
 * Options for modifying the game
 */
object Options {

    /**
     * The speed how long the [GameLoop] waits before rendering the next frame in milliseconds
     */
    const val updateSpeed = 100

    /**
     * The chance of a cell to be alive in percent
     */
    const val aliveChancePercentage = 10.0

    /**
     * If the neighbours behave like in a torus ("like in pac man") or the walls are just [de.nycode.gameoflife.cell.BorderCell]s
     */
    const val torusMode = true
}
