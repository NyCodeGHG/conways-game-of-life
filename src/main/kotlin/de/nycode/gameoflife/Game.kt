package de.nycode.gameoflife

import de.nycode.gameoflife.cell.BorderCell
import de.nycode.gameoflife.cell.Cell
import de.nycode.gameoflife.cell.CellState
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement

const val gameFieldWidth = 500
const val gameFieldHeight = 500

const val scale = 2.0

private lateinit var cells: Array<Array<Cell>>

private var fps = 0
private var lastFrame: Double? = null

fun main() {
    cells = initializeArray()
    val canvas = createCanvas()

    val ctx = canvas.getContext("2d") as CanvasRenderingContext2D
    gameLoop(ctx)
}

fun initializeArray(): Array<Array<Cell>> {
    return Array(gameFieldWidth) { x ->
        Array(gameFieldHeight) { y ->
            Cell(x.toDouble(), y.toDouble(), CellState.values().random())
        }
    }
}

fun gameLoop(ctx: CanvasRenderingContext2D) {
    window.requestAnimationFrame {
        update()
        render(ctx)
        gameLoop(ctx)
    }
}

fun update() {

    cells.forEach {
        it.forEach { cell ->
            val neighbours = getNeighbors(cell)
            val aliveNeighbours = neighbours.filter { neighbour -> neighbour.isAlive() }.size
            val deadNeighbours = neighbours.filter { neighbour -> neighbour.isDead() }.size
            // TODO: change state after this "generation" and not instantly

            if (cell.isDead()) {
                when (aliveNeighbours) {
                    3 -> {
                        cell.state = CellState.ALIVE
                    }
                    0, 1 -> {
                        cell.state = CellState.DEAD
                    }
                }
            }
        }
    }
}

fun getNeighbors(cell: Cell): Array<Cell> {
    return arrayOf(
        cell.getOffsetCell(-1, -1), // Top Left
        cell.getOffsetCell(-1, 0), // Center Left
        cell.getOffsetCell(-1, 1), // Bottom Left
        cell.getOffsetCell(0, 1), // Bottom Center
        cell.getOffsetCell(1, 1), // Bottom Right
        cell.getOffsetCell(1, 0), // Bottom Center
        cell.getOffsetCell(1, 1), // Bottom Center
        cell.getOffsetCell(0, -1), // Bottom Center
    )
}

fun Cell.getOffsetCell(offsetX: Int, offsetY: Int): Cell {
    val neighborX = this.x + offsetX
    val neighborY = this.y + offsetY
    // Check if it's out of range
    return if (neighborX < 0 || neighborY < 0 || neighborX >= cells.size || neighborY >= cells[neighborX.toInt()].size) {
        BorderCell
    } else {
        cells[neighborX.toInt()][neighborY.toInt()]
    }
}

private val times = arrayListOf<Double>()

fun render(ctx: CanvasRenderingContext2D) {
    // Calculate fps
    val now = window.performance.now()
    while (times.isNotEmpty() && times[0] <= now - 1000) {
        times.removeFirst()
    }
    times.add(now)
    fps = times.size

    ctx.clearRect(0.0, 0.0, gameFieldWidth.toDouble(), gameFieldHeight.toDouble())

    cells.forEach {
        it.forEach { cell ->
            ctx.fillStyle = cell.state.style
            ctx.fillRect(cell.x * scale, cell.y * scale, scale, scale)
        }
    }

    ctx.fillStyle = "white"
    ctx.fillText("$fps FPS", 20.0, 20.0)
}

/**
 * Creates a canvas, sets its size and appends it to the document's body
 */
private fun createCanvas(): HTMLCanvasElement {
    val canvas = document.createElement("canvas") as HTMLCanvasElement
    canvas.width = (gameFieldWidth / scale).toInt()
    canvas.height = (gameFieldHeight / scale).toInt()
    document.body?.appendChild(canvas)
    return canvas
}
