// Written by Alexander Sulistyo and Travis Pham, sulis008 and pham0459
public class Queen {
    private int row, col;
    private boolean isBlack;
    public Queen(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        boolean diagonalMove = (row != endRow)&&(col != endCol);
        boolean verticalMove = (row != endRow)&&(col == endCol);
        boolean horizontalMove = (row == endRow)&&(col != endCol);
        if (diagonalMove) {
            return board.verifySourceAndDestination(row, col, endRow, endCol, isBlack)
                    &&board.verifyDiagonal(row, col, endRow, endCol);
        } else if (verticalMove) {
            return board.verifySourceAndDestination(row, col, endRow, endCol, isBlack)
                    &&board.verifyVertical(row, col, endRow, endCol);
        } else if (horizontalMove) {
            return board.verifySourceAndDestination(row, col, endRow, endCol, isBlack)
                    &&board.verifyHorizontal(row, col, endRow, endCol);
        } else {
            return false;
        }
    }
}
