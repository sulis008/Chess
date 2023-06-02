// Written by Alexander Sulistyo and Travis Pham, sulis008 and pham0459
public class Board {

    // Instance variables
    private Piece[][] board;

    //TODO:
    // Construct an object of type Board using given arguments.
    public Board() {
        board = new Piece[8][8];    // Chess board is 8x8 grid
    }

    // Accessor Methods

    //TODO:
    // Return the Piece object stored at a given row and column
    public Piece getPiece(int row, int col) {
        return board[row][col];
    }

    //TODO:
    // Update a single cell of the board to the new piece.
    public void setPiece(int row, int col, Piece piece) {
        board[row][col] = piece;
    }

    // Game functionality methods

    //TODO:
    // Moves a Piece object from one cell in the board to another, provided that
    // this movement is legal. Returns a boolean to signify success or failure.
    public boolean movePiece(int startRow, int startCol, int endRow, int endCol) {
        if (board[startRow][startCol].isMoveLegal(this, endRow, endCol)) {
            board[endRow][endCol] = board[startRow][startCol];  // Piece set to end position
            board[endRow][endCol].setPosition(endRow, endCol);  // Changes variables
            board[startRow][startCol] = null;   // Sets original spot to null
            return true;
        } else {
            return false;   // Only gets here if the move is illegal.
        }
    }

    //TODO:
    // Determines whether the game has been ended, i.e., if one player's King
    // has been captured.
    public boolean isGameOver() {
        int kingCount = 0;
        for (int r = 0; r < board.length; r++) {    // Loops through all rows
            for (int c = 0; c < board[0].length; c++) {// Loops through all columns
                if (board[r][c] == null) { continue; }
                boolean isKing1 = board[r][c].getCharacter() == '\u265a'; // Checks for white/black king
                boolean isKing2 = board[r][c].getCharacter() == '\u2654'; // "
                if (isKing1 || isKing2) { kingCount++; } // Increments kingCount if king is found
            }
        }
        return (kingCount < 2); // If game is over, there should be less than 2 kings.
    }

    //TODO:
    // Construct a String that represents the Board object's 2D array. Return
    // the fully constructed String.
    public String toString() {
        // result is initialized to the top row of numbers that index the columns of the board
        String result = "\u2001"+"\u2001"+"\u2001"+"0"+"\u2001"+"1"+"\u2001"+"2"+
                "\u2001"+"3"+"\u2001"+"4"+"\u2001"+"5"+"\u2001"+"6"+
                "\u2001"+"7";
        for (int i = 0; i < 8; i++) {
            result += "\n"+i+"\u2001"+"|";  // This line adds the row number, space and bar to start a new row.
            for (int j = 0; j < 8; j++) {   // This loop goes through the columns and adds each character
                if (board[i][j] == null) {  // If there is no piece there, just add a space.
                    result += "\u2001"+"|";
                } else {    // Else, add the character!
                    result += board[i][j].getCharacter()+"|";
                }
            }
        }
        return result;
    }

    //TODO:
    // Sets every cell of the array to null. For debugging and grading purposes.
    public void clear() {
        for (int r = 0; r < board.length; r++) {    // Loops through all rows
            for (int c = 0; c < board[0].length; c++) { // Loops through all columns
                board[r][c] = null;
            }
        }
    }

    // Movement helper functions

    //TODO:
    // Ensure that the player's chosen move is even remotely legal.
    // Returns a boolean to signify whether:
    // - 'start' and 'end' fall within the array's bounds.
    // - 'start' contains a Piece object, i.e., not null.
    // - Player's color and color of 'start' Piece match.
    // - 'end' contains either no Piece or a Piece of the opposite color.
    public boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {
        // Initializing boolean variables for each condition
        boolean startInBound = (startRow>=0&&startRow<board.length)&&(startCol>=0&&startCol<board[0].length);
        boolean endInBound = (endRow>=0&&endRow<board.length)&&(endCol>=0&&endCol<board[0].length);
        boolean startHasPiece = (board[startRow][startCol] != null);
        boolean sameColor = (isBlack == board[startRow][startCol].getIsBlack());
        boolean endPiece = (board[endRow][endCol]==null)||(board[endRow][endCol].getIsBlack()!=isBlack);
        // Returns true iff all the boolean variables are true
        return (startInBound && endInBound && startHasPiece && sameColor && endPiece);
    }

