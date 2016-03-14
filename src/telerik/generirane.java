package telerik;

import java.util.Random;
import java.util.Scanner;

public class generirane {
	/*
	 * Variable Declarations
	 */
	public boolean isVisited[][] = new boolean[7][7];
	public String maze[][] = new String[7][7];
	public int playersCurrentRow;
	public int playersCurrentColumn;
	public String command;
	public boolean isExit = false;
	public int playersMovesCount = 0;
	HighScoreBoard board;

	/*
	 * Initialize a Random generation maze
	 *
	 */
	void initializeMaze() {
		Random randomgenerator = new Random();
		// Generates a new maze until at least one solution is found
		do {
			for (int row = 0; row < 7; row++) {
				for (int column = 0; column < 7; column++) {
					isVisited[row][column] = false;
					if (randomgenerator.nextInt(2) == 1) {
						maze[row][column] = "X";
					} else {
						maze[row][column] = "-";
					}
				}
			}
		} while (isSolvable(3, 3) == false);
		playersCurrentRow = 3;
		playersCurrentColumn = 3;

		maze[playersCurrentRow][playersCurrentColumn] = "*";
		printMaze();
	}

	/*
	 * ScoreBoard Initialization
	 * 
	 */
	public void initializeScoreBoard() {
		board = new HighScoreBoard();
	}

	/*
	 * Recursive function for solving the maze
	 */
	public boolean isSolvable(int row, int col) {
		if ((row == 6) || (col == 6) || (row == 0) || (col == 0)) {
			isExit = true;
			return isExit;
		}
		moveSolvable(row - 1, col);
		moveSolvable(row + 1, col);
		moveSolvable(row, col - 1);
		moveSolvable(row, col + 1);
		return isExit;
	}

	/*
	 * Function which can Print the Maze accordingly
	 */
	void printMaze() {
		for (int row = 0; row < 7; row++) {
			for (int column = 0; column < 7; column++) {
				System.out.print(maze[row][column] + " ");
			}
			{
				System.out.println();
			}
		}
	}

	/*
	 * This function take the input from Command line and check "exit","restart"
	 * and "top" keyword
	 */
	public void inputCommand() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your next move : L(left), " + "R(right), U(up), D(down) ");
		command = scanner.next();
		int size = command.length();
		if (command.equalsIgnoreCase("exit")) {
			System.out.println("Good bye!");
			System.exit(0);
		} else if (command.equalsIgnoreCase("restart")) {
			isExit = false;
			initializeMaze();
		} else if (command.equals("top")) {
			if (board.list.size() > 0) {
				board.printBoard(board.list);
			} else {
				System.out.println("The High score board is empty!");
			}
		} else if (size > 1) {
			System.out.println("Invalid command!");
		} else {
			movePlayer(command);
		}

	}

	/*
	 * Function which move the players command
	 */
	public void movePlayer(String firstLetter) {
		if (firstLetter.equalsIgnoreCase("l")) {
			moveLeft(playersCurrentRow, playersCurrentColumn);
		} else if (firstLetter.equalsIgnoreCase("r")) {
			moveRight(playersCurrentRow, playersCurrentColumn);
		} else if (firstLetter.equalsIgnoreCase("u")) {
			moveUp(playersCurrentRow, playersCurrentColumn);
		} else if (firstLetter.equalsIgnoreCase("d")) {
			moveDown(playersCurrentRow, playersCurrentColumn);
		} else {
			System.out.println("Invalid command!");
		}
	}

	void moveLeft(int pCrow, int pCcol) {
		if (!maze[pCrow][pCcol - 1].equalsIgnoreCase("X")) {
			swapCells(pCrow, pCrow, pCcol, pCcol - 1);
			playersCurrentColumn = pCcol - 1;
			playersMovesCount++;

		} else {
			invalid();
		}
	}

	void moveRight(int pCrow, int pCcol) {
		if (!maze[pCrow][pCcol + 1].equalsIgnoreCase("X")) {
			swapCells(pCrow, pCrow, pCcol, pCcol + 1);
			System.out.println();
			printMaze();
			playersCurrentColumn = pCcol + 1;
			playersMovesCount++;

		} else {
			invalid();
		}
	}

	void moveUp(int pCrow, int pCcol) {
		if (!maze[pCrow - 1][pCcol].equalsIgnoreCase("X")) {
			swapCells(pCrow, pCrow - 1, pCcol, pCcol);
			playersCurrentRow = pCrow - 1;
			playersMovesCount++;

		} else {
			invalid();
		}
	}

	void moveDown(int pCrow, int pCcol) {
		if (!maze[pCrow + 1][playersCurrentColumn].equalsIgnoreCase("X")) {
			swapCells(pCrow, pCrow + 1, pCcol, pCcol);
			playersCurrentRow = pCrow + 1;
			playersMovesCount++;

		} else {
			invalid();
		}
	}

	/*
	 * Method which can swap the cells
	 */
	void swapCells(int currentRow, int newRow, int currentColumn, int newColumn) {
		boolean evaluate = true;// evaluate()
		if (evaluate) {
			String previousCell = maze[currentRow][currentColumn];
			maze[currentRow][currentColumn] = maze[newRow][newColumn];
			maze[newRow][newColumn] = previousCell;
			System.out.println();
			printMaze();
		}
	}

	void invalid() {
		System.out.println("Invalid move!");
		printMaze();
	}

	void moveSolvable(int rowNew, int colNew) {
		if ((maze[rowNew][colNew].equalsIgnoreCase("-")) && (isVisited[rowNew][colNew] == false)) {
			isVisited[rowNew][colNew] = true;
			isSolvable(rowNew, colNew);
		}
	}
}