// Written by Alexander Sulistyo and Travis Pham, sulis008 and pham0459
public class King {
    private int row, col;
    private boolean isBlack;
    public King(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        // King's move is legal if source and destination is valid and move is only adjacent
        return board.verifySourceAndDestination(row, col, endRow, endCol, isBlack)
                &&board.verifyAdjacent(row, col, endRow, endCol);
    }
}
