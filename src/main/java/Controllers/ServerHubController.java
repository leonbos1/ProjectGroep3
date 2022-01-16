package src.main.java.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import src.main.java.main.GUI;
import src.main.java.main.Server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class ServerHubController extends GUI {

    public ListView listView;
    public Button SubscribeTicTacToe;
    public Button SubscribeReversi;
    public Button challengeReversiButton;
    public Button challengeTictactoeButton;
    private ArrayList<String> playerlist;
    ObservableList<String> players;
    public ChoiceBox gameList;
    Stage stage;
    Parent root;
    Thread autoRefresh;

    @FXML
    Button backButton;

    Server server;

    @FXML
    public void initialize() {
        AtomicBoolean running = new AtomicBoolean(true);
        autoRefresh = new Thread(() -> {
            while (running.get()) {
                try {
                    Thread.sleep(1000);
                    refreshPlayers();
                } catch (InterruptedException e) {
                    running.set(false);
                }
            }
        }, "autoRefresh");
        autoRefresh.start();
    }

    public void firstPlayers() {
        ObservableList<String> players = FXCollections.observableArrayList ();
        players.setAll(playerlist);
        System.out.println(playerlist);
        listView.setItems(players);
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    public void refreshPlayers() throws InterruptedException {
        server.updatePlayerlist();

        ObservableList<String> players = FXCollections.observableArrayList ();

        players.setAll(playerlist);

        System.out.println(playerlist);

        listView.setItems(players);

        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

//        ObservableList<String> games =FXCollections.observableArrayList ("Reversi", "Tic Tac Toe");
//        gameList.setItems(games);
//        gameList.getItems().add("Tictactoe");
    }

    @FXML
    private void challengeReversiClicked() {

        players = listView.getSelectionModel().getSelectedItems();

        for (String p: players) {
            server.challenge(p, "reversi");
        }
    }

    @FXML
    private void challengeTictactoeClicked() {

        players = listView.getSelectionModel().getSelectedItems();

        for (String p: players) {
            server.challenge(p, "tic-tac-toe");
        }
    }

    @FXML
    public void back() throws IOException {
        server.endConnection();
        autoRefresh.stop();
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
