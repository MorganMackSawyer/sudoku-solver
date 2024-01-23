package sudoku.util;


/**
 * 
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
	 * 
	 * @param board
	 * @param number
	 * @param row
	 * @return
	 * @throws IllegalArgumentException 
	 */
	public boolean validNumberInRow(int[][] board, int number, int row) {
		
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
	 * 
	 * @param board
	 * @param number
	 * @param column
	 * @return
	 * @throws IllegalArgumentException 
	 */
	public boolean validNumberInColumn(int[][] board, int number, int column) {
		
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
	 * 
	 * @param board
	 * @param number
	 * @param row
	 * @param column
	 * @return
	 * @throws IllegalArugmentException
	 */
	public boolean validNumberInSmallBox(int[][] board, int number, int row, int column) {
		
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
	 * Returns true if all three valid number methods 
	 * @param board
	 * @param number
	 * @param row
	 * @param column
	 * @return
	 */
	public boolean isValidNumber(int[][] board, int number, int row, int column) {
		return validNumberInRow(board, number, row) &&
				validNumberInColumn(board, number, column) &&
				validNumberInSmallBox(board, number, row, column);
	}
	
	/**
	 * Solves the sudoku board recursively by place valid numbers one by one
	 * and then replacing a value with zero if a future spot has no valid inputs. 
	 * @param board sudoku board
	 * @return true if the board solved, otherwise returns false.
	 */
	public boolean solveBoard(int[][] board) {
		
		for (int row = 0; row < GRID_SIZE; row++) {
			for (int column = 0; column < GRID_SIZE; column++) {
				if (board[row][column] == 0) {
					for (int number = 1; number <= GRID_SIZE; number++) {
						if (isValidNumber(board, number, row, column)) {
							board[row][column] = number;
							
							if (solveBoard(board)) {
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
	
}
