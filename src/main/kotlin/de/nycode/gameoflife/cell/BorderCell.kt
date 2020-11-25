package de.nycode.gameoflife.cell

/**
 * Represents a [Cell] but outside of the game field
 */
object BorderCell : Cell(null, -1.0, -1.0, CellState.DEAD)
