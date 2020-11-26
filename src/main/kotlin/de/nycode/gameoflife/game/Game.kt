package de.nycode.gameoflife.game

/**
 * Represents any type of a game which can be rendered with any Type of [Renderer]
 */
abstract class Game {

    /**
     * The renderer the game uses
     */
    abstract val renderer: Renderer

    /**
     * Game update lifecycle
     */
    abstract fun update()

}
