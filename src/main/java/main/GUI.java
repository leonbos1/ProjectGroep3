package src.main.java.main;

import com.sun.tools.javac.Main;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import src.main.java.Controllers.ServerHubController;
import src.main.java.UI.FourRowUI;
import src.main.java.UI.ReversiUI;
import src.main.java.UI.TicTacToeUI;
import src.main.java.reversi.Reversi;
import src.main.java.tictactoe.TicTacToe;

import java.io.*;
import java.util.ArrayList;

public class GUI extends Application {
    Server server;
    public ArrayList<String> playerlist;
    ServerHubController serverHubController;

    @FXML
    private Label welkomlabel;

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

    @FXML
    private Button hanzeServer;

    @FXML
    private Button testButton;

    @FXML
    private ListView<String> listView;

    @FXML
    private Button ReversiAI;

    @FXML
    private Button ReversiMulti;

    @FXML
    private Button SubscribeTicTacToe;

    @FXML
    private Button SubscribeReversi;


    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Start.fxml"));
        stage.setTitle("Hanze E-Games");
        stage.setScene(new Scene(root));
        stage.show();
    }


    @FXML
    void offlineMenu(ActionEvent event) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("offlineHub.fxml"));
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
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Start.fxml"));
        Stage stage = (Stage) FourRow.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }


    public static class guiServerHub{
        private final Stage stage;
        private final Server server;
        private final Scene scene;
        private ListView<String> listView;

        public guiServerHub(Stage stage, Server server, Scene scene, ListView<String> listView){
            this.stage = stage;
            this.server = server;
            this.scene = scene;
            this.listView = listView;

        }

        public void initialize(){
            try {
                listView = new ListView<>();

                ObservableList<String> players =FXCollections.observableArrayList (
                        "Single", "Double", "Suite", "Family App", "dfgdfg", "hgfhfgh");


                listView.setItems(players);

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
    }

    public void guiServerHub(ActionEvent event) throws IOException, InterruptedException{
        String ip = hostIP.getText();
        int port = Integer.parseInt(portNumber.getText());
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("serverHub.fxml"));

        Parent root = loader.load();

        serverHubController = loader.getController();


        this.server = new Server(ip, port, this);
        server.login();

        playerlist = server.playerlist();

        serverHubController.setPlayerlist(playerlist);

        Platform.runLater(
                () -> {
                    serverHubController.setServer(server);
                });



        Stage stage = (Stage) connectButton.getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);



    }

    public void guiServerHubHanze(ActionEvent event) throws IOException, InterruptedException {

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("serverHub.fxml"));

        Parent root = loader.load();

        serverHubController = loader.getController();


        String ip = "145.33.225.170";
        int port = 7789;
        this.server = new Server(ip, port, this);
        server.login();

        playerlist = server.playerlist();

        serverHubController.setPlayerlist(playerlist);

        Platform.runLater(() -> serverHubController.setServer(server));

        
        Stage stage = (Stage) connectButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        serverHubController.firstPlayers();

    }

    public void endGameAlert(String winner) {
        Platform.runLater(
                () -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Game ended");
                    alert.setHeaderText(null);
                    if (winner.equals("opponent")) {
                        alert.setContentText(String.format("Helaas, je hebt verloren."));
                    }
                    else if (winner.equals("you")) {
                        alert.setContentText(String.format("YES!, je hebt gewonnen."));
                    }
                    else {
                        alert.setContentText(String.format("Gelijkspel!"));
                    }
                    alert.showAndWait();

                });
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

    public  void startreversi(ActionEvent event) throws Exception {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("reversiConfig.fxml"));
        stage.setTitle("Reversi");
        stage.setScene(new Scene(root));
        stage.show();
        /*
        try {
            Parent root = FXMLLoader.load(getClass().getResource("reversiConfig.fxml"));
            Stage stage = (Stage) Reversi.getScene().getWindow();
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
        */
    }

    public void playreversiMulti(ActionEvent event) {
        Stage stage = (Stage) ReversiAI.getScene().getWindow();
        stage.close();
        playreversi(true);
    }

    public void playreversiAI(ActionEvent event) {
        Stage stage = (Stage) ReversiMulti.getScene().getWindow();
        stage.close();
        playreversi(false);
    }

    public void playreversi(boolean multiplayer) {
        ReversiUI reversiUI = new ReversiUI();
        Reversi reversi = new Reversi(1);
        Stage stage = new Stage();

        stage.setScene(new Scene(reversiUI.createContent(reversi, false, multiplayer, false)));
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