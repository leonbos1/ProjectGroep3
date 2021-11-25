package src.main.java.main;

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


    public Server(String ip, int port) throws IOException {
        this.sock = new Socket(ip, port);
        this.out = new PrintStream(sock.getOutputStream());
        this.in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        this.tictactoe = new TicTacToe(3,3);
        this.reversi = new Reversi(1);
        this.username = "ITV2C3";
        this.game = game;

        boolean alive = true;
        thread = new Thread(() -> {
            while (alive) receive();
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


                if (arr[0].equals("SVR")) {

                    if (arr[1].equals("PLAYERLIST")) {
                        for (int i = 2; i < arr.length; i++) {
                            System.out.println(arr[i]);
                        }
                    }


                    if (arr[1].equals("GAME")) {
                        switch (arr[2]) {
                            case "MOVE":
                                if (arr[3].equals("PLAYER:")) {
                                    if (!arr[4].equals(getUsername())) {
                                        int move = Integer.parseInt(arr[6]);
                                        int row = move / 3; int col = move % 3;
                                        tictactoe.makeMove(row, col);
                                        tictactoe.getBoard().showBoard();
                                        System.out.println();
                                    }
                                }
                                break;

                            case "MATCH":
                                System.out.println("Je speelt tegen: "+ arr[8]);

                            case "CHALLENGE":
                                //if (arr[3].equals("CHALLENGER:")) {
                                //    send("challenge accept " + arr[6]);
                                //}

                            case "YOURTURN":
                                if (arr[3].equals("TURNMESSAGE:")) {
                                    int[] movearray = tictactoe.aiMove(2);
                                    int move = ((movearray[0] - 1) * 3) + ((movearray[1] - 1));
                                    move(move);
                                    tictactoe.getBoard().showBoard();
                                    System.out.println();
                                }
                                break;

                            case "DRAW":
                                System.out.println("Gelijkspel");
                                tictactoe = new TicTacToe(3,3);
                                //subscribe("tic-tac-toe");
                                playerlist();
                                break;

                            case "WIN":
                                System.out.println("Gewonnen");
                                tictactoe = new TicTacToe(3,3);
                                playerlist();
                                break;

                            case "LOSS":
                                System.out.println("Verloren");
                                tictactoe = new TicTacToe(3,3);
                                playerlist();
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
    }

    public void playerlist() {
        send("get playerlist");
    }

    public void challenge(String username,String game) {
        send("challenge "+ username+" "+game);
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
}