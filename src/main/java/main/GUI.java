package src.main.java.main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import src.main.java.UI.FourRowUI;
import src.main.java.UI.ReversiUI;
import src.main.java.UI.TicTacToeUI;
import src.main.java.main.Server;
import src.main.java.tictactoe.TicTacToe;

import java.io.IOException;
import java.util.ResourceBundle;

public class GUI extends Application {
    private Server server;

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


    public static void main(String[] args){
        System.out.println(1);
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

    @FXML
    void guiServerHub(ActionEvent event) throws Exception {
        try {
            String ip = hostIP.getText();
            Integer port = Integer.parseInt(portNumber.getText());
            server = new Server(ip, port);
            server.username = userName.getText();
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

    public void playreversi(ActionEvent event) {
        ReversiUI reversiUI = new ReversiUI();

        Stage stage = new Stage();

        stage.setScene(new Scene(reversiUI.createContent()));
        stage.show();
    }

    public void playTicTacToe(ActionEvent event) {
        TicTacToeUI ticTacToeui = new TicTacToeUI();

        Stage stage = new Stage();

        stage.setScene(new Scene(ticTacToeui.createContent()));
        stage.show();
    }

    public void playFourRow(ActionEvent event) {
        FourRowUI fourRowUI = new FourRowUI();

        Stage stage = new Stage();

        stage.setScene(new Scene(fourRowUI.createContent()));
        stage.show();
    }
}
