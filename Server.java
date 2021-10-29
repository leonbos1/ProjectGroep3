import java.io.*;
import java.net.*;

public class Server{
    private static Socket sock;

    public static void main(String[] args){
        connect("Bastiaan");
    }
    
    public static void connect(String username){
        try{
            sock = new Socket("localhost", 7789);
            username = String.format("login %s", username);
            send(username);
            receive();
        } catch(Exception error){System.out.println(error);}
    }

    public static void send(String data){
        try {
            OutputStream output = sock.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            writer.println(data);
        } catch(Exception error){System.out.println(error);}
    }

    public static void receive(){
        try {
            InputStream input = sock.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            while((line = reader.readLine()) != null){
                    System.out.println("test1");
                    System.out.println(line);
                    line = null;
            }


        } catch (Exception error) {
            System.out.println(error);
        }
    }

    public static void endConnection(){
        try{
            send("disconnect");
            sock.close();
        } catch(Exception error){System.out.println(error);}

    }
}