import java.lang.*;
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

class main{

    public static void main(String[] args) {
        try {
            serverTicTacToe();
        } catch (IOException e) {
            System.out.println("error");
        }
    }

    public static void serverTicTacToe() throws IOException {
        String ip = "145.33.225.170";
        int port = 7789;

        Server server = new Server(ip, port);

        server.login();
        server.challenge("test2","tic-tac-toe");

    }

    public static void cmdTicTacToe() {
        boolean running = true;

        System.out.println("Welkom bij tic-tac-toe!");
        System.out.print("Wilt u eerst spelen (0), of mag de ai eerst (1): ");
        Scanner scanner = new Scanner(System.in);
        int choice = Integer.parseInt(scanner.nextLine());

        System.out.print("Hoe groot is het speelboard?:");
        int size = Integer.parseInt(scanner.nextLine());

        System.out.print("Wat is de benodigde lengte om te winnen?:");
        int win_length = Integer.parseInt(scanner.nextLine());

        TicTacToe game = new TicTacToe(win_length, size);

        if (game.getBoardWinLength()>game.getBoardArray().length) {
            System.out.println("De win lengte kan niet groter zijn dan het bord zelf!");
            running = false;
        }
        else if (game.getBoardWinLength()<=1) {
            System.out.println("De win lengte moet groter zijn dan 1");
            running = false;
        }

        if (running) {
            if (choice == 1) {
                int[] aiArray;
                aiArray = game.aiMove(2);
                System.out.println("\nDe ai kiest: "+ Arrays.toString(aiArray));
            }

            System.out.println();
            game.getBoard().showBoard();
        }

        while (running) {
            while (true) {
                int[] move = input();
                if (game.makeMove(move[0]-1, move[1]-1)) {
                    System.out.println("test");
                    game.getBoard().showBoard();
                    break;
                }
            }

            if (CheckRules.checkWinner(game.getBoardArray() ,1, game.getBoardWinLength())) {
                System.out.println("Jij wint!");
                if (restart()) {
                    game.getBoard().clearBoard();
                } else {
                    break;
                }
            }
            if (CheckRules.checkBoardFull(game.getBoardArray())) {
                System.out.println("Gelijk spel!");
                if (restart()) {
                    game.getBoard().clearBoard();
                } else {
                    break;
                }
            }

            int[] aiArray;
            aiArray = game.aiMove(2);
            System.out.println("\nDe ai kiest: "+ Arrays.toString(aiArray));

            game.getBoard().showBoard();
            if (CheckRules.checkWinner(game.getBoardArray() ,2, game.getBoardWinLength())) {
                System.out.println("De Ai heeft gewonnen :(");
                running = false;
                if (restart()) {
                    game.getBoard().clearBoard();
                } else {
                    break;
                }
            }
            if (CheckRules.checkBoardFull(game.getBoardArray())) {
                System.out.println("Gelijk spel!");
                if (restart()) {
                    game.getBoard().clearBoard();
                } else {
                    break;
                }
            }
        }
    }

    public static int[] input(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nKies een row: ");
        int x = Integer.parseInt(scanner.nextLine());
        System.out.println("\nKies een column: ");
        int y = Integer.parseInt(scanner.nextLine());
        return new int[]{x,y};
    }

    public static boolean restart(){
        while (true) {
            System.out.println("Wilt u opnieuw spelen? Toets y of n: ");
            Scanner scanner = new Scanner(System.in);
            String next = scanner.nextLine();
            if (next.equals("y")) {
                return true;
            }
            if (next.equals("n")) {
                System.out.println("Tot ziens!");
                return false;
            }
            System.out.println("Probeer dat opnieuw.");
        }
    }


}
