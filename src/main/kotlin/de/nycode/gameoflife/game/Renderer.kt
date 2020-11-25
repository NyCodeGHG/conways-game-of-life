package de.nycode.gameoflife.game

import org.w3c.dom.CanvasRenderingContext2D

interface Renderer {

    fun render(ctx: CanvasRenderingContext2D)

}
