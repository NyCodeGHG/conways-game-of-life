package de.nycode.gameoflife.game

abstract class Game {

    abstract val renderer: Renderer
    abstract fun update()

}
