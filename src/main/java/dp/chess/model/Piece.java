package dp.chess.model;

public class Piece {
    private boolean isWhite;
    private PieceType type;
    private char symbol;

    public Piece (boolean b, PieceType pt) {
        this.isWhite = b;
        this.type = pt;
        this.symbol = pt.name().charAt(0);
    }

    public String getTeam() {
        if (isWhite) {
            return "White";
        }
        else {
            return "Black";
        }
    }

    public PieceType getType() {
        return type;
    }

    public void setType(PieceType type) {
        this.type = type;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return getTeam() + " Piece {" +
                "type=" + type +
                ", symbol=" + symbol +
                '}';
    }
}
