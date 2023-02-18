package tictactoe;

import java.util.Scanner;

public class tictactoe {

    public static void printMatrix(char[][] matrix) {

        System.out.println("---------");
        for (char[] chars : matrix) {
            System.out.print("| ");
            for (char aChar : chars) {
                System.out.print(aChar + " ");
            }
            System.out.print("|\n");
        }
        System.out.println("---------");
    }

    public static boolean isWinner(char[][] matrix, char player) {
        if ((matrix[0][0] == player && matrix[1][1] == player && matrix[2][2] == player) ||
            (matrix[0][2] == player && matrix[1][1] == player && matrix[2][0] == player)){
            return true;
        }
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[0][i] == player && matrix[1][i] == player && matrix[2][i] == player) {
                return true;
            }
        }
        for (char[] chars : matrix) {
            if (chars[0] == player && chars[1] == player && chars[2] == player) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNotDraw(char[][] matrix) {
        for (char[] chars : matrix) {
            for (char aChar : chars) {
                if (aChar == '_' || aChar == ' ') {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isImpossible(char[][] matrix) {
        int amountX = 0,  amountO = 0;
        int difference;

        for (char[] chars : matrix) {
            for (char aChar : chars) {
                if (aChar == 'X') {
                    amountX++;
                }
                else if (aChar == 'O') {
                    amountO++;
                }
            }
        }
        difference = Math.abs(amountX - amountO);
        return difference >= 2;
    }

    public static void inputCheck(char player, char[][] matrix) {
        Scanner scanner = new Scanner(System.in);
        boolean isBreak;
        int x, y;
        do {
            System.out.print("Enter a number: ");
            try {
                x = Integer.parseInt(scanner.next());
                y = Integer.parseInt(scanner.next());

                if ((x > 3 || x < 1) || (y > 3 || y < 1)) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    isBreak = true;
                }
                else {
                    if (matrix[x-1][y-1] == 'X' || matrix[x-1][y-1] == 'O') {
                        System.out.println("This cell is occupied! Choose another one!");
                        isBreak = true;
                    }
                    else {
                        matrix[x-1][y-1] = player;
                        isBreak = false;
                    }
                }

            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
                isBreak = true;
            }
        } while (isBreak);

        printMatrix(matrix);
    }

    public static void main(String[] args) {

        long time = System.currentTimeMillis();

        final int size = 3;
        String input = "_________";
        char[][] matrix = new char[size][size];

        for (int i = 0, x = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                matrix[i][j] = input.toCharArray()[x];
                x++;
            }
        }

        printMatrix(matrix);

        do {

            inputCheck('X', matrix);
            if (isWinner(matrix, 'X')) {
                System.out.println("X wins");
                break;
            }
            if (!isNotDraw(matrix)) {
                System.out.println("Draw");
                break;
            }

            inputCheck('O', matrix);
            if (isWinner(matrix, 'O')) {
                System.out.println("O wins");
                break;
            }
            if (!isNotDraw(matrix)) {
                System.out.println("Draw");
                break;
            }

        } while (true);
        System.out.printf("It took %s seconds to complete this game", time/1000);
    }
}
