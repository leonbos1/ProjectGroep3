import java.lang.*;
import java.util.Scanner;
class main{
    public static void main(String[] args) {
        System.out.println("Welkom bij tic-tac-toe!");
        System.out.print("Wilt u eerst spelen (0), of mag de ai eerst (1):");
        Scanner scanner = new Scanner(System.in);
        int choice = Integer.parseInt(scanner.nextLine());

        TicTacToe game = new TicTacToe();
        if (choice == 1) {
            game.aiMove(2);
        }

        while (true) {
            while (true) {
                int[] move = input();
                if (game.makeMove(move[0], move[1])) {
                    break;
                }
            }

            if (CheckRules.checkWinner(game.getBoardArray() ,1)) {
                System.out.println("De Ai heeft gewonnen :(");
                break;
            }

            game.aiMove(2);
            if (CheckRules.checkWinner(game.getBoardArray() ,2)) {
                System.out.println("Jij wint!");
                break;
            }
        }
    }

    public static int[] input(){
        Scanner scanner = new Scanner(System.in);
        int[] userInput = coordinates(Integer.parseInt(scanner.nextLine()));
        return userInput;
    }

    public static int[] coordinates(int userinput){
        int x = userinput/3;
        int y = (userinput%3)-1;
        return new int[] {x,y};
    }
}
