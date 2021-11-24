import javafx.scene.layout.GridPane;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.application.Application;
import java.util.ArrayList;
import java.util.List;

public class UI extends Application implements Runnable {
    public GridPane board;
    public Scene scene;
    public List<Button> buttonsList;
    public ArrayList<ArrayList<Button>> buttonGrid;
    public InputHandler boardButtonHandler;
    private static Stage _stage;

    public UI() {

    }

    @Override
    public void start(Stage primaryStage) {
        _stage = new Stage();

    }

    @Override
    public void run() {
        _stage = new Stage();
    }

    public void setupBoard(int min, int max, int pref, int boardSize) {
        for (int col = 0; col < boardSize; col++) {
            buttonGrid.add(new ArrayList<>());
            for (int row = 0; row < boardSize; row++) {
                BoardButton boardButton = new BoardButton("", col, row);
                buttonGrid.get(col).add(boardButton);
                buttonsList.add(boardButton);

                boardButton.setMinSize(min, min);
                boardButton.setMaxSize(max, max);
                boardButton.setPrefSize(pref, pref);
            }
        }

        for (int i = 0; i < buttonsList.size(); i++) {
            buttonsList.get(i).setOnAction(this.boardButtonHandler);
        }

        int count = 0;
        for (int col = 0; col < boardSize; col++) {
            for (int row = 0; row < boardSize; row++) {
                board.add(buttonsList.get(count), row, col);
                count++;
            }
        }
    }
}