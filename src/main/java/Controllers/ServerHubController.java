package src.main.java.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import src.main.java.main.GUI;
import src.main.java.main.Server;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ServerHubController extends GUI {

    public Button refreshButton;
    public ListView listView;
    public Button challengeButton;
    public Button SubscribeTicTacToe;
    public Button SubscribeReversi;
    private ArrayList<String> playerlist;
    public ChoiceBox gameList;
    Stage stage;
    Parent root;

    @FXML
    Button backButton;

    Server server;

    public void refreshPlayers() throws InterruptedException {
        server.updatePlayerlist();

        ObservableList<String> players = FXCollections.observableArrayList ();

        players.setAll(playerlist);

        System.out.println(playerlist);

        listView.setItems(players);

        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

//        ObservableList<String> games =FXCollections.observableArrayList ("Reversi", "Tic Tac Toe");
//        gameList.setItems(games);

        //gameList.getItems().add("Tictactoe");

        challengeButton.setOnAction(event -> challengeClicked());
        SubscribeTicTacToe.setOnAction(event -> subscribeTicTacToe());
        SubscribeReversi.setOnAction(event -> subscribeReversi());




    }

    private void challengeClicked() {

        ObservableList<String> players;
        players = listView.getSelectionModel().getSelectedItems();

        for (String p: players) {
            server.challenge(p, "reversi");
        }
    }

    @FXML
    private void back() throws IOException {
        server.endConnection();
        server = null;
        stage = (Stage) backButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getClassLoader().getResource("Start.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    private void subscribeTicTacToe(){
        server.subscribe("tic-tac-toe");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Succes!");
        alert.setHeaderText(null);
        alert.setContentText("Succesvol aangemeld voor TicTacToe!");
        alert.showAndWait();
    }

    @FXML
    private void subscribeReversi(){
        server.subscribe("Reversi");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Succes!");
        alert.setHeaderText(null);
        alert.setContentText("Succesvol aangemeld voor Reversi!");
        alert.showAndWait();
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public void setPlayerlist(ArrayList<String> playerlist) {
        this.playerlist = playerlist;
    }
}
