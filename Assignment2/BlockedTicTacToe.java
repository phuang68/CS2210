
public class BlockedTicTacToe {
	private int board_size; // the length and width of the board
	private int inline; // Number of a single symbol needed in a row to win
	private int max_levels;// the maximum level of the game tree that will be explored by the program
	private char[][] gameBoard; // declare an empty dimensional char array of the board
	private final int defaultSize = 5000;// the default size of the dictionary
	private TTTDictionary configurations;// the TTTDictionary

	public BlockedTicTacToe(int board_size, int inline, int max_levels) {
		this.board_size = board_size;
		this.inline = inline;
		this.max_levels = max_levels;
		this.gameBoard = new char[board_size][board_size];
		// It stores a space to every entry of gameBoard
		for (int i = 0; i < board_size; i++)
			for (int j = 0; j < board_size; j++)
				gameBoard[i][j] = ' ';
	}

	// return an empty TTTDictionary of the size that you have selected.
	public TTTDictionary createDictionary() {
		return this.configurations = new TTTDictionary(defaultSize);
	}

	// The method represents the gameBoard as a string as described above; then it
	// checks whether the string rep-
	// resenting the gameBoard is in the configurations dictionary
	public int repeatedConfig(TTTDictionary configurations) {
		String config = "";// declare a string that stores the configuration string.
		for (int i = 0; i < board_size; i++)
			for (int j = 0; j < board_size; j++)
				config = config + gameBoard[i][j];// return a string of the configuration of the gameBoard.
		if (configurations.get(config) != null)// check if the configuration is in the dictionary,if not return -1
			return configurations.get(config).getScore();
		else
			return -1;
	}

	// The method represents the content of gameBoard as a string as described
	// above; then it
	// inserts this string, score and level in the configurations dictionary.
	public void insertConfig(TTTDictionary configurations, int score, int level) throws DuplicatedKeyException {
		String config = "";// declare a string that stores the configuration string.
		for (int i = 0; i < board_size; i++)
			for (int j = 0; j < board_size; j++)
				config = config + gameBoard[i][j];// return a string of the configuration of the gameBoard.
		TTTRecord tR = new TTTRecord(config, score, level);// put the TTTRecord based on the generated configuration
															// string
		try {// check if the configuration is in the dictionary,if it is, it throws the
				// exception
			configurations.put(tR);
		} catch (DuplicatedKeyException e) {
			System.out.println("Can't not insert the configuration!");
		}
	}

	// The method stores the character symbol in gameBoard[row][col].
	public void storePlay(int row, int col, char symbol) {
		this.gameBoard[row][col] = symbol;
	}

	// This method returns true if gameBoard[row][col] is ' '; otherwise it returns
	// false.
	public boolean squareIsEmpty(int row, int col) {
		if (this.gameBoard[row][col] == ' ')
			return true;
		else
			return false;
	}

	// This method returns true if there are k adjacent occurrences of
	// symbol in the same row, column, or diagonal of gameBoard, where k is the
	// number of required
	// symbols in-line needed to win the game.
	public boolean wins(char symbol) {
		if (horizontalWins(symbol) || verticalWins(symbol) || diagonalWinsLTR(symbol) || diagonalWinsRTL(symbol))
			return true;
		else
			return false;
	}

	// This method returns true if there are k adjacent occurrences of symbol in the
	// same line horizontally
	private boolean horizontalWins(char symbol) {
		int check = 0;
		for (int i = 0; i < board_size; i++) {
			for (int j = 0; j < board_size; j++)
				if (gameBoard[i][j] == symbol)
					check++;// increment the factor to check
				else
					check = 0;// if it is not the case when they are in a line,set the check to zero
			if (check >= inline)
				return true;
		}
		return false;
	}

	// This method returns true if there are k adjacent occurrences of symbol in the
	// same line vertically
	private boolean verticalWins(char symbol) {
		int check = 0;
		for (int i = 0; i < board_size; i++) {
			for (int j = 0; j < board_size; j++)
				if (gameBoard[j][i] == symbol)
					check++;
				else
					check = 0;
			if (check >= inline)
				return true;
		}
		return false;
	}

	// This method returns true if there are k adjacent occurrences of symbol in the
	// same line diagonally from left to right
	private boolean diagonalWinsLTR(char symbol) {
		int check = 0;
		for (int i = 0; i < board_size; i++)
			if (gameBoard[i][i] == symbol)
				check++;
			else
				check = 0;
		if (check >= inline)
			return true;
		else
			return false;
	}

	// This method returns true if there are k adjacent occurrences of symbol in the
	// same line diagonally from right to left
	private boolean diagonalWinsRTL(char symbol) {
		int check = 0;
		for (int i = 0; i < board_size; i++)
			for (int j = 0; j < board_size; j++)
				if (i + j == (inline - 1) && gameBoard[i][j] == symbol)
					check++;
				else
					check = 0;
		if (check >= inline)
			return true;
		else
			return false;
	}

	// Returns true if gameBoard has no empty positions left and no player has won
	// the game
	public boolean isDraw() {
		int checkEmpty = 0;
		for (int i = 0; i < board_size; i++)
			for (int j = 0; j < board_size; j++)
				if (gameBoard[i][j] == ' ')
					checkEmpty++;
		if (!(checkEmpty > 0) && !(wins('x')) && !(wins('o')))
			return true;
		return false;
	}

	// The method returns values depending on who wins the game
	public int evalBoard() {
		if (wins('o')) {
			return 3;
		} else if (wins('x')) {
			return 0;
		} else if (isDraw()) {
			return 1;
		} else {
			return 2;
		}
	}
}
