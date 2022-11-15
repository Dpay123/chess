package dp.chess.model;

import java.util.ArrayList;
import java.util.HashMap;

import static dp.chess.model.PieceType.*;

public class Board {
    private HashMap<Integer, Square[]> board;

    public Board() {
        this.board = new HashMap<>();
        for (int i = 0; i < 8; i++) {
            Square[] row = new Square[8];
            for (int j = 0; j < 8; j++) {
                row[j] = new Square();
            }
            board.put(i, row);
        }
    }

    public HashMap<Integer, Square[]> getBoard() {
        return board;
    }

    public void setPiece(char c, int num, Piece p) {
        int row = 8 - num;
        int col = c - 65;
        board.get(row)[col].setPiece(p);

        if (p.getType() == KING) {
            // gather squares in a radius of 1 around
            for (int i = row - 1; i <= row + 1; i++) {
                // disregard rows not on the board
                if (i >= 0 && i <= 7) {
                    for (int j = col -1; j <= col + 1; j++) {
                        // disregard cols not on board
                        if (j >= 0 && j <= 7) {
                            Square s = board.get(i)[j];
                            // if unoccupied, add value
                            if (s.getPiece() == null) {
                                s.addValue();
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("----The Board----\n");
        str.append(" A B C D E F G H \n");
        int count = 8;
        for (int k: board.keySet()) {
            str.append("|");
            Square[] row = board.get(k);
            for (Square s : row) {
                str.append(s.toString() + "|");
            }
            str.append("  " + String.valueOf(count) + "\n");
            count--;
        }
        return str.toString();
    }
}
