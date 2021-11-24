package src.main.java.UI;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.control.Button;
import src.main.java.reversi.CheckRulesReversi;
import src.main.java.reversi.Reversi;

import javax.swing.border.Border;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;


//1 is X
//2 is O

public class ReversiUI extends Application {
    src.main.java.reversi.Reversi reversi = new src.main.java.reversi.Reversi(1);
    CheckRulesReversi rules = new CheckRulesReversi(reversi.getBoard(), 1);
    int Xsize = reversi.getBoard().getHeigth();
    int Ysize = reversi.getBoard().getWidth();
    int xWindowSize = 800;
    int yWindowSize = 800;
    int player = reversi.getPlayer();
    String game;

    Tile[][] tileArray = new Tile[Xsize][Ysize];

    private Parent createContent() {

        Pane root = new Pane();
        root.setPrefSize(xWindowSize,yWindowSize);

        for (int i = 0; i < Xsize; i++) {
            for (int j = 0; j < Ysize; j++) {
                Tile tile = new Tile(i, j);
                tile.setTranslateX(j * (yWindowSize / Ysize));
                tile.setTranslateY(i * (xWindowSize / Xsize));
                tileArray[i][j] = (tile);

                if (reversi.getBoardArray()[i][j] == reversi.getPlayer()) {
                    tileArray[i][j].drawX();
                } else if (reversi.getBoardArray()[i][j] == rules.getOpponent(reversi.getPlayer())) {
                    tileArray[i][j].drawO();
                }
                if (rules.checkLegalMove(i, j, player)) {
                    tileArray[i][j].setborder(Color.GREEN);
                } else {
                    tileArray[i][j].setborder(Color.BLACK);
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

        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();

    }

    private void updateBoard() {

        Color black = Color.BLACK;
        Color green = Color.GREEN;

        for (int i = 0; i < Xsize; i++) {
            for (int j = 0; j < Ysize; j++) {

                if (reversi.getBoardArray()[i][j] == reversi.getPlayer()) {
                    tileArray[i][j].drawX();
                } else if (reversi.getBoardArray()[i][j] == rules.getOpponent(reversi.getPlayer())) {
                    tileArray[i][j].drawO();
                }
                if (rules.checkLegalMove(i,j,player)) {
                    tileArray[i][j].setborder(green);
                } else {
                    tileArray[i][j].setborder(black);
                }


            }
        }

    }

    private class Tile extends StackPane {

        int row;
        int col;

        private Rectangle border = new Rectangle(xWindowSize/Xsize,yWindowSize/Ysize);
        private Text text = new Text();

        private void setborder(Color color) {
            border.setStroke(color);
            if (color == Color.GREEN) {
                border.setStrokeWidth(3);
            } else {
                border.setStrokeWidth(1);
            }
        }


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

            text.setFont(Font.font(72));

            border.setStrokeWidth(1);
            border.setFill(null);
            border.setStroke(Color.BLACK);

            setAlignment(Pos.CENTER);
            getChildren().addAll(border, text);

            setOnMouseClicked(event -> {
                if (turn.get() == player) {
                    if (event.getButton() == MouseButton.PRIMARY) {
                        if (rules.checkLegalMove(getRow(), getCol(), reversi.getPlayer())) {
                            reversi.playerMove(getRow(), getCol());
                            updateBoard();
                            turn.set(changeTurn(turn.get()));
                        }
                    }
                }
                if (turn.get() == src.main.java.reversi.Reversi.getOpponent(player)) {
                    reversi.AIMove(src.main.java.reversi.Reversi.getOpponent(player));
                    updateBoard();
                    turn.set(changeTurn(turn.get()));
                }
            });
        }
    }
    public static int changeTurn(int turn) {
        if (turn == 1) {return 2;}
        else {return 1;}
    }

}
