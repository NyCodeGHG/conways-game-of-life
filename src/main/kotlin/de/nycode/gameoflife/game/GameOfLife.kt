package de.nycode.gameoflife.game

import de.nycode.gameoflife.cell.Cell
import de.nycode.gameoflife.cell.CellState

class GameOfLife(internal val width: Int, internal val height: Int, val scale: Double) : Game() {

    override val renderer: Renderer = GameOfLifeRenderer(this)
    internal val cells: Array<Array<Cell>> = this.initializeGameField()

    private fun initializeGameField(): Array<Array<Cell>> {
        return Array(width) { x ->
            Array(height) { y ->
                Cell(this, x.toDouble(), y.toDouble(), CellState.values().random())
            }
        }
    }

    override fun update() {
        cells.forEach {
            it.forEach { cell ->
                val neighbours = cell.getNeighbors()

                val aliveNeighbours = neighbours.filter { neighbour -> neighbour.isAlive() }.size
                val deadNeighbours = neighbours.filter { neighbour -> neighbour.isDead() }.size
                // TODO: change state after this "generation" and not instantly

                if (cell.isDead()) {
                    when (aliveNeighbours) {
                        3 -> {
                            cell.state = CellState.ALIVE
                        }
                    }
                } else {
                    when (aliveNeighbours) {
                        0, 1, 4, 5, 6, 7, 8 -> {
                            cell.state = CellState.DEAD
                        }
                    }
                }
            }
        }
    }
}
