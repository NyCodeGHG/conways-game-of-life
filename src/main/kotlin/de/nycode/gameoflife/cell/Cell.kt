package de.nycode.gameoflife.cell

import de.nycode.gameoflife.Options
import de.nycode.gameoflife.game.GameOfLife

/**
 * Represents a cell in the [GameOfLife]
 */
open class Cell(private val game: GameOfLife?, val x: Double, val y: Double, var state: CellState) {

    /**
     * Just a fancier way to check if the cell is alive
     * @return if the cell is a alive cell
     */
    fun isAlive(): Boolean {
        return this.state == CellState.ALIVE
    }

    /**
     * Just a fancier way to check if the cell is dead
     * @return if the cell is a dead cell
     */
    fun isDead(): Boolean {
        return this.state == CellState.DEAD
    }

    /**
     * Get another cell by the offset in x and y from a cell
     * The offset is positive when go down or right and negative when going left or up
     * @return the found cell or [BorderCell] when it's hitting the border
     * @param offsetX the horizontal offset
     * @param offsetY the vertical offset
     */
    private fun getOffsetCell(offsetX: Int, offsetY: Int): Cell {
        if (this.game == null) return BorderCell

        var neighborX = this.x + offsetX
        var neighborY = this.y + offsetY
        // Check if it's out of range
        return if (neighborX < 0 ||
            neighborY < 0 ||
            neighborX >= this.game.cells.size ||
            neighborY >= this.game.cells[neighborX.toInt()].size
        ) {
            if (Options.torusMode) {
                // [1] [1] [1] [1] [1]
                //  0   1   2   3   4  5
                // neighborX %= this.game.cells.size

                if (neighborX < 0) {
                    neighborX += this.game.cells.size
                } else if (neighborX > this.game.cells.size - 1) {
                    neighborX -= this.game.cells.size
                }

                // neighborY %= this.game.cells[neighborX.toInt()].size

                if (neighborY < 0) {
                    neighborY += this.game.cells[neighborX.toInt()].size
                } else if (neighborY > this.game.cells[neighborX.toInt()].size - 1) {
                    neighborY -= this.game.cells[neighborX.toInt()].size
                }

                this.game.cells[neighborX.toInt()][neighborY.toInt()]
            } else {
                // It's a border cell so we return BorderCell
                BorderCell
            }
        } else {
            // Return neighbour cell
            this.game.cells[neighborX.toInt()][neighborY.toInt()]
        }
    }

    /**
     * Gets the neighbours of a [Cell] by getting them by their offset to themself
     * No cell in the array can be null, because even when this cell would be at 0, 0 the neighbour would be a [BorderCell]
     * @return an [Array] of the neighbour cells
     */
    fun getNeighbors(): Array<Cell> {
        return arrayOf(
            this.getOffsetCell(-1, -1), // Top Left
            this.getOffsetCell(-1, 0), // Center Left
            this.getOffsetCell(-1, 1), // Bottom Left
            this.getOffsetCell(0, 1), // Bottom Center
            this.getOffsetCell(1, 1), // Bottom Right
            this.getOffsetCell(1, 0), // Center Right
            this.getOffsetCell(1, -1), // Top Right
            this.getOffsetCell(0, -1), // Top Center
        )
    }
}
