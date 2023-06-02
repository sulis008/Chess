// Written by Alexander Sulistyo and Travis Pham, sulis008 and pham0459
public class Knight {
    private int row, col;
    private boolean isBlack;
    public Knight(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (isBlack && board.getPiece(endRow, endCol) != null && board.getPiece(endRow, endCol).getIsBlack()) {
            return false;
        }
        boolean verticalMove = (Math.abs(row - endRow) == 2);
        boolean horizontalMove = (Math.abs(col - endCol) == 2);
        boolean oneSpaceAway;
        if (verticalMove) {
            oneSpaceAway = (Math.abs(col - endCol) == 1);
            return (board.verifySourceAndDestination(row, col, endRow, endCol, isBlack)&&oneSpaceAway);
        }
        if (horizontalMove) {
            oneSpaceAway = (Math.abs(row - endRow) == 1);
            return (board.verifySourceAndDestination(row, col, endRow, endCol, isBlack)&&oneSpaceAway);
        } else {
            return false;
        }
    }
}
