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
import src.main.java.main.Server;

import java.io.IOException;

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

}
