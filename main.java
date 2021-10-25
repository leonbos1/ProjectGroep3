import java.lang.*;
import java.util.Arrays;
import java.util.Scanner;
class main{

    public static void main(String[] args) {
        boolean running = true;
        System.out.println("Welkom bij tic-tac-toe!");
        System.out.print("Wilt u eerst spelen (0), of mag de ai eerst (1): ");
        Scanner scanner = new Scanner(System.in);
        int choice = Integer.parseInt(scanner.nextLine());

        System.out.print("Hoe groot is het speelboard?:");

        TicTacToe game = new TicTacToe();
        if (game.getBoardWinLength()>game.getBoardArray().length) {
            System.out.println("De win lengte kan niet groter zijn dan het bord zelf!");
            running = false;
        }
        else if (game.getBoardWinLength()>=1) {
            System.out.println("De win lengte moet groter zijn dan 1");
            running = false;
        }

        if (running) {
            if (choice == 1) {
                int[] aiArray = game.aiMove(2);
                int x = aiArray[0];
                int y = aiArray[1];
                System.out.printf("\nDe ai kiest: %d", x);
            }

            System.out.println();
            game.getBoard().showBoard();
        }

        while (running) {
            while (true) {
                int[] move = input();
                if (game.makeMove(move[0], move[1])) {
                    game.getBoard().showBoard();
                    break;
                }
            }

            if (CheckRules.checkWinner(game.getBoardArray() ,1, game.getBoardWinLength())) {
                System.out.println("Jij wint!");
                break;
            }
            if (CheckRules.checkBoardFull(game.getBoardArray())) {
                System.out.println("Gelijk spel!");
                break;
            }

            System.out.println("\nDe ai kiest: "+ Arrays.toString(game.aiMove(2)));
            game.getBoard().showBoard();
            if (CheckRules.checkWinner(game.getBoardArray() ,2, game.getBoardWinLength())) {
                System.out.println("De Ai heeft gewonnen :(");
                break;
            }
            if (CheckRules.checkBoardFull(game.getBoardArray())) {
                System.out.println("Gelijk spel!");
                break;
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


}
