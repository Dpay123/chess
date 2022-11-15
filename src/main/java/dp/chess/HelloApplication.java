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

    public static void main(String[] args) {

        Board b = new Board();
        Piece king = new Piece(true, PieceType.KING);
        b.setPiece('A', 1, king);
        b.setPiece('H', 8, king);
        System.out.println(b);

//        launch();
    }
}