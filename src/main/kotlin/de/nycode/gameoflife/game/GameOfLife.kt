package de.nycode.gameoflife.game

import de.nycode.gameoflife.cell.Cell
import de.nycode.gameoflife.cell.CellState
import kotlin.random.Random

class GameOfLife(internal val width: Int, internal val height: Int, val scale: Double) : Game() {

    private val aliveChancePercentage = 31.25
    override val renderer: Renderer = GameOfLifeRenderer(this)
    internal val cells: Array<Array<Cell>> = this.initializeGameField()

    private fun initializeGameField(): Array<Array<Cell>> {
        val array = Array(width) { x ->
            Array(height) { y ->
                Cell(this, x.toDouble(), y.toDouble(), this.getRandomCellState())
            }
        }
        return array
    }

    override fun update() {
        val changes = hashMapOf<Pair<Int, Int>, CellState>()
        cells.forEach {
            it.forEach { cell ->
                val neighbours = cell.getNeighbors()

                val aliveNeighbours = neighbours.filter { neighbour -> neighbour.isAlive() }.size
                val deadNeighbours = neighbours.filter { neighbour -> neighbour.isDead() }.size
                // TODO: change state after this "generation" and not instantly

                if (cell.isDead()) {
                    when (aliveNeighbours) {
                        3 -> {
                            changes[cell.x.toInt() to cell.y.toInt()] = CellState.ALIVE
                        }
                    }
                } else {
                    when (aliveNeighbours) {
                        0, 1, 4, 5, 6, 7, 8 -> {
                            changes[cell.x.toInt() to cell.y.toInt()] = CellState.DEAD
                        }
                    }
                }
            }
        }
        changes.forEach {
            val x = it.key.first
            val y = it.key.second

            cells[x][y].state = it.value
        }
    }

    private fun getRandomCellState(): CellState {
        //val aliveChancePercentage = this.aliveChancePercentage
        val randomDouble = Random.nextDouble(100.0)
        return if (randomDouble < aliveChancePercentage) {
            CellState.ALIVE
        } else {
            CellState.DEAD
        }
    }
}
