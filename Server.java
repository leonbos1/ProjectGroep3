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
                //for (String a : arr) {
                //    System.out.println(a);
                //}
                //System.out.println(arr[0]);
                if (arr[0].equals("SVR")) {
                    //System.out.println(arr[0]);
                    //System.out.println(getUsername());
                    game.getBoard().showBoard();

                    if (arr[1].equals("GAME")) {
                        if (arr[2].equals("MATCH")) {
                            if (arr[3].equals("PLAYERTOMOVE:")) {
                                if (arr[4].equals(getUsername())) {
                                    int[] movearray = game.aiMove(2);
                                    System.out.println(movearray[0] +" "+movearray[1]);
                                    int move = (movearray[0]-1) * (movearray[1]-1);
                                    System.out.println(move);
                                    send("move "+move);

                                }
                                else  {
                                    System.out.println("not me start");
                                }
                            }
                        }
                        else if (arr[2].equals("MOVE")) {
                            if (arr[3].equals("PLAYER:")) {
                                if (arr[4].equals(getUsername()) == false) {
                                    int move = Integer.parseInt(arr[6]);
                                    int row = move / 3;
                                    int col = move % 3;
                                    game.makeMove(row, col);
                                    System.out.println("OPPPONENT MOVE " + move);
                                }
                            }

                        }
                        else if (arr[2].equals("YOURTURN")) {
                            if (arr[3].equals("TURNMESSAGE:")) {
                                int[] movearray = game.aiMove(2);
                                int move = (movearray[0]-1) * (movearray[1]-1);
                                send("move "+move);
                                System.out.println(move);
                            }
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