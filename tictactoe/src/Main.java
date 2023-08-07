import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static final int MAX_DEPTH = 6;
    private static int availableMoves = 3 * 3;
    public static void main(String[] args) {

        // matrix 3x3
        char[][] board = new char[3][3];
        char player = 'X';
        boolean gameOver = false;

        for (char[] chars : board) {
            Arrays.fill(chars, ' ');
        }

        while (!gameOver) {

            switch (player) {
                case 'O' -> playO(board);
                case 'X' -> playX(board);
            }

            gameOver = haveWon(board, player);

            if (gameOver) {
                System.out.printf("Player %c has won: \n", player);
                printBoard(board);
                return;
            }

            player = player == 'X' ? 'O' : 'X';

            printBoard(board);

        }
    }

    private static void playX(char[][] board) {
        int[] xMove = findBestMove(board);

        board[xMove[0]][xMove[1]] = 'X';

        availableMoves--;

    }

    private static void playO(char[][] board) {
        Scanner scanner = new Scanner(System.in);

        print("Player " + 'O' + " enter: ");

        int row = scanner.nextInt();
        int col = scanner.nextInt();

        if (board[row][col] != ' ' || row > 0  ) {
            print("Invalid move. Try Again");
        }

        board[row][col] = 'O';

        availableMoves--;
    }

    private static int[] findBestMove(char[][] board) {

        int[] bestMove = {-1, -1};
        int bestValue = Integer.MIN_VALUE;

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == ' ') {
                    board[row][col] = 'X';
                    int moveValue = minimax(board, MAX_DEPTH, false);
                    board[row][col] = ' ';

                    if (moveValue > bestValue) {
                        bestMove[0] = row;
                        bestMove[1] = col;
                        bestValue = moveValue;
                    }
                }
            }
        }

        return bestMove;
    }

    private static int minimax(char[][] board, int depth, boolean isMax) {
        int boardValue = evaluateBoard(board);

        if (Math.abs(boardValue) == 10 || depth == 0 || availableMoves == 0) {
            return boardValue;
        }

        if (isMax) {
            int highValue = Integer.MIN_VALUE;
            for (int row = 0; row < board.length; row++) {
                for (int col = 0; col < board[row].length; col++) {
                    if (board[row][col] == ' ') {
                        board[row][col] = 'X';
                        highValue = Math.max(highValue, minimax(board, depth - 1, false));
                        board[row][col] = ' ';
                    }
                }
            }

            return highValue;
        }

        int lowValue = Integer.MAX_VALUE;

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == ' ') {
                    board[row][col] = 'O';
                    lowValue = Math.min(lowValue, minimax(board, depth - 1, true));
                    board[row][col] = ' ';
                }
            }
        }

        return lowValue;

    }

    private static int evaluateBoard(char[][] board) {
        if (haveWon(board, 'X')) {
            return 10;
        }

        if (haveWon(board, 'O')) {
            return -10;
        }

        return 0;
    }

    private static boolean haveWon(char[][] board, char player) {

        for (int row = 0; row < board.length; row++) {
            if (board[row][0] == player && board[row][1] == player && board[row][2] == player) {
                return true;
            }
        }

        for (int col = 0; col < board.length; col++) {
            if (board[0][col] == player && board[1][col] == player && board[2][col] == player) {
                return true;
            }
        }

        if (board[0][0] == player && board[1][1] == player && board[2][2] == player)
            return true;

        if (board[0][2] == player && board[1][1] == player && board[2][0] == player)
            return true;

        return false;
    }

    private static void printBoard(char[][] board) {
        for (char[] chars : board) {
            for (char aChar : chars) {
                System.out.print(aChar + " | ");
            }
            System.out.println();
        }
    }

    private static void print(String text) {
        System.out.println(text);
    }
}
