package src.main.java.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import src.main.java.main.GUI;
import src.main.java.main.Server;

import java.util.ArrayList;
import java.util.Arrays;

public class ServerHubController extends GUI {

    public Button resfreshButton;
    public ListView listView;
    public Button challengeButton;
    private ArrayList<String> playerlist;
    Server server;

    public void refreshPlayers() throws InterruptedException {
        server.updatePlayerlist();

        ObservableList<String> players = FXCollections.observableArrayList ();

        players.setAll(playerlist);

        System.out.println(playerlist);

        listView.setItems(players);

        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        challengeButton.setOnAction(event -> challengeClicked());



    }

    private void challengeClicked() {

        ObservableList<String> players;
        players = listView.getSelectionModel().getSelectedItems();

        for (String p: players) {
            server.challenge(p, "reversi");
        }
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public void setPlayerlist(ArrayList<String> playerlist) {
        this.playerlist = playerlist;
    }
}
