package de.nycode.gameoflife.game

abstract class Game {

    abstract val renderer: Renderer

    /**
     * Game update lifecycle
     */
    abstract fun update()

}
