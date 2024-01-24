package sudoku.run;

import sudoku.util.Board;

/**
 * Runs the sudoku solver
 * @author Morgan Sawyer
 */
public class SudokuSolverRun {
	
	/**
	 * Runs the Board Program and gives an example of the sudoku solver in action.
	 * @param args arguments to run the program.
	 */
	public static void main(String args[]) {
		Board board = new Board();
		System.out.println("Unsolved Board:");
		board.printBoard();
		System.out.println();
		System.out.println("Solved Board:");
		board.solveBoard();
		board.printBoard();
	}
}
