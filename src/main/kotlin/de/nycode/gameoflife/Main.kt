package de.nycode.gameoflife

import de.nycode.gameoflife.game.GameLoop
import kotlinx.browser.window

private var fps = 0
private var lastFrame: Double? = null

fun main() {
    val gameLoop = GameLoop(100, 100, 8.0)
    window.setTimeout({
        gameLoop.start()
    }, 1000)
}

private val times = arrayListOf<Double>()

//fun render(ctx: CanvasRenderingContext2D) {
//    // Calculate fps
//    val now = window.performance.now()
//    while (times.isNotEmpty() && times[0] <= now - 1000) {
//        times.removeFirst()
//    }
//    times.add(now)
//    fps = times.size
//
//    ctx.fillStyle = "white"
//    ctx.fillText("$fps FPS", 20.0, 20.0)
//}
