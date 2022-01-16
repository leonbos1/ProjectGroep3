package src.main.java.main;

import src.main.java.reversi.Reversi;
import src.main.java.tictactoe.CheckRulesTicTacToe;
import src.main.java.tictactoe.TicTacToe;

import java.lang.*;
import java.util.Arrays;
import java.util.Scanner;

class main{

    //zwart is 1
    //wit is 2

    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.main(args);
        //cmdReversi();
        /*
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wilt u verbinden met de server(0) of zelf tegen de AI spelen(1): ");
        int choice = Integer.parseInt(scanner.nextLine());
        if (choice == 0) {
        try {
            serverTicTacToe();
        } catch (IOException | InterruptedException e) {
            System.out.println("error");
        }

        } else if (choice == 1) {
            cmdTicTacToe();
        } else {
            System.out.println("Verkeerde invoer waarde, probeer het opnieuw.");
        }
         */

    }


    public static void cmdTicTacToe() {
        while (true) {
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

            if (game.getBoardWinLength() > game.getBoardArray().length) {
                System.out.println("De win lengte kan niet groter zijn dan het bord zelf!");
                running = false;
            } else if (game.getBoardWinLength() <= 1) {
                System.out.println("De win lengte moet groter zijn dan 1");
                running = false;
            }

            if (running) {
                if (choice == 1) {
                    int[] aiArray;
                    aiArray = game.aiMove(2);
                    System.out.println("\nDe ai kiest: " + Arrays.toString(aiArray));
                }

                System.out.println();
                game.getBoard().showBoard();
            }

            while (running) {
                while (true) {
                    int[] move = input();
                    if (game.makeMove(move[0] - 1, move[1] - 1)) {
                        game.getBoard().showBoard();
                        break;
                    }
                }

                if (CheckRulesTicTacToe.checkWinner(game.getBoardArray(), 1, game.getBoardWinLength())) {
                    System.out.println("Jij wint!");
                }
                if (CheckRulesTicTacToe.checkBoardFull(game.getBoardArray())) {
                    System.out.println("Gelijk spel!");
                }

                int[] aiArray;
                aiArray = game.aiMove(2);
                System.out.println("\nDe ai kiest: " + Arrays.toString(aiArray));

                game.getBoard().showBoard();
                if (CheckRulesTicTacToe.checkWinner(game.getBoardArray(), 2, game.getBoardWinLength())) {
                    System.out.println("De Ai heeft gewonnen :(");
                }
                if (CheckRulesTicTacToe.checkBoardFull(game.getBoardArray())) {
                    System.out.println("Gelijk spel!");
                }
            }
            if (restart()) {
                break;
            }
        }
    }

    public static void cmdReversi() {
        System.out.println("Welkom bij reversi!");
        while (true) {
            System.out.print("Wilt u eerst spelen (0), of mag de ai eerst (1): ");
            Scanner scanner = new Scanner(System.in);
            int choice = Integer.parseInt(scanner.nextLine());

            int player;
            if (choice == 0) {
                player = 1;
            } else {
                player = 2;
            }

            System.out.println("U bent speler " + player + ".");

            Reversi game = new Reversi(player);
            int[] move;
            int turn = 1;
            boolean pass = false;
            game.getBoard().showBoard();

            while (true) {
                if (game.canPlay(turn)) {
                    if (player == turn) {
                        while (true) {
                            move = input();
                            if (game.playerMove(move[0] - 1, move[1] - 1)) {
                                System.out.println();
                                game.getBoard().showBoard();
                                break;
                            }
                        }
                    } else {
                        move = game.AIMove(turn);
                        System.out.println("De ai doet row: " + (move[0] + 1) + " col: " + (move[1] + 1));
                        game.getBoard().showBoard();
                    }
                    pass = false;

                } else if (!pass) {
                    System.out.println("Speler " + turn + " past.");
                    pass = true;
                    game.getBoard().showBoard();

                } else {
                    System.out.println("Speler " + turn + " past.");
                    game.getBoard().showBoard();

                    System.out.println("Het spel is afgelopen, het resultaat is:");
                    int score1 = game.playerScore(1);
                    int score2 = game.playerScore(2);
                    System.out.println("Speler 1: " + score1);
                    System.out.println("Speler 2: " + score2);

                    if (score1 == score2) {
                        System.out.println("Gelijkspel, goed gespeeld.");
                        break;
                    } else if (score1 > score2) {
                        System.out.print("Speler 1 wint!");
                        if (player == 1) {
                            System.out.println("Gefeliciteerd!");
                        } else {
                            System.out.println("Volgende keer beter.");
                        }
                        break;
                    } else {
                        System.out.print("Speler 2 wint!");
                        if (player == 2) {
                            System.out.println("Gefeliciteerd!");
                        } else {
                            System.out.println("Volgende keer beter.");
                        }
                        break;
                    }
                }
                if (turn == 1) {
                    turn = 2;
                } else {
                    turn = 1;
                }
            }
            if (restart()) {
                break;
            }
        }
    }

    public static int[] input(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Kies een row: ");
        int x = Integer.parseInt(scanner.nextLine());
        System.out.println("Kies een column: ");
        int y = Integer.parseInt(scanner.nextLine());
        return new int[]{x,y};
    }

    public static boolean restart(){
        while (true) {
            System.out.println("Wilt u opnieuw spelen? Toets y of n: ");
            Scanner scanner = new Scanner(System.in);
            String next = scanner.nextLine();
            if (next.equals("y")) {
                return false;
            }
            if (next.equals("n")) {
                System.out.println("Tot ziens!");
                return true;
            }
            System.out.println("Probeer dat opnieuw.");
        }
    }


}
