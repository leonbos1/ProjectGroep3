import java.io.*;
import java.net.*;


public class Server{
    public Socket sock;
    public OutputStream out;
    public BufferedReader in;
    private Thread thread;
    private TicTacToe game;
    public String username;


    public Server(String ip, int port) throws IOException {
        this.sock = new Socket(ip, port);
        this.out = new PrintStream(sock.getOutputStream());
        this.in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        game = new TicTacToe(3,3);
        this.username = "test1";

        boolean alive = true;
        thread = new Thread(() -> {
            while (alive) receive();
        }, "ServerlisternerThread");
        thread.start();
    }

    public void send(String data){
        try {
            OutputStream output = sock.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            writer.println(data);
        } catch(Exception error){System.out.println(error);}
    }

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
                    game.getBoard().showBoard();

                    if (arr[1].equals("GAME")) {
                        switch (arr[2]) {
                            case "MOVE":
                                if (arr[3].equals("PLAYER:")) {
                                    if (!arr[4].equals(getUsername())) {
                                        int move = Integer.parseInt(arr[6]);
                                        int row = move / 3; int col = move % 3;
                                        game.makeMove(row, col);
                                    }
                                }
                                break;

                            case "CHALLENGE":
                                send("challenge accept "+arr[8]);

                            case "YOURTURN":
                                if (arr[3].equals("TURNMESSAGE:")) {
                                    int[] movearray = game.aiMove(2);
                                    int move = ((movearray[0] - 1) * 3) + ((movearray[1] - 1));
                                    move(move);
                                }
                                break;

                            case "DRAW":
                                System.out.println("Gelijkspel");
                                game = new TicTacToe(3,3);
                                break;

                            case "WIN":
                                System.out.println("Gewonnen");
                                game = new TicTacToe(3,3);
                                break;

                            case "LOSS":
                                System.out.println("Verloren");
                                game = new TicTacToe(3,3);
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