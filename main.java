import Board;
import java.lang.*;

import TicTacToe;
import java.util.Scanner;
class main{
    public static void main() {
        System.out.println("Welkom bij tic-tac-toe!");
        System.out.print("Wilt u eerst spelen (0), of mag de ai eerst (1):");
        Scanner scanner = new Scanner(System.in);
        int choice = Integer.parseInt(scanner.nextLine());
        TicTacToe game = new TicTacToe;
        if (choice = 1) {
            game.aiMove();
        }
        while (true) {
            
            while (true) {
                int[] move = input();
            }
            
            
        }
    }

    public static int[] input(){
        Scanner scanner = new Scanner(System.in);
        int userInput = Integer.parseInt(scanner.nextLine());
        return userInput;
    }

    public static int[] coordinates(int userinput){
        int x = userinput/3;
        int y = (userinput%3)-1;
        return new int[] {x,y};
    }
}

