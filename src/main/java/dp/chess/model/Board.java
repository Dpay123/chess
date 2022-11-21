package dp.chess.model;

import java.util.ArrayList;
import java.util.List;

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

    /**Load test data onto board and check for known accuracy.
     Call this method to set an example board with pieces for both teams.
     The test data is converted to a string representation of all 64 squares
     (each square either a value or a char for the piece occupying it) and compared
     against a string representing the known value of the test data.
     Prints a statement of whether the test data was correctly calculated or not.
     NOTE - this should be abstracted as a unit test.
     */
    public void setCheckTestData() {
        // black pieces
        Piece rookB = new Piece(false, PieceType.ROOK);
        Piece pawnB = new Piece(false, PieceType.PAWN);
        Piece bishopB = new Piece(false, PieceType.BISHOP);
        Piece queenB = new Piece(false, PieceType.QUEEN);
        Piece knightB = new Piece(false, PieceType.KNIGHT);
        Piece kingB = new Piece(false, PieceType.KING);
        this.setPiece('G', 4, knightB);
        this.setPiece('E', 5, pawnB);
        this.setPiece('F', 5, bishopB);
        this.setPiece('B', 6, queenB);
        this.setPiece('H', 6, pawnB);
        this.setPiece('A', 7, pawnB);
        this.setPiece('B', 7, pawnB);
        this.setPiece('E', 7, bishopB);
        this.setPiece('A', 8, rookB);
        this.setPiece('F', 8, rookB);
        this.setPiece('H', 8, kingB);
        // white Pieces
        Piece rookW = new Piece(true, PieceType.ROOK);
        Piece pawnW = new Piece(true, PieceType.PAWN);
        Piece bishopW = new Piece(true, PieceType.BISHOP);
        Piece queenW = new Piece(true, PieceType.QUEEN);
        Piece knightW = new Piece(true, PieceType.KNIGHT);
        Piece kingW = new Piece(true, PieceType.KING);
        this.setPiece('C', 4, bishopW);
        this.setPiece('A', 3, pawnW);
        this.setPiece('B', 2, pawnW);
        this.setPiece('C', 2, pawnW);
        this.setPiece('D', 2, bishopW);
        this.setPiece('E', 2, knightW);
        this.setPiece('F', 2, pawnW);
        this.setPiece('G', 2, pawnW);
        this.setPiece('H', 2, pawnW);
        this.setPiece('A', 1, rookB);
        this.setPiece('D', 1, queenB);
        this.setPiece('F', 1, rookW);
        this.setPiece('G', 1, kingW);
        System.out.println("Test Data is set");

        // confirm correctness
        System.out.println("Calculating...");
        this.checkSquares();
        // gather squares
        // build board string representation to compare
        StringBuilder testStr = new StringBuilder();
        for (Square[] row : board) {
            for (Square s : row) {
                if (s.getPiece() == null) {
                    testStr.append(s.getValue());
                }
                else {
                    testStr.append(s.getPiece().getSymbol());
                }
            }
        }

        // compare to known list of values of test data
        String valueStr = "R2342R3KPP11B2123Q12342P3221PB2114B223N2P33231212PPBNPPPR24Q3RK1";
        if(testStr.toString().equals(valueStr)) {
            System.out.println("Check Complete...correct!");
        } else {
            System.out.println("Error: calculation does not match known test data values");
            System.out.println("Test Data string: " + testStr);
            System.out.println("Expected values:  " + valueStr);
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

        if (p.getType() == BISHOP) {
            calculateBishop(row, col);
        }

        if (p.getType() == QUEEN) {
            calculateQueen(row, col);
        }

        if (p.getType() == KNIGHT) {
            calculateKnight(row,col);
        }

    }

    /**Calculate the possible moves of a Knight piece.
     A single move of a night consists of a first move in a cardinal direction,
     followed by a second move in a perpendicular direction. One of the moves must
     traverse a single square distance, while the other move must traverse 2 squares,
     but the order does not matter. A knight can traverse over pieces.
     @param row the int row location
     @param col the int col location
     */
    private void calculateKnight(int row, int col) {
        ArrayList<Square> squares = new ArrayList<>();
        // define 4 gutters possible
        int upRow = row - 2;
        int downRow = row + 2;
        int leftCol = col - 2;
        int rightCol = col + 2;
        // check upwards move 2 distance
        if (upRow >= 0) {
            if (col - 1 >= 0) {
                squares.add(board[upRow][col-1]);
            }
            if (col + 1 <= 7) {
                squares.add(board[upRow][col+1]);
            }
        }
        // check left move 2 distance
        if (leftCol >= 0) {
            if (row - 1 >= 0) {
                squares.add(board[row-1][leftCol]);
            }
            if (row + 1 <= 7) {
                squares.add(board[row+1][leftCol]);
            }
        }
        // check down move 2 distance
        if (downRow <= 7) {
            if (col - 1 >= 0) {
                squares.add(board[downRow][col-1]);
            }
            if (col + 1 <= 7) {
                squares.add(board[downRow][col+1]);
            }
        }
        // check right move 2 distance
        if (rightCol <= 7) {
            if (row - 1 >= 0) {
                squares.add(board[row-1][rightCol]);
            }
            if (row + 1 <= 7) {
                squares.add(board[row+1][rightCol]);
            }
        }
        // add value to gathered squares if not occupied
        for (Square s : squares) {
            if (s.getPiece() == null) {
                s.addValue();
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

    /**Calculate possible moves diagonally from a position.
     @param row the row location
     @param col the col location
     @return the list of squares that can be reached
     */
    private ArrayList<Square> calculateDiagonals(int row, int col) {
        // the flags to indicate whether a piece/board edge is encountered
        boolean upLeftFlag = true;
        boolean upRightFlag = true;
        boolean downLeftFlag = true;
        boolean downRightFlag = true;
        // the radius being checked around the initial position
        int radius = 1;
        ArrayList<Square> squares = new ArrayList<>();

        while (radius < 8) {

            // check up left
            if (upLeftFlag && row - radius >= 0 && col - radius >= 0) {
                Square s = board[row-radius][col-radius];
                if (s.getPiece() != null) {
                    upLeftFlag = false;
                } else {
                    squares.add(s);
                }
            }
            // check up right
            if (upRightFlag && row - radius >= 0 && col + radius <= 7) {
                Square s = board[row-radius][col+radius];
                if (s.getPiece() != null) {
                    upRightFlag = false;
                } else {
                    squares.add(s);
                }
            }
            // check down left
            if (downLeftFlag && row + radius <= 7 && col - radius >= 0) {
                Square s = board[row+radius][col-radius];
                if (s.getPiece() != null) {
                    downLeftFlag = false;
                } else {
                    squares.add(s);
                }
            }
            // check down right
            if (downRightFlag && row + radius <= 7 && col + radius <= 7) {
                Square s = board[row+radius][col+radius];
                if (s.getPiece() != null) {
                    downRightFlag = false;
                } else {
                    squares.add(s);
                }
            }
            // step outward 1 square
            radius++;
        }
        return squares;
    }

    /**Calculate possible moves horizontally/vertically from a position.
     @param row the row location
     @param col the col location
     @return the list of squares that can be reached
     */
    private ArrayList<Square> calculateCross(int row, int col) {
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
        return squares;
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

    /**Calculate the possible moves of a Rook piece.
     A rook can move any length horizontally or vertically (within its current
     row or column) but it can only pick one direction to move, and cannot move
     past an occupied space.
     @param row the row location
     @param col the col location
     */
    private void calculateRook(int row, int col) {
        ArrayList<Square> squares = calculateCross(row, col);
        // increment all squares collected
        for (Square s : squares) {
            s.addValue();
        }
    }

    /**Calculate the possible moves of a Queen piece.
     A queen can move any length in all 4 cardinal directions as well as diagonally,
     but cannot cross over a piece.
     @param row the row location
     @param col the col location
     */
    private void calculateQueen(int row, int col) {
        ArrayList<Square> diagSquares = calculateDiagonals(row, col);
        ArrayList<Square> crossSquares = calculateCross(row, col);
        for (Square s : diagSquares) {
            s.addValue();
        }
        for (Square s : crossSquares) {
            s.addValue();
        }
    }

    /**Calculate the possible moves of a Bishop piece.
     A bishop can move diagonally from its position, any distance where there
     is no piece in its path, but it can only travel in one direction per move.
     */
    private void calculateBishop(int row, int col) {
        ArrayList<Square> squares = calculateDiagonals(row, col);
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