    //TODO:
    // Check whether the 'start' position and 'end' position are adjacent to each other
    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
        // Initializing boolean variables for each condition
        boolean adjacentRows = (endRow==startRow+1)||(endRow==startRow-1)||(endRow==startRow);
        boolean adjacentCols = (endCol==startCol+1)||(endCol==startCol-1)||(endCol==startCol);
        // Returns true iff both conditions are true
        return adjacentRows && adjacentCols;
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid horizontal move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one row.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {
        // Initializing/Declaring boolean variables for each condition
        boolean sameRow = (startRow == endRow);
        if (!sameRow) { return false; }
        boolean emptySpaces;
        // Following if-else block takes account for three different cases:
        // First, if the player is not trying to move horizontally
        // Second, if the move is adjacent and there is no need to check for empty spaces between
        // Third, we need to check if the spaces between the move are empty
        if (startCol == endCol) {
            return false;
        } else if (Math.abs(startCol-endCol) == 1) {
            emptySpaces = true;
        } else {
            // Consider whether the piece is moving left or right based on start and end values
            emptySpaces = true; // Assume the spaces are empty
            for (int col = Math.min(startCol, endCol)+1; col < Math.max(startCol, endCol); col++) {
                // If any spaces inbetween are found to contain a piece, immediately return false!
                if (board[startRow][col] != null) { return false; }
            }
        }
        return true;
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid vertical move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one column.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {
        // Initializing/Declaring boolean variables for each condition
        boolean sameCol = (startCol == endCol);
        if (!sameCol) { return false; }
        boolean emptySpaces;
        if (startRow == endRow) {
            return false;
        } else if (Math.abs(startRow - endRow)==1) {
            emptySpaces = true;
        } else {
            // Consider whether the piece is moving up or down based on start and end values
            emptySpaces = true; // Assume spaces are empty
            for (int row = Math.min(startRow, endRow)+1; row < Math.max(startRow, endRow); row++) {
                if (board[row][startCol] != null) { return false; }
            }
        }
        return true;
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid diagonal move.
    // Returns a boolean to signify whether:
    // - The path from 'start' to 'end' is diagonal... change in row and col.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {
        if (startRow==endRow||startCol==endCol) { return false; }
        // Checking direction of diagonal move
        boolean upRight = (endRow < startRow && startCol < endCol);
        boolean upLeft = (endRow < startRow && endCol < startCol);
        boolean downRight = (startRow < endRow && startCol < endCol);
        boolean downLeft = (startRow < endRow && endCol < startCol);
        //Check if spaces don't even need to be checked (moving only one diagonal square)
        if (Math.abs(startRow-endRow)==1&&Math.abs(startCol-endCol)==1) { return true; }
        //Verify empty spaces
        if (upRight || downLeft) { // Same checking algorithm
            int currentRow = Math.max(startRow, endRow)-1;
            int currentCol = Math.min(startCol, endCol)+1;
            while (currentRow > Math.min(startRow, endRow) && currentCol < Math.max(startCol, endCol)) {
                if (board[currentRow][currentCol] != null) { return false; }
                currentRow--;
                currentCol++;
            }
        } else if (upLeft || downRight) { // Same checking algorithm
            int currentRow = Math.min(startRow, endRow)+1;
            int currentCol = Math.min(startCol, endCol)+1;
            while (currentRow < Math.max(startRow, endRow)&&currentCol < Math.max(startCol, endCol)) {
                if (board[currentRow][currentCol] != null) { return false; }
                currentRow++;
                currentCol++;
            }
        }
        return true; // Diagonal verified at this point
    }
}
