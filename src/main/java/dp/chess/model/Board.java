package dp.chess.model;

import java.util.ArrayList;

import static dp.chess.model.PieceType.*;

/**Board is an 8x8 matrix of Squares, each Square can have a piece.
 Clear the board, or reset, set individual pieces. A board has
 8 rows (labeled 8 to 1 in descending order) and 8 columns (labeled
 A to H in ascending order. The location of a Square is given by row/column,
 example "A1" is the last row and first column.
 */
public class Board {

    /**8x8 matrix of Squares, contains all board details.*/
    private Square[][] board;

    /**When a board is created, new squares are initialized.*/
    public Board() {
        this.board = new Square[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Square();
            }
        }
    }

    /**Get the current board.
     @return the board
     */
    public Square[][] getBoard() {
        return board;
    }

    /**Clears all squares of their pieces.*/
    public void clearBoard() {
        System.out.println("Clearing Board...");
        // clear all squares
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Square();
            }
        }
    }

    /**Resets the board to default game positions.*/
    public void reset() {
        clearBoard();
        System.out.println("Resetting Board...");
        // set black first row
        board[0][0].setPiece(new Piece(false, ROOK));
        board[0][7].setPiece(new Piece(false, ROOK));
        board[0][1].setPiece(new Piece(false, KNIGHT));
        board[0][6].setPiece(new Piece(false, KNIGHT));
        board[0][2].setPiece(new Piece(false, BISHOP));
        board[0][5].setPiece(new Piece(false, BISHOP));
        board[0][3].setPiece(new Piece(false, QUEEN));
        board[0][4].setPiece(new Piece(false, KING));
        // set black second row
        for (int i = 0; i < 8; i++) {
            board[1][i].setPiece(new Piece(false, PAWN));
        }
        // set the white first row
        board[7][0].setPiece(new Piece(true, ROOK));
        board[7][7].setPiece(new Piece(true, ROOK));
        board[7][1].setPiece(new Piece(true, KNIGHT));
        board[7][6].setPiece(new Piece(true, KNIGHT));
        board[7][2].setPiece(new Piece(true, BISHOP));
        board[7][5].setPiece(new Piece(true, BISHOP));
        board[7][3].setPiece(new Piece(true, QUEEN));
        board[7][4].setPiece(new Piece(true, KING));
        // set the white second row
        for (int i = 0; i < 8; i++) {
            board[6][i].setPiece(new Piece(true, PAWN));
        }
    }

    /**Check each square on the board, and if a piece is detected,
     call the calculate function for that piece. If no piece, do nothing.
     */
    private void checkSquares() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece p = board[i][j].getPiece();
                if (p != null) {
                    calculate(p, i, j);
                }
            }
        }
    }

    /**Identify the piece type and call the appropriate calculate method for that piece.
     @param p the Piece to calculate
     @param row the integer representing the row position
     @param col the integer representing the col position
     */
    private void calculate(Piece p, int row, int col) {

        if (p.getType() == KING) {
            calculateKing(row, col);
        }

        if (p.getType() == PAWN && (row <= 6 && row >= 1)) {
            calculatePawn(p, row, col);
        }

        if (p.getType() == ROOK) {
            calculateRook(row, col);
        }

    }

    /**Calculate the possible moves of a Rook piece.
     A rook can move any length horizontally or vertically (within its current
     row or column) but it can only pick one direction to move, and cannot move
     past an occupied space.
     @param row the row location
     @param col the col location
     */
    private void calculateRook(int row, int col) {
        ArrayList<Square> squares = new ArrayList<>();
        // check up
        for (int i = row - 1; i >= 0; i--) {
            // stop checking once a piece is encountered
            Square s = board[i][col];
            if (s.getPiece() != null) {
                break;
            }
            squares.add(s);
        }
        // check down
        for (int i = row + 1; i <= 7; i++) {
            // stop checking once a piece is encountered
            Square s = board[i][col];
            if (s.getPiece() != null) {
                break;
            }
            squares.add(s);
        }
        // check left
        for (int i = col - 1; i >= 0; i--) {
            // stop checking once a piece is encountered
            Square s = board[row][i];
            if (s.getPiece() != null) {
                break;
            }
            squares.add(s);
        }
        // check right
        for (int i = col + 1; i <= 7; i++) {
            // stop checking once a piece is encountered
            Square s = board[row][i];
            if (s.getPiece() != null) {
                break;
            }
            squares.add(s);
        }
        // increment all squares collected
        for (Square s : squares) {
            s.addValue();
        }
    }

    /**Calculate the possible moves of a King piece.
     A king can move within a one square radius of its position.
     @param row the int row location of the King
     @param col the int col location of the King
     */
    private void calculateKing(int row, int col) {
        for (int i = row - 1; i <= row + 1; i++) {
            // disregard rows not on the board
            if (i >= 0 && i <= 7) {
                for (int j = col -1; j <= col + 1; j++) {
                    // disregard cols not on board
                    if (j >= 0 && j <= 7) {
                        Square s = board[i][j];
                        // if unoccupied, add value
                        if (s.getPiece() == null) {
                            s.addValue();
                        }
                    }
                }
            }
        }
    }

    /**Calculate the possible moves of a Pawn piece.
     A pawn can only move if located on rows 1-6 and there is no piece in its path.
     A pawn at its starting location (row 1 for Black Pawn, row 6 White Pawn)
     is able to move 2 spaces forward; otherwise it can move one space forward.
     For a Black Pawn, forward indicates moving down the board (row ascending);
     a White Pawn moves forward up the board (row descending).
     @param p the Pawn to calculate
     @param row the row location
     @param col the col location
     */
    private void calculatePawn(Piece p, int row, int col) {
        int reach = 1;
        ArrayList<Square> squares = new ArrayList<>();

        // white pan can only move up (row decreasing)
        if (p.getTeam().equals("White")) {
            // calculate reach (if starting position = 2, else =1)
            if (row == 6) {
                reach = 2;
            }
            // gather squares within reach
            for (int i = 1; i <= reach; i++) {
                Square s = board[row-i][col];
                if (s.getPiece() != null) {
                    break;
                }
                squares.add(s);
            }
        }
        // black pawn can only move down (row increasing)
        else {
            // calculate reach (if starting position = 2, else =1)
            if (row == 1) {
                reach = 2;
            }
            // gather squares within reach
            for (int i = 1; i <= reach; i++) {
                Square s = board[row+i][col];
                if (s.getPiece() != null) {
                    break;
                }
                squares.add(s);
            }
        }

        // increment squares
        for (Square s : squares) {
            s.addValue();
        }
    }

    /**Set the piece to a square, given the row/col location of the square.
     Location of the square is given in "A1" format, where the column is
     indicated by a letter from A to H, and the row is indicated by an integer
     from 0-7.
     */
    public void setPiece(char c, int num, Piece p) {
        int row = 8 - num;
        int col = c - 65;
        board[row][col].setPiece(p);

    }

    /**Return a rudimentary string representation of the board.
     @return a string representing the board.
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("----The Board----\n");
        str.append(" A B C D E F G H \n");
        int count = 8;
        for (int i = 0; i < 8; i++) {
            str.append("|");
            for (int j = 0; j < 8; j++) {
                str.append(board[i][j].toString() + "|");
            }
            str.append("  " + String.valueOf(count) + "\n");
            count--;
        }
        return str.toString();
    }

    /**Calculate the board and print as a String.*/
    public void showBoard() {
        checkSquares();
        System.out.println(this);
    }
}
