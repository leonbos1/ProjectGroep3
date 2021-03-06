package src.main.java.Controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import src.main.java.main.GUI;
import src.main.java.main.Server;

import java.awt.*;
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
    String playType;

    @FXML
    Label subscribeLabel;

    @FXML
    Button backButton;

    @FXML
    ToggleButton toggleManual;

    Server server;

    @FXML
    public void initialize() {
        AtomicBoolean running = new AtomicBoolean(true);
        autoRefresh = new Thread(() -> {
            while (running.get()) {
                try {
                    Thread.sleep(1000);
                    Platform.runLater(
                            () -> {
                                try {
                                    refreshPlayers();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            });

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

        autoRefresh.stop();
        stage = (Stage) backButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getClassLoader().getResource("Start.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        try {
            server.endConnection();
        }
        catch (Error ignored) {

        }
    }


    @FXML
    private void subscribeTicTacToe(){
        setSubscribeLabel("Tic-tac-toe");
        server.subscribe("tic-tac-toe");
    }

    @FXML
    private void subscribeReversi(){
        setSubscribeLabel("Reversi");
        server.subscribe("Reversi");
    }

    @FXML
    private void setSubscribeLabel(String text) {
        subscribeLabel.setText("Subscribe: "+text);
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public void setPlayerlist(ArrayList<String> playerlist) {
        this.playerlist = playerlist;
    }

    public void toggleManual(ActionEvent event) {
        Color red = Color.decode("#FF0000");
        Color green = Color.decode("#00ff00");

        if (toggleManual.getText().equals("AI")) {
            server.setManual(true);
            System.out.println("Nu manual!");
            toggleManual.setText("Manual");
            toggleManual.setStyle("-fx-text-fill: white; -fx-background-radius: 100; -fx-background-color: #00FF00;");
        }
        else {
            server.setManual(false);
            toggleManual.setText("AI");
            toggleManual.setStyle("-fx-text-fill: white; -fx-background-radius: 100; -fx-background-color: #FF0000;");

        }

    }
}
