package src.main.java.main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import src.main.java.UI.FourRowUI;
import src.main.java.UI.ReversiUI;
import src.main.java.UI.TicTacToeUI;
import src.main.java.main.Server;
import src.main.java.reversi.Reversi;
import src.main.java.tictactoe.TicTacToe;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.ResourceBundle;

public class GUI extends Application {
    public Server server;

    @FXML
    private Button connectButton;

    @FXML
    private TextField hostIP;

    @FXML
    private Button playOffline;

    @FXML
    private TextField portNumber;

    @FXML
    private TextField userName;

    @FXML
    private Button offlineReversi;

    @FXML
    private Button FourRow;


    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Start.fxml"));
        stage.setTitle("Hanze E-Games");
        stage.setScene(new Scene(root));
        stage.show();

    }


    @FXML
    void offlineMenu(ActionEvent event) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("offlineHub.fxml"));
            Stage stage = (Stage) playOffline.getScene().getWindow();
            Scene scene = new Scene(root);
            //scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            stage.setScene(scene);

        } catch (Exception e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error:");
            alert.setHeaderText(null);
            alert.setContentText(e.toString());
            alert.showAndWait();
        }
    }

    public void setServer(Server server) {this.server = server;}

    public Server getServer() {return this.server;}

    @FXML
    void mainMenu(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Start.fxml"));
        Stage stage = (Stage) FourRow.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    void guiServerHub(ActionEvent event) throws Exception {
        try {
            String ip = hostIP.getText();
            Integer port = Integer.parseInt(portNumber.getText());
            this.server = new Server(ip ,port, this);
            server.username = userName.getText();
            server.login();
            Parent root = FXMLLoader.load(getClass().getResource("serverHub.fxml"));
            Stage stage = (Stage) connectButton.getScene().getWindow();

            stage.setScene(new Scene(root));

        } catch (Exception e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error:");
            alert.setHeaderText(null);
            alert.setContentText(e.toString());
            alert.showAndWait();
        }

    }



    public void challengeAlert(String name, String game, String challengenumber) {
        Platform.runLater(
        () -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Incoming challenge");
            alert.setHeaderText(null);
            alert.setContentText(String.format("%s wil %s met je spelen", name, game));
            alert.showAndWait().ifPresent((btnType) -> {
                if (btnType == ButtonType.OK) {

                    getServer().send("challenge accept " + challengenumber);
                    getServer().setGame(game);

                } else if (btnType == ButtonType.CANCEL) {

                }
            });




    }
        );
    }

    public void playreversi(ActionEvent event) {
        ReversiUI reversiUI = new ReversiUI();
        Reversi reversi = new Reversi(1);
        Stage stage = new Stage();

        stage.setScene(new Scene(reversiUI.createContent(reversi, false)));
        stage.show();
    }

    public void playTicTacToe(ActionEvent event) {
        TicTacToeUI ticTacToeui = new TicTacToeUI();
        TicTacToe ticTacToe = new TicTacToe(3,3);

        Stage stage = new Stage();

        stage.setScene(new Scene(ticTacToeui.createContent(ticTacToe,false)));
        stage.show();
    }

    public void playFourRow(ActionEvent event) {
        FourRowUI fourRowUI = new FourRowUI();

        Stage stage = new Stage();

        stage.setScene(new Scene(fourRowUI.createContent()));
        stage.show();
    }


}
