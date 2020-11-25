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
    val aliveChancePercentage = 10.0

    /**
     * Or "pac-man" mode
     */
    val torusMode = true
}
