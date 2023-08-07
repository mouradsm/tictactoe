public class Board {
    private final Mark[][] board;

    private final int BOARD_WIDTH = 3;
    private boolean gameOver;
    private boolean isXTurn;
    private Mark winningMark;

    public Board() {
        board = new Mark[BOARD_WIDTH][BOARD_WIDTH];

        gameOver = false;
        isXTurn = true;

        initBoard();
    }

    private void initBoard() {
        for (int row = 0; row < BOARD_WIDTH; row++) {
            for (int col = 0; col < BOARD_WIDTH; col++) {
                board[row][col] = Mark.BLANK;
            }
        }
    }

    public static void printBoard(Board board) {
        for (int i = 0; i < board.board.length; i++) {
            for (int j = 0; j < board.board[i].length; j++) {
                System.out.print(board.board[i][j] + " | ");
            }
            System.out.println();
        }
    }

    public boolean placeMark(int row, int col) {

        if (row < 0 || row >= BOARD_WIDTH || col < 0 || col >= BOARD_WIDTH || isTileMarked(row, col) || gameOver) {
            return false;
        }

        this.board[row][col] = isXTurn ? Mark.X : Mark.O;

        togglePlayer();
        checkWin(row, col);

        // pensar numa meneira de imprimir o resultado lá no método main


        return true;
    }

    public boolean isTileMarked(int row, int col) {
        return this.board[row][col].isMarked();
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void togglePlayer() {
        isXTurn = !isXTurn;
    }

    public Mark getMarkAt(int row, int col) {
        return board[row][col];
    }

    private boolean checkWin(int row, int col) {

        int sum = 0;

        //check row
        for (int c = 0; c < BOARD_WIDTH; c++ ) {

        }

        if(calculateWinner(sum) != Mark.BLANK) {
            return true;
        }

        return false;



    }

    private Mark calculateWinner(int sum) {
        int XWins = Mark.X.getMark() * BOARD_WIDTH;
        int OWins = Mark.O.getMark() * BOARD_WIDTH;


        if(sum == XWins) {
            gameOver = true;
            winningMark = Mark.X;

            return Mark.X;
        }

        if(sum == OWins) {
            gameOver = true;
            winningMark = Mark.O;

            return Mark.O;
        }

        return Mark.BLANK;
    }
}
