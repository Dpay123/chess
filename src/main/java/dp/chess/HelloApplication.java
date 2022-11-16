package dp.chess;

import dp.chess.model.Board;
import dp.chess.model.Piece;
import dp.chess.model.PieceType;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/dp/chess/view/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    /**The Main Application method.
     Create a Board and set pieces individually, clear the board, or reset.
     */
    public static void main(String[] args) {

        Board b = new Board();
        Piece king = new Piece(true, PieceType.KING);
        b.setPiece('A', 1, king);
        Piece bPawn = new Piece(false, PieceType.PAWN);
        Piece wPawn = new Piece(true, PieceType.PAWN);
        b.setPiece('C', 2, wPawn);
        b.setPiece('E', 4, wPawn);
        b.setPiece('E', 5, bPawn);
        b.setPiece('H', 4, bPawn);
        System.out.println(b);
        b.reset();
        System.out.println(b);

//        launch();
    }
}