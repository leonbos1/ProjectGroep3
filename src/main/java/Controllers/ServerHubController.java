package src.main.java.Controllers;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

public class ServerHubController {

    public Button resfreshButton;
    public ListView listView;
    public Label welkomlabel;
    public Button challengeButton;

    public void refreshPlayers() {
        ObservableList<String> players = FXCollections.observableArrayList (
                "Player1", "Player2", "Player3", "Player4", "Player5", "Player6");


        listView.getItems().addAll(players);

        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        challengeButton.setOnAction(event -> challengeClicked());

    }

    private void challengeClicked() {
        String Player = "";

        ObservableList<String> players;
        players = listView.getSelectionModel().getSelectedItems();

        for (String p: players) {
            System.out.println(p);
        }
    }

}
