// Written by Alexander Sulistyo and Travis Pham, sulis008 and pham0459
import java.util.Scanner;
public class Game {
    public static void main(String[] args) {
        Board board = new Board();
        Fen.load("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", board);
        System.out.println("Player 1 is White, Player 2 is Black.");
        Scanner scanner = new Scanner(System.in);
        boolean playerOneTurn = true;   // Separate player's turn value
        while (true) {
            System.out.println(board.toString());
            if (playerOneTurn) {
                System.out.println("Player 1's turn\nWhat is your move? (format: [start row] [start col]" +
                        " [end row] [end col])");
                int startRow = scanner.nextInt();
                int startCol = scanner.nextInt();
                int endRow = scanner.nextInt();
                int endCol = scanner.nextInt();
                // If statement checks for legal move and the piece belongs to player one.
                if (board.getPiece(startRow, startCol).isMoveLegal(board, endRow, endCol)&&!board.getPiece(startRow, startCol).getIsBlack()) {
                    board.movePiece(startRow, startCol, endRow, endCol);
                    // If statement checks if piece moved was a pawn, in which case we check if it needs to be promoted.
                    if (board.getPiece(endRow, endCol).getCharacter() == '\u2659'&&board.getPiece(endRow, endCol).checkPawnPromotion(board)) {
                        System.out.println("Pawn has been promoted! Which upgrade would you like to choose?" +
                                "\nEnter Queen, Rook, Bishop or Knight: ");
                        String input = scanner.next();
                        // Set the promotion pawn to corresponding choice of upgrade
                        switch (input) {
                            case "Queen":
                                board.setPiece(endRow, endCol, new Piece('\u2655', endRow, endCol, false));
                                System.out.println("Pawn promoted to Queen");
                                break;
                            case "Rook":
                                board.setPiece(endRow, endCol, new Piece('\u2656', endRow, endCol, false));
                                System.out.println("Pawn promoted to Rook");
                                break;
                            case "Bishop":
                                board.setPiece(endRow, endCol, new Piece('\u2657', endRow, endCol, false));
                                System.out.println("Pawn promoted to Bishop");
                                break;
                            case "Knight":
                                board.setPiece(endRow, endCol, new Piece('\u2658', endRow, endCol, false));
                                System.out.println("Pawn promoted to Knight");
                                break;
                            default:
                                System.out.println("Incorrect Option, Queen set as default.");
                                board.setPiece(endRow, endCol, new Piece('\u2655', endRow, endCol, false));
                        }
                    }
                } else {
                    // Move was determined not legal, but they can try again in case it was a typo.
                    System.out.println("Invalid move! Try again.");
                    continue;
                }
                // Check if game is over after every turn, otherwise switch player turns!
                if (board.isGameOver()) {
                    System.out.println("Player 1 has won the game!");
                    break;
                } else {
                    playerOneTurn = false;
                }
            }
            // Means it is player two's turn
            else {
                System.out.println("Player 2's turn\nWhat is your move? (format: [start row] [start col]" +
                        " [end row] [end col])");
                int startRow = scanner.nextInt();
                int startCol = scanner.nextInt();
                int endRow = scanner.nextInt();
                int endCol = scanner.nextInt();
                // Same code as player one turn, but with a different color piece in mind.
                if (board.getPiece(startRow, startCol).isMoveLegal(board, endRow, endCol)&&board.getPiece(startRow, startCol).getIsBlack()) {
                    board.movePiece(startRow, startCol, endRow, endCol);
                    if (board.getPiece(endRow, endCol).getCharacter() == '\u265F'&&board.getPiece(endRow, endCol).checkPawnPromotion(board)) {
                        System.out.println("Pawn has been promoted! Which upgrade would you like to choose?" +
                                "\nEnter Queen, Rook, Bishop or Knight: ");
                        String input = scanner.next();
                        switch (input) {
                            case "Queen":
                                board.setPiece(endRow, endCol, new Piece('\u265B', endRow, endCol, true));
                                System.out.println("Pawn promoted to Queen");
                                break;
                            case "Rook":
                                board.setPiece(endRow, endCol, new Piece('\u265C', endRow, endCol, true));
                                System.out.println("Pawn promoted to Rook");
                                break;
                            case "Bishop":
                                board.setPiece(endRow, endCol, new Piece('\u265D', endRow, endCol, true));
                                System.out.println("Pawn promoted to Bishop");
                                break;
                            case "Knight":
                                board.setPiece(endRow, endCol, new Piece('\u265E', endRow, endCol, true));
                                System.out.println("Pawn promoted to Knight");
                                break;
                            default:
                                System.out.println("Incorrect Option, Queen set as default.");
                                board.setPiece(endRow, endCol, new Piece('\u265B', endRow, endCol, true));
                        }
                    }
                } else {
                    System.out.println("Invalid move! Try again.");
                    continue;
                }
                if (board.isGameOver()) {
                    System.out.println("Player 2 has won the game!");
                    break;
                } else {
                    playerOneTurn = true;
                }
            }
        }
        System.out.println("Thanks for playing!");
    }
}
