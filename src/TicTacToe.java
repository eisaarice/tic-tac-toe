import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class TicTacToe {
    public static void main (String[] args) {
        String [][] board = new String [3][3]; //3 rows x 3 columns
        boolean [][] spaces = new boolean[3][3];
        boolean xTurn = true; //X moves first

        playGame(board, spaces, xTurn);
    } //end main

    public static void fillBoard(String[][] board, boolean[][] spaces) {
        for (String[] strings : board) {
            Arrays.fill(strings, "   ");
        } // end for

        for (boolean[] space : spaces) {
            Arrays.fill(space, false);
        } // end for
    } //end fillBoard

    public static void printBoard(String[][] board) {
        for (int i = 0; i < board.length; i++) {
            if (i != 0) {
                System.out.println("---+---+---");
            }

            for (int j = 0; j < board[i].length; j++) {
                if (j == 2) {
                    System.out.println(board[i][j]);
                }
                else {
                    System.out.print(board[i][j] + "|");
                }
            } //end for j
        } // end for i
    } //end printBoard

    public static void playGame (String[][] board, boolean[][] spaces, boolean xTurn) {
        fillBoard(board, spaces);
        printBoard(board);
        System.out.println("Let the game begin.");

        playTurn(board, spaces, xTurn);
    } //end playGame

    public static void playTurn (String[][] board, boolean[][] spaces, boolean xTurn) {
        Scanner keyboard = new Scanner(System.in);
        int x, y;

        //get user input on board location
        if (xTurn) {
            System.out.println("X, what row would you like? (0, 1, 2)");

        }
        else {
            System.out.println("O, what row would you like? (0, 1, 2)");

        }
        x = keyboard.nextInt();
        keyboard.nextLine();
        if (x != 0 && x != 1 && x != 2) {
            do {
                System.out.println("Invalid input, there are only rows 0, 1, or 2. Please try again.");

                x = keyboard.nextInt();
                keyboard.nextLine();
            } while (x != 0 && x != 1 && x != 2);
        }
        System.out.println("And now, what column? (0, 1, 2)");
        y = keyboard.nextInt();
        keyboard.nextLine();
        if (y != 0 && y != 1 && y != 2) {
            do {
                System.out.println("Invalid input, there are only columns 0, 1, or 2. Please try again.");

                y = keyboard.nextInt();
                keyboard.nextLine();
            } while (y != 0 && y != 1 && y != 2);
        }

        //check space availability
        if (spaces[x][y]) {
            System.out.println("That spot is already taken. Please try again.");
            playTurn(board, spaces, xTurn);
        }
        else {
            spaces[x][y] = true;
        }

        //fill in value with X or O depending on whose turn it is
        if (xTurn) {
            board[x][y] = " X ";
            xTurn = false;
        }
        else {
            board[x][y] = " O ";
            xTurn = true;
        }
        printBoard(board);

        //check if there's a win yet. if not, check if board is full. if neither, continue play
        if (checkBoardFull(spaces)) { //board full, no win
            System.out.println("The board has filled up, the game is a tie.");
            return;
        }
        else if (checkForWin(board)) { //board not full, no win yet
            playTurn(board, spaces, xTurn);
        }
        else {
            return;
        }
    } //end playTurn

    public static boolean checkForWin (String[][] board) { //false if someone won, true if game is still ongoing or a tie
        if ( //if X won
            //ROWS
            (Objects.equals(board[0][0], " X ") && Objects.equals(board[0][1], " X ") && Objects.equals(board[0][2], " X ")) ||
            (Objects.equals(board[1][0], " X ") && Objects.equals(board[1][1], " X ") && Objects.equals(board[1][2], " X ")) ||
            (Objects.equals(board[2][0], " X ") && Objects.equals(board[2][1], " X ") && Objects.equals(board[2][2], " X ")) ||
            //COLUMNS
            (Objects.equals(board[0][0], " X ") && Objects.equals(board[1][0], " X ") && Objects.equals(board[2][0], " X ")) ||
            (Objects.equals(board[0][1], " X ") && Objects.equals(board[1][1], " X ") && Objects.equals(board[2][1], " X ")) ||
            (Objects.equals(board[0][2], " X ") && Objects.equals(board[1][2], " X ") && Objects.equals(board[2][2], " X ")) ||
            //DIAGONALS
            (Objects.equals(board[0][0], " X ") && Objects.equals(board[1][1], " X ") && Objects.equals(board[2][2], " X ")) ||
            (Objects.equals(board[2][0], " X ") && Objects.equals(board[1][1], " X ") && Objects.equals(board[0][2], " X "))
        ) {
            System.out.println("X wins!");
            return false;
        } else if ( //if O won
                //ROWS
            (Objects.equals(board[0][0], " O ") && Objects.equals(board[0][1], " O ") && Objects.equals(board[0][2], " O ")) ||
            (Objects.equals(board[1][0], " O ") && Objects.equals(board[1][1], " O ") && Objects.equals(board[1][2], " O ")) ||
            (Objects.equals(board[2][0], " O ") && Objects.equals(board[2][1], " O ") && Objects.equals(board[2][2], " O ")) ||
            //COLUMNS
            (Objects.equals(board[0][0], " O ") && Objects.equals(board[1][0], " O ") && Objects.equals(board[2][0], " O ")) ||
            (Objects.equals(board[0][1], " O ") && Objects.equals(board[1][1], " O ") && Objects.equals(board[2][1], " O ")) ||
            (Objects.equals(board[0][2], " O ") && Objects.equals(board[1][2], " O ") && Objects.equals(board[2][2], " O ")) ||
            //DIAGONALS
            (Objects.equals(board[0][0], " O ") && Objects.equals(board[1][1], " O ") && Objects.equals(board[2][2], " O ")) ||
            (Objects.equals(board[2][0], " O ") && Objects.equals(board[1][1], " O ") && Objects.equals(board[0][2], " O "))
        ) {
            System.out.println("O wins!");
            return false;
        } else {
            return true;
        }
    } //end checkForWin

    public static boolean checkBoardFull (boolean[][] spaces) {
        //whoa
        return spaces[0][0] && spaces[0][1] && spaces[0][2] &&
               spaces[1][0] && spaces[1][1] && spaces[1][2] &&
               spaces[2][0] && spaces[2][1] && spaces[2][2];
    } //end checkBoardFull
}