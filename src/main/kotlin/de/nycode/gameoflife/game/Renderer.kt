package de.nycode.gameoflife.game

import org.w3c.dom.CanvasRenderingContext2D

/**
 * Class for doing the graphical rendering on an html canvas
 */
interface Renderer {

    fun render(ctx: CanvasRenderingContext2D)

}
