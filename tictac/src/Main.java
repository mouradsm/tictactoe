import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // tabuleiro
        // imprimir tabuleiro
        // verificar vencedor
        // fazer jogadas

        char[][] board =  new char[3][3];

        char player = 'X';
        boolean gameOver =  false;
        Scanner scanner = new Scanner(System.in);

        //inicializando o tabuleiro
        // falar sobre o for melhorado
        for(int row = 0; row < board.length; row++) {
            // falar sobre array fill
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = ' ';
            }
        }

        while(!gameOver) {
            printBoard(board);

            System.out.printf("O jogador %c deve jogar: \n", player);
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            //verifica se a jogada é válida

            if(board[row][col] != ' ') {
                System.out.println("Esta jogada é inválida");
                continue;
            }

            board[row][col] = player;

            // verificar se existe vencedor
            gameOver = haveWon(board, player);

            if(gameOver) {
                System.out.printf("O jogador %c foi o vencedor! \n", player);
                printBoard(board);
            }

            player = player == 'X' ? 'O' : 'X';

        }

    }

    private static boolean haveWon(char[][] board, char player) {
        for (int row = 0; row < board.length; row++) {
            if (board[row][0] == player && board[row][1] == player && board[row][2] == player) {
                return true;
            }

            if (board[0][row] == player && board[1][row] == player && board[2][row] == player) {
                return true;
            }
        }

        //Diagonais
        if(board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }

        if(board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }

        return false;
    }
    private static void printBoard(char[][] board) {
        String format = "|%3c  |";
        System.out.printf("|===================|%n");
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                System.out.format(format, board[row][col]);
                //System.out.print(board[row][col] + " | ");
            }
            System.out.println();
            System.out.printf("|===================|%n");
        }
    }
}
