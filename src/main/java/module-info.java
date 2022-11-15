module dp.chess {
    requires javafx.controls;
    requires javafx.fxml;


    opens dp.chess to javafx.fxml;
    exports dp.chess;
    exports dp.chess.controller;
    opens dp.chess.controller to javafx.fxml;
}