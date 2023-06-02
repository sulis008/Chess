// Written by Alexander Sulistyo and Travis Pham, sulis008 and pham0459
public class Rook {
    private int row;
    private int col;
    private boolean isBlack;
    public Rook(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        boolean horizontalMove = row == endRow;
        boolean verticalMove = col == endCol;
        if (horizontalMove && verticalMove) {
            return false;
        } else if (horizontalMove) {
            return board.verifyHorizontal(row, col, endRow, endCol)
                    &&board.verifySourceAndDestination(row, col, endRow, endCol, isBlack);
        } else if (verticalMove) {
            return board.verifyVertical(row, col, endRow, endCol)
                    &&board.verifySourceAndDestination(row, col, endRow, endCol, isBlack);
        } else {
            return false;
        }
    }
}
