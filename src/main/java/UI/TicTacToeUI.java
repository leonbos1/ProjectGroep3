package src.main.java.UI;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import src.main.java.tictactoe.CheckRules;
import src.main.java.tictactoe.TicTacToe;

import java.util.concurrent.atomic.AtomicInteger;


//1 is X
//2 is O

public class TicTacToeUI extends Application {
    TicTacToe ticTacToe;

    int Xsize;
    int Ysize;
    int xWindowSize;
    int yWindowSize;
    int player;
    boolean online;
    Tile[][] tileArray = new Tile[8][8];

    public Parent createContent(TicTacToe ticTacToe, boolean online) {
        this.online = online;
        this.ticTacToe = ticTacToe;
        Xsize = ticTacToe.getBoard().getHeigth();
        Ysize = ticTacToe.getBoard().getWidth();
        xWindowSize = 800;
        yWindowSize = 800;
        player = ticTacToe.getPlayer();

        Pane root = new Pane();
        root.setPrefSize(xWindowSize,yWindowSize);

        for (int i = 0; i < Xsize; i++) {
            for (int j = 0; j < Ysize; j++) {
                Tile tile = new Tile(i, j);
                tile.setTranslateX(j * (yWindowSize / Ysize));
                tile.setTranslateY(i * (xWindowSize / Xsize));
                tileArray[i][j] = (tile);

                if (ticTacToe.getBoardArray()[i][j] == ticTacToe.getPlayer()) {
                    tileArray[i][j].drawX();
                } else if (ticTacToe.getBoardArray()[i][j] == ticTacToe.getOpponent(ticTacToe.getPlayer())) {
                    tileArray[i][j].drawO();
                }
                root.getChildren().add(tile);
            }
        }
        return root;
    }


    public static void main(String[] args) {

        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {

        primaryStage.setScene(new Scene(createContent(new TicTacToe(3,3), false)));
        primaryStage.show();

    }

    public void updateBoard() {


        for (int i = 0; i < Xsize; i++) {
            for (int j = 0; j < Ysize; j++) {

                if (ticTacToe.getBoardArray()[i][j] == ticTacToe.getPlayer()) {
                    tileArray[i][j].drawX();
                } else if (ticTacToe.getBoardArray()[i][j] == ticTacToe.getOpponent(ticTacToe.getPlayer())) {
                    tileArray[i][j].drawO();
                }

            }
        }

    }

    private class Tile extends StackPane {

        int row;
        int col;

        private Rectangle border = new Rectangle(xWindowSize/Xsize,yWindowSize/Ysize);
        private Text text = new Text();



        public void drawX() {
            text.setText("X");
        }

        public void drawO() {
            text.setText("O");
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public Tile(int row, int col) {
            AtomicInteger turn = new AtomicInteger(1);
            this.row = row;
            this.col = col;

            text.setFont(Font.font(100));

            border.setStrokeWidth(1);
            border.setFill(null);
            border.setStroke(Color.BLACK);

            setAlignment(Pos.CENTER);
            getChildren().addAll(border, text);

            if (!online) {
                setOnMouseClicked(event -> {
                    if (turn.get() == player) {
                        if (event.getButton() == MouseButton.PRIMARY) {
                            if (CheckRules.checkLegalMove(ticTacToe.getBoardArray(), getRow(), getCol())) {
                                ticTacToe.makeMove(getRow(), getCol());
                                updateBoard();
                                turn.set(changeTurn(turn.get()));
                            }
                        }
                    }
                    if (turn.get() == ticTacToe.getOpponent(player)) {
                        ticTacToe.aiMove(ticTacToe.getOpponent(player));
                        updateBoard();
                        turn.set(changeTurn(turn.get()));
                    }
                });
            }
        }
    }
    public static int changeTurn(int turn) {
        if (turn == 1) {return 2;}
        else {return 1;}
    }

}
