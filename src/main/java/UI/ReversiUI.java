package src.main.java.UI;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    Reversi reversi;
    CheckRulesReversi rules;
    int xWindowSize;
    int yWindowSize;
    int player;
    boolean online;
    int turn;
    String game;
    Tile[][] tileArray = new Tile[8][8];


    public Parent createContent(Reversi reversi, boolean online) {
        this.online = online;
        this.reversi = reversi;
        this.rules = new CheckRulesReversi(reversi.getBoard(), reversi.getPlayer());
        this.xWindowSize = 800;
        this.yWindowSize = 800;
        this.player = reversi.getPlayer();
        this.turn = player;

        Pane root = new Pane();
        root.setPrefSize(xWindowSize,yWindowSize);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Tile tile = new Tile(i, j);
                tile.setTranslateX(j * (yWindowSize / 8));
                tile.setTranslateY(i * (xWindowSize / 8));
                tileArray[i][j] = tile;

                if (reversi.getBoardArray()[i][j] == reversi.getPlayer()) {
                    tileArray[i][j].drawX();
                } else if (reversi.getBoardArray()[i][j] == rules.getOpponent(reversi.getPlayer())) {
                    tileArray[i][j].drawO();
                }
                if (rules.checkLegalMove(i, j, player)) {
                    tileArray[i][j].setborder(Color.BLUE);
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
        primaryStage.setScene(new Scene(createContent(new Reversi(1), false)));
        primaryStage.show();

    }

    public void resetBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tileArray[i][j].clearText();
            }
        }
    }

    public void updateBoard() {
        reversi.getBoard().showBoard();

        Color black = Color.BLACK;
        Color blue = Color.BLUE;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                if (reversi.getBoardArray()[i][j] == reversi.getPlayer()) {
                    tileArray[i][j].drawX();
                } else if (reversi.getBoardArray()[i][j] == rules.getOpponent(reversi.getPlayer())) {
                    tileArray[i][j].drawO();
                }
                else {tileArray[i][j].clearText();};
                if (rules.checkLegalMove(i,j,player)) {
                    tileArray[i][j].setborder(blue);
                } else {
                    tileArray[i][j].setborder(black);
                }


            }
        }

        if (!online) {
            if (reversi.gameOver()) {
                int winner;
                int playerScore = reversi.playerScore(player);
                int aiScore = reversi.playerScore(Reversi.getOpponent(player));
                int winnerChar;
                if (playerScore > aiScore) {winner = player;}
                else {winner = Reversi.getOpponent(player);}

                Alert gameOverAlert = new Alert(Alert.AlertType.CONFIRMATION);
                gameOverAlert.setTitle("Game over");
                gameOverAlert.setHeaderText(null);

                if (winner == 1) {winnerChar = 'X';}
                else {winnerChar = 'O';}
                if (playerScore != aiScore) {
                    gameOverAlert.setContentText(String.format("%c heeft gewonnen!\nDe score is %d - %d\nWil je nog een keer spelen?", winnerChar, playerScore, aiScore));
                }
                else {gameOverAlert.setContentText("Gelijkspel!!\nWil je nog een keer spelen?");}

                gameOverAlert.showAndWait().ifPresent((btnType) -> {
                    if (btnType == ButtonType.OK) {

                        this.reversi = new Reversi(1);
                        this.rules = new CheckRulesReversi(reversi.getBoard(), player);

                        updateBoard();
                        //createContent(reversi, false);

                    } else if (btnType == ButtonType.CANCEL) {

                    }
                });
            }
        }

    }

    private class Tile extends StackPane {

        int row;
        int col;

        private Rectangle border = new Rectangle(xWindowSize/8,yWindowSize/8);
        private Text text = new Text();
        public javafx.scene.image.ImageView imageView1;
        public javafx.scene.image.ImageView imageView2;

        private void setborder(Color color) {
            border.setStroke(color);
            if (color == Color.BLUE) {
                border.setStrokeWidth(4);
            } else {
                border.setStrokeWidth(2);
            }
        }

        public void clearText() {
            text.setText("");
        }

        public void drawX() {
            imageView1.setVisible(true);
            imageView2.setVisible(false);
        }

        public void drawO() {
            imageView2.setVisible(true);
            imageView1.setVisible(false);
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public Tile(int row, int col) {
            this.row = row;
            this.col = col;

            javafx.scene.image.Image image = new javafx.scene.image.Image("src/media/x.png");
            imageView1 = new javafx.scene.image.ImageView();
            imageView1.setFitHeight(70);
            imageView1.setFitWidth(70);
            imageView1.setImage(image);
            imageView1.setVisible(false);

            javafx.scene.image.Image image2 = new Image("src/media/o.png");
            imageView2 = new javafx.scene.image.ImageView();
            imageView2.setFitHeight(70);
            imageView2.setFitWidth(70);
            imageView2.setImage(image2);
            imageView2.setVisible(false);

            text.setFont(Font.font(72));

            border.setStrokeWidth(1);
            border.setFill(Color.YELLOW);
            border.setStroke(Color.BLACK);

            setAlignment(Pos.CENTER);
            getChildren().addAll(border, text, imageView1, imageView2);

            if (!online) {
                setOnMouseClicked(event -> {
                    if (turn == player && reversi.canPlay(player)) {
                        if (event.getButton() == MouseButton.PRIMARY) {
                            if (rules.checkLegalMove(getRow(), getCol(), reversi.getPlayer())) {
                                reversi.playerMove(getRow(), getCol());
                                updateBoard();
                                changeTurn();
                            }
                        }
                    }
                    Aimove();

                });
            }
        }
    }

    private void Aimove() {
        if (turn == Reversi.getOpponent(player) && reversi.canPlay(Reversi.getOpponent(player)) && !reversi.gameOver()) {
            reversi.AIMove(src.main.java.reversi.Reversi.getOpponent(player));
            updateBoard();
            changeTurn();
        }
        if (!reversi.canPlay(player) && !reversi.gameOver()) {
            reversi.AIMove(src.main.java.reversi.Reversi.getOpponent(player));
            updateBoard();
        }
    }


    public void changeTurn() {
        if (this.turn == 1) {this.turn = 2;}
        else {this.turn = 1;}
    }

}
