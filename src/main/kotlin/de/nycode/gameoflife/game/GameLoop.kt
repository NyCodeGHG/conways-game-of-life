package de.nycode.gameoflife.game

import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement

class GameLoop(private val width: Int, private val height: Int, private val scale: Double) {

    private val game: Game = GameOfLife(width, height, scale)
    private val canvas = this.createCanvas()
    private val speed = 100

    /**
     * Creates a canvas, sets its size and appends it to the document's body
     */
    private fun createCanvas(): HTMLCanvasElement {
        val canvas = document.createElement("canvas") as HTMLCanvasElement
        canvas.width = (width * scale).toInt()
        canvas.height = (height * scale).toInt()
        document.body?.appendChild(canvas)
        return canvas
    }

    /**
     * Starts the actual game loop
     */
    fun start() {
        val ctx = canvas.getContext("2d") as CanvasRenderingContext2D
        this.runGameFrame(ctx)
    }

    private fun runGameFrame(ctx: CanvasRenderingContext2D) {
        //window.requestAnimationFrame {
        this.game.update()
        this.game.renderer.render(ctx)
        window.setTimeout({
            runGameFrame(ctx)
        }, speed)
        //}
    }

}
