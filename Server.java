import java.io.*;
import java.net.*;


public class Server{
    public Socket sock;
    public OutputStream out;
    public BufferedReader in;
    private Thread thread;


    public Server(String ip, int port) throws IOException {
        this.sock = new Socket(ip, port);
        this.out = new PrintStream(sock.getOutputStream());
        this.in = new BufferedReader(new InputStreamReader(sock.getInputStream()));

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

                line = null;
            }

        } catch (Exception error) {
            System.out.println(error);
        }
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
}