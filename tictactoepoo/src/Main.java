import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        System.out.println(Mark.X.getMark() * 3);

        Board board = new Board();

        Scanner scanner = new Scanner(System.in);

        while (!board.isGameOver()) {
            Board.printBoard(board);

            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (!board.placeMark(row, col)) {
                System.out.println("Jogada Inválida!");
                continue;
            }

        }


    }
}
