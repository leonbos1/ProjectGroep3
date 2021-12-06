package src.main.java.main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import src.main.java.UI.ReversiUI;
import src.main.java.UI.TicTacToeUI;
import src.main.java.reversi.Reversi;
import src.main.java.tictactoe.TicTacToe;
import src.main.java.main.Board;

import java.io.*;
import java.net.*;


public class Server{
    public Socket sock;
    public OutputStream out;
    public BufferedReader in;
    private Thread thread;
    private TicTacToe tictactoe;
    private Reversi reversi;
    public String username;
    public String game;
    public String[] playerList;
    public GUI gui;
    ReversiUI reversiUI = new ReversiUI();
    TicTacToeUI ticTacToeUI = new TicTacToeUI();

    public Server(String ip, int port, GUI gui) throws IOException {
        this.sock = new Socket(ip, port);
        this.out = new PrintStream(sock.getOutputStream());
        this.in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        this.username = "ITV2C3";
        this.gui = gui;

        thread = new Thread(() -> {
            while (true) receive();
        }, "ServerlisternerThread");
        thread.start();
    }

    /**
     * verstuurt berichten naar de server
     */

    public void send(String data){
        try {
            OutputStream output = sock.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            writer.println(data);
        } catch(Exception error){System.out.println(error);}
    }

    /**
     * ontvangt en handled binnenkomende server berichten
     */

    public void receive(){
        try {
            InputStream input = sock.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            while((line = reader.readLine()) != null){
                System.out.println(line);

                line = line.replace("[", "");
                line = line.replace("]","");
                line = line.replace("{", "");
                line = line.replace("}","");
                line = line.replace("\"","");
                line = line.replace(",", "");
                String[] arr = line.split(" ");
                playerList = new String[arr.length];


                if (arr[0].equals("SVR")) {

                    if (arr[1].equals("PLAYERLIST")) {
                        for (int i = 2; i < arr.length; i++) {
                            playerList[i-2] = arr[i];
                        }
                    }


                    if (arr[1].equals("GAME")) {
                        switch (arr[2]) {
                            case "MOVE":
                                if (arr[3].equals("PLAYER:")) {
                                    if (game.equals("tictactoe")) {
                                        if (!arr[4].equals(getUsername())) {
                                            int move = Integer.parseInt(arr[6]);
                                            int row = move / 3;
                                            int col = move % 3;
                                            tictactoe.makeMove(row, col);
                                            tictactoe.getBoard().showBoard();
                                            System.out.println();
                                            ticTacToeUI.updateBoard();
                                        }
                                    }
                                    else if (game.equals("reversi")) {

                                        if (!arr[4].equals(getUsername())) {
                                            int move = Integer.parseInt(arr[6]);
                                            int row = move / 8;
                                            int col = move % 8;
                                            reversi.makeMove(Reversi.getOpponent(reversi.getPlayer()), row, col);
                                            reversi.getBoard().showBoard();
                                            System.out.println();
                                            reversiUI.updateBoard();
                                        }
                                    }
                                }
                                break;

                            case "MATCH":
                                // wij mogen de eerste zet doen: player 1 begint altijd bij ons bord
                                if (arr[4].equals(getUsername())) {
                                    if (arr[6].equals("Reversi")) {
                                        setGame("reversi");
                                        reversi = new Reversi(2);

                                        Platform.runLater(
                                                () -> {
                                                    Stage stage = new Stage();

                                                    stage.setScene(new Scene(reversiUI.createContent(reversi, true)));
                                                    stage.show();
                                                });
                                    }
                                    else if (arr[6].equals("Tic-tac-toe")) {
                                        setGame("tictactoe");
                                        tictactoe = new TicTacToe(3,3);

                                        Platform.runLater(
                                                () -> {
                                                    Stage stage = new Stage();

                                                    stage.setScene(new Scene(ticTacToeUI.createContent(tictactoe, true)));
                                                    stage.show();
                                                });
                                    }

                                }

                                // tegenstander begint
                                else if (game.equals("reversi")){
                                    reversi = new Reversi(1);
                                    Platform.runLater(
                                            () -> {
                                                Stage stage = new Stage();

                                                stage.setScene(new Scene(reversiUI.createContent(reversi, true)));
                                                stage.show();
                                                });
                                }

                                else if (game.equals("tictactoe")) {
                                    tictactoe = new TicTacToe(3,3);
                                    Platform.runLater(
                                            () -> {
                                                Stage stage = new Stage();

                                                stage.setScene(new Scene(ticTacToeUI.createContent(tictactoe, true)));
                                                stage.show();
                                            });
                                }
                                break;

                            case "CHALLENGE":
                                if (arr[3].equals("CHALLENGER:")) {
                                    gui.challengeAlert(arr[4], arr[8], arr[6]);
                                    //send("challenge accept " + arr[6]);
                                    if (arr[8].equals("Reversi")) {
                                        setGame("reversi");
                                    }
                                    else if (arr[8].equals("tic-tac-toe")) {
                                        setGame("tictactoe");
                                    }
                                }

                            case "YOURTURN":
                                if (arr[3].equals("TURNMESSAGE:")) {
                                    if (game.equals("tictactoe")) {
                                        int[] movearray = tictactoe.aiMove(2);
                                        int move = ((movearray[0] - 1) * 3) + ((movearray[1] - 1));
                                        move(move);
                                        tictactoe.getBoard().showBoard();
                                        System.out.println();
                                        ticTacToeUI.updateBoard();
                                    }

                                    else if (game.equals("reversi")) {
                                        int[] movearray = reversi.AIMove(reversi.getPlayer());
                                        int move = ((movearray[0] - 1) * 8 ) + ((movearray[1] - 1) );
                                        System.out.println(move);
                                        move(move);
                                        reversi.getBoard().showBoard();
                                        System.out.println();
                                        reversiUI.updateBoard();
                                    }
                                }
                                break;

                            case "DRAW":
                                System.out.println("Gelijkspel");
                                if (game.equals("tictactoe")) {
                                    tictactoe = new TicTacToe(3, 3);
                                }

                                break;

                            case "WIN":
                                System.out.println("Gewonnen");
                                if (game.equals("tictactoe")) {
                                    tictactoe = new TicTacToe(3, 3);
                                }

                                break;

                            case "LOSS":
                                System.out.println("Verloren");
                                if (game.equals("tictactoe")) {
                                    tictactoe = new TicTacToe(3, 3);
                                }

                                break;
                        }
                    }
                }

                line = null;
            }
            endConnection();

        } catch (Exception error) {
            System.out.println(error);
        }
    }

    public void login() {
        send("login "+username);
    }

    public void subscribe(String game) {
        send("subscribe " + game);
        setGame(game);
    }

    public String[] playerlist() throws InterruptedException {
        send("get playerlist");
        Thread.sleep(500);
        return playerList;
    }

    public void challenge(String username,String game) {
        send("challenge "+ username+" "+game);
        setGame(game);
    }

    public void move(int move) {
        send("move "+move);
    }

    public void endConnection(){
        try {
            send("disconnect");
            sock.close();
        }
        catch (Exception error) {
            System.out.println("error");
        }
    }

    public Socket getSock() {
        return sock;
    }

    public String getUsername() {return this.username;}

    public void setGame(String game) {this.game = game;}
}