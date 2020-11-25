package de.nycode.gameoflife.game

import org.w3c.dom.CanvasRenderingContext2D

class GameOfLifeRenderer(private val game: GameOfLife) : Renderer {

    override fun render(ctx: CanvasRenderingContext2D) {
        val scale = this.game.scale
        ctx.clearRect(0.0, 0.0, game.width.toDouble(), game.height.toDouble())

        this.game.cells.forEach {
            it.forEach { cell ->
                ctx.fillStyle = cell.state.style
                ctx.fillRect(cell.x * scale, cell.y * scale, scale, scale)
            }
        }
    }

}
