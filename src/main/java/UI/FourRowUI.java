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
import src.main.java.FourRow.CheckRulesFourRow;
import src.main.java.FourRow.FourRow;
import javafx.scene.image.Image;

import java.util.concurrent.atomic.AtomicInteger;


//1 is X
//2 is O

public class FourRowUI extends Application {
    FourRow fourrow = new FourRow(1);

    int Xsize = fourrow.getBoard().getHeigth();
    int Ysize = fourrow.getBoard().getWidth();
    double xWindowSize = 700;
    double yWindowSize = 600;
    int player = fourrow.getPlayer();
    CheckRulesFourRow rules = new CheckRulesFourRow(fourrow.getBoard(), player);

    Tile[][] tileArray = new Tile[Xsize][Ysize];

    public Parent createContent() {

        Pane root = new Pane();
        root.setPrefSize(xWindowSize,yWindowSize);

        for (int i = 0; i < Xsize; i++) {
            for (int j = 0; j < Ysize; j++) {
                Tile tile = new Tile(i, j);

                tile.setTranslateX(j * (115));
                tile.setTranslateY(i * (85));
                tileArray[i][j] = (tile);

                if (fourrow.getBoardArray()[i][j] == fourrow.getPlayer()) {
                    tileArray[i][j].drawStone1();
                } else if (fourrow.getBoardArray()[i][j] == FourRow.getOpponent(fourrow.getPlayer())) {
                    tileArray[i][j].drawStone2();
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

        for (int i = 0; i < Xsize; i++) {
            for (int j = 0; j < Ysize; j++) {

                if (fourrow.getBoardArray()[i][j] == fourrow.getPlayer()) {
                    tileArray[i][j].drawStone1();
                } else if (fourrow.getBoardArray()[i][j] == FourRow.getOpponent(fourrow.getPlayer())) {
                    tileArray[i][j].drawStone2();
                }

            }
        }

    }

    private class Tile extends StackPane {

        int row;
        int col;

        private Rectangle border = new Rectangle(xWindowSize/Xsize,yWindowSize/Ysize);
        private Text text = new Text();
        public javafx.scene.image.ImageView imageView1;
        public javafx.scene.image.ImageView imageView2;


        public void drawStone1() {
            imageView1.setVisible(true);
        }

        public void drawStone2() {
            imageView2.setVisible(true);
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

            Image image = new Image("stone.png");
            imageView1 = new javafx.scene.image.ImageView();
            imageView1.setFitHeight(70);
            imageView1.setFitWidth(70);
            imageView1.setImage(image);
            imageView1.setVisible(false);

            Image image2 = new Image("stone2.png");
            imageView2 = new javafx.scene.image.ImageView();
            imageView2.setFitHeight(70);
            imageView2.setFitWidth(70);
            imageView2.setImage(image2);
            imageView2.setVisible(false);


            text.setFont(Font.font(72));

            border.setStrokeWidth(2);
            border.setFill(Color.GREEN);
            border.setStroke(Color.BLACK);

            setAlignment(Pos.CENTER);
            getChildren().addAll(border, text, imageView1, imageView2);

            setOnMouseClicked(event -> {
                if (turn.get() == player) {
                    if (event.getButton() == MouseButton.PRIMARY) {
                        if (rules.checkLegalMove(getCol())) {
                            fourrow.makeMove(player, getCol());
                            updateBoard();
                            turn.set(changeTurn(turn.get()));
                        }
                    }
                }
                if (turn.get() == FourRow.getOpponent(player)) {
                    fourrow.AIMove(FourRow.getOpponent(player));
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
