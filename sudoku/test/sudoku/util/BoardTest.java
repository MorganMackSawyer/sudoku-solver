package sudoku.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the Board class
 * @author Morgan Sawyer
 */
class BoardTest {
	
	/** Easy Sudoku Board in 2D array format*/
	private final int[][] EASY_BOARD = {
		{0, 8, 0, 0, 0, 0, 2, 0, 0},
		{0, 0, 0, 0, 8, 4, 0, 9, 0},
		{0, 0, 6, 3, 2, 0, 0, 1, 0},
		{0, 9, 7, 0, 0, 0, 0, 8, 0},
		{8, 0, 0, 9, 0, 3, 0, 0, 2},
		{0, 1, 0, 0, 0, 0, 9, 5, 2},
		{0, 7, 0, 0, 4, 5, 8, 0, 0},
		{0, 3, 0, 7, 1, 0, 0, 0, 0},
		{0, 0, 8, 0, 0, 0, 0, 4, 0}};
	
	/** Hard Sudoku Board in 2D array format*/
	private final int[][] HARD_BOARD = {
		{0, 2, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 6, 0, 0, 0, 0, 3},
		{0, 7, 4, 0, 8, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 3, 0, 0, 2},
		{0, 8, 0, 0, 4, 0, 0, 1, 0},
		{6, 0, 0, 5, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 1, 0, 7, 8, 0},
		{5, 0, 0, 0, 0, 9, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 4, 0}};
	
	/** Unsolved Sudoku Board in 2D array format*/
	private final int[][] UNSOLVED_BOARD = {
		{5, 3, 0, 0, 7, 0, 0, 0, 0},
		{6, 0, 0, 1, 9, 5, 0, 0, 0},
		{0, 9, 8, 0, 0, 0, 0, 6, 0},
		{8, 0, 0, 0, 6, 0, 0, 0, 3},
		{4, 0, 0, 8, 0, 3, 0, 0, 1},
		{7, 0, 0, 0, 2, 0, 0, 0, 6},
		{0, 6, 0, 0, 0, 0, 2, 8, 0},
		{0, 0, 0, 4, 1, 9, 0, 0, 5},
		{0, 0, 0, 0, 8, 0, 0, 7, 9}};
	
	/** Solved Sudoku Board in 2D array format */
	private final int[][] SOLVED_BOARD = {
		{5, 3, 4, 6, 7, 8, 9, 1, 2},
		{6, 7, 2, 1, 9, 5, 3, 4, 8},
		{1, 9, 8, 3, 4, 2, 5, 6, 7},
		{8, 5, 9, 7, 6, 1, 4, 2, 3},
		{4, 2, 6, 8, 5, 3, 7, 9, 1},
		{7, 1, 3, 9, 2, 4, 8, 5, 6},
		{9, 6, 1, 5, 3, 7, 2, 8, 4},
		{2, 8, 7, 4, 1, 9, 6, 3, 5},
		{3, 4, 5, 2, 8, 6, 1, 7, 9}};
	
	/** Easy Sudoku Board */
	private Board easy = new Board(EASY_BOARD);;
	/** Hard Sudoku Board */
	private Board hard  = new Board(HARD_BOARD);
	/** Unsolved Sudoku Board */
	private Board unsolved = new Board(UNSOLVED_BOARD);
	/** Solved Sudoku Board */
	private Board solved = new Board(SOLVED_BOARD);
	
	/**
	 * Tests the ValidNumberInRow method
	 */
	@Test
	void testValidNumberInRow() {
		assertTrue(easy.validNumberInRow(1, 0));
		assertTrue(easy.validNumberInRow(3, 0));
		assertFalse(easy.validNumberInRow(8, 0));
		
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> easy.validNumberInRow(-1, 0));
		assertEquals("Sudoku Program Doesn't Allow for Numbers Less"
				+ " than 0 or Greater than 9 in the Board", e1.getMessage());
	}

	/**
	 * Tests the ValidNumberInColumn method
	 */
	@Test
	void testValidNumberInColumn() {
		assertTrue(hard.validNumberInColumn(1, 0));
		assertTrue(hard.validNumberInColumn(3, 0));
		assertFalse(hard.validNumberInColumn(6, 0));
		
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> easy.validNumberInColumn(10, 8));
		assertEquals("Sudoku Program Doesn't Allow for Numbers Less"
				+ " than 0 or Greater than 9 in the Board", e1.getMessage());
	}

	/**
	 * Tests the ValidNumberInSmallBox method
	 */
	@Test
	void testValidNumberInSmallBox() {
		assertTrue(hard.validNumberInSmallBox(1, 0, 0));
		assertTrue(hard.validNumberInSmallBox(5, 1, 1));
		assertFalse(hard.validNumberInSmallBox(7, 2, 2));
		
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> easy.validNumberInSmallBox(10, 1, 2));
		assertEquals("Sudoku Program Doesn't Allow for Numbers Less"
				+ " than 0 or Greater than 9 in the Board", e1.getMessage());
	}

	/**
	 * Tests the SolveBoard method
	 */
	@Test
	void testSolveBoard() {
		assertTrue(unsolved.solveBoard());
		assertTrue(solved.equals(unsolved));
		assertTrue(solved.solveBoard());
	}

}
