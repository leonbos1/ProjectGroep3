import java.lang.*;
import java.util.Scanner;
class main{
    public static void main(String[] args) {
        System.out.println("Welkom bij tic-tac-toe!");
        System.out.print("Wilt u eerst spelen (0), of mag de ai eerst (1): ");
        Scanner scanner = new Scanner(System.in);
        int choice = Integer.parseInt(scanner.nextLine());

        TicTacToe game = new TicTacToe();
        if (choice == 1) {
            System.out.println("\nDe ai kiest: "+game.aiMove(2));
        }
        game.getBoard().showBoard();

        while (true) {
            while (true) {
                int[] move = input();
                if (game.makeMove(move[0], move[1])) {
                    game.getBoard().showBoard();
                    break;
                }
            }

            if (CheckRules.checkWinner(game.getBoardArray() ,1)) {
                System.out.println("Jij wint!");
                break;
            }
            if (CheckRules.checkBoardFull(game.getBoardArray())) {
                System.out.println("Gelijk spel!");
                break;
            }

            System.out.println("\nDe ai kiest: "+game.aiMove(2));
            game.getBoard().showBoard();
            if (CheckRules.checkWinner(game.getBoardArray() ,2)) {
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
        System.out.print("\nKies een set (1-9): ");
        Scanner scanner = new Scanner(System.in);
        int[] userInput = coordinates(Integer.parseInt(scanner.nextLine()));
        return userInput;
    }

    public static int[] coordinates(int userinput){
        int x = (userinput-1)/3;
        int y = (userinput-x*3)-1;
        return new int[] {x,y};
    }
}
