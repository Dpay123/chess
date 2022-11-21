package dp.chess;

import dp.chess.model.Board;
import dp.chess.model.GUI;
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

        GUI gui = new GUI();
        gui.start();

//        launch();
    }
}