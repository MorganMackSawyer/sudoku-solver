package sudoku.util;

import java.util.Arrays;

/**
 * Creates and solves sudoku boards.
 * @author Morgan Sawyer
 */
public class Board {

	/** Size of the Sudoku Board */
	public static final int GRID_SIZE = 9;
	
	/** Default Sudoku Board */
	public static final int[][] DEFAULT_BOARD = {
		{0, 0, 0, 0, 8, 9, 5, 1, 6},
		{0, 0, 0, 7, 1, 0, 2, 0, 4},
		{0, 0, 4, 0, 6, 2, 0, 0, 7},
		{6, 7, 5, 8, 2, 0, 0, 0, 3},
		{0, 8, 0, 0, 3, 0, 0, 7, 0},
		{4, 0, 0, 0, 9, 7, 6, 8, 2},
		{3, 0, 0, 1, 5, 0, 4, 0, 0},
		{9, 0, 8, 0, 7, 6, 0, 0, 0},
		{5, 1, 2, 9, 4, 0, 0, 0, 0}};
	
	/** Sudoku Board */
	private int[][] board;
	
	/**
	 * Default Constructor for the Sudoku Board. 
	 */
	public Board() {
		this.board = DEFAULT_BOARD;
	}
	
	/**
	 * Default Constructor for the Sudoku Board. 
	 */
	public Board(int[][] board) {
		this.board = board;
	}
	
	/**
	 * Returns true if the row of the board allows the number to be placed
	 * @param number number to add to the board
	 * @param row row to check in the sudoku board
	 * @return true if the row of the board allows the number to be place,
	 * 		otherwise false.
	 * @throws IllegalArgumentException 
	 */
	public boolean validNumberInRow(int number, int row) {
		
		if (number < 0 || number > GRID_SIZE) {
			invalidNumberException();
		}
		for (int i = 0; i < GRID_SIZE; i++) {
			if (board[row][i] < 0 || board[row][i] > GRID_SIZE) {
				invalidNumberException();
			} else if (board[row][i] == number) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Returns true if the column of the board allows the number to be placed
	 * @param number number to add to the board
	 * @param column column to check in the sudoku board
	 * @return true if the column of the board allows the number to be place,
	 * 		otherwise false.
	 * @throws IllegalArgumentException 
	 */
	public boolean validNumberInColumn(int number, int column) {
		
		if (number < 0 || number > GRID_SIZE) {
			invalidNumberException();
		}
		for (int i = 0; i < GRID_SIZE; i++) {
			if (board[i][column] < 0 || board[column][i] > GRID_SIZE) {
				invalidNumberException();
			} else if (board[i][column] == number) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Returns true if the small box of the board allows the number to be placed
	 * @param number number to add to the board
	 * @param row row to check in the sudoku board
	 * @param column column to check in the sudoku board
	 * @return true if the small box of the board allows the number to be place,
	 * 		otherwise false.
	 * @throws IllegalArugmentException
	 */
	public boolean validNumberInSmallBox(int number, int row, int column) {
		
		if (number < 0 || number > GRID_SIZE) {
			invalidNumberException();
		}
		
		int smallBoxRow = row - row % 3;
		int smallBoxColumn = column - column % 3;
		
		for (int i = smallBoxRow; i < smallBoxRow + 3; i++) {
			for (int j = smallBoxColumn; j < smallBoxColumn + 3; j++) {
				if (board[i][j] == number) {
					return false;
				}
			}
		}
		return true;	
	}
	
	/**
	 * Returns true if the number is valid in the specified row, column, and small box.
	 * @param board sudoku board
	 * @param number number to add to the board
	 * @param row row to check in the sudoku board
	 * @param column column to check in the sudoku board
	 * @return true if the number is valid in the board, otherwise false.
	 */
	public boolean isValidNumber(int number, int row, int column) {
		return validNumberInRow(number, row) &&
				validNumberInColumn(number, column) &&
				validNumberInSmallBox(number, row, column);
	}
	
	/**
	 * Solves the sudoku board recursively by place valid numbers one by one
	 * and then replacing a value with zero if a future spot has no valid inputs. 
	 * @param board sudoku board
	 * @return true if the board solved, otherwise returns false.
	 */
	public boolean solveBoard() {
		
		for (int row = 0; row < GRID_SIZE; row++) {
			for (int column = 0; column < GRID_SIZE; column++) {
				if (board[row][column] == 0) {
					for (int number = 1; number <= GRID_SIZE; number++) {
						if (isValidNumber(number, row, column)) {
							board[row][column] = number;
							
							if (solveBoard()) {
								return true;
							} else {
								board[row][column] = 0;
							}
						}
					}
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Throws IllegalArumentException when a number is outside
	 * of the boundaries of the sudoku board
	 * @throws IllegalArgumentException when a number in the board is 
	 * 		outside boundaries of the board
	 */
	private void invalidNumberException() {
		throw new IllegalArgumentException("Sudoku Program Doesn't Allow "
				+ "for Numbers Less than 0 or Greater than 9 in the Board");
	}
	
	/**
	 * Prints the board to the console.
	 */
	public void printBoard() {
		
		for (int row = 0; row < GRID_SIZE; row++) {
			if (row % 3 == 0 && row != 0) {
				System.out.println("---+---+---");
			}
			for (int column = 0; column < GRID_SIZE; column++) {
				if (column % 3 == 0 && column != 0) {
					System.out.print("|");
				}
				System.out.print(board[row][column]);
			}
			System.out.println();
		}
	}

	/**
	 * Generates a unique hash code for boards.
	 * @return the integer value of the hash code.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(board);
		return result;
	}

	/**
	 * Checks two boards and returns true if they are equal to each other.
	 * @param obj the other board to check.
	 * @return true if boards numbers are the same, false otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board other = (Board) obj;
		for (int i = 0; i < GRID_SIZE; i++) {
			for (int j = 0; j < GRID_SIZE; j++) {
				if (this.board[i][j] != other.board[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
}
