package dp.chess.model;

public class Square {
    private int value;
    private Piece piece;

    public Square() {
        this.value = 0;
    }

    public Square(Piece piece) {
        this.piece = piece;
        this.value = 0;
    }

    public int getValue() {
        return value;
    }

    public void addValue() {
        this.value += 1;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    @Override
    public String toString() {
        if (this.piece == null) {
            return String.valueOf(value);
        }
        else {
            return String.valueOf(this.piece.getSymbol());
        }
    }
}
