package dp.chess.model;

public class GUI {
    private Board board;

    public GUI() {
        this.board = new Board();
    }

    public void start() {
        // run the application
        board.setCheckTestData();
        board.showBoard();
    }
}
