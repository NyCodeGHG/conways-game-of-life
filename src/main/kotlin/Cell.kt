package de.nycode.gameoflife

open class Cell(val x: Double, val y: Double, var state: CellState) {

    fun isAlive(): Boolean {
        return this.state == CellState.ALIVE
    }

    fun isDead(): Boolean {
        return this.state == CellState.DEAD
    }
}
