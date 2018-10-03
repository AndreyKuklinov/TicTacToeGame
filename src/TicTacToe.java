import java.util.Scanner;

class TicTacToe {

    //Declare fields
    private static Scanner scan = new Scanner(System.in);
    private static BoardSquare[][] gameBoard;
    private static SquareType winner = SquareType.NONE;

    static void start() {
        //Main cycle
        while (true) {
            //Initialize gameBoard
            System.out.print("Hello, player! Please enter the field size: ");
            int fieldSize = scan.nextInt();
            gameBoard = new BoardSquare[fieldSize][fieldSize];
            for (int i = 0; i < gameBoard.length; i++) {
                for (int j = 0; j < gameBoard[i].length; j++) {
                    gameBoard[i][j] = new BoardSquare();
                }
            }

            //Game loop
            do {
                startTurn(SquareType.X);
                if (winner != SquareType.NONE) break;
                startTurn(SquareType.O);
            }
            while (winner == SquareType.NONE);
            draw();
            System.out.println("Player " + winner.toString() + " has won!\n");
        }
    }

    //Starts an iteration of the game loop
    private static void startTurn(SquareType player) {
        draw();
        makeMove(player);
        winner = determineWinner();
    }

    //Draws the gameBoard
    private static void draw() {
        for (BoardSquare[] row : gameBoard) {
            System.out.print("|");
            for (BoardSquare square : row) {
                switch (square.getType()) {
                    case NONE:
                        System.out.print("   |");
                        break;
                    case X:
                        System.out.print(" X |");
                        break;
                    case O:
                        System.out.print(" O |");
                        break;
                }
            }
            System.out.println();
        }
    }

    //Takes input from the player to move
    private static void makeMove(SquareType player) {
        //Input location
        System.out.print("Player " + player.toString() + ", it's your turn!" +
                "\nRow Column: ");
        int row, column;
        do {
            row = scan.nextInt() - 1;
            column = scan.nextInt() - 1;
            if (gameBoard[row][column].getType() != SquareType.NONE) {
                System.out.print("You can't place your piece here!" +
                        "\nRow Column: ");
            }
        }
        while (gameBoard[row][column].getType() != SquareType.NONE);
        gameBoard[row][column].setType(player);
    }

    //Checks if there's a winner
    private static SquareType determineWinner() {
        boolean diagonalX = true, diagonalO = true;
        //Iterate through gameBoard
        for (int i = 0; i < gameBoard.length; i++) {
            //Declare and reset row and column victory booleans
            boolean rowX = true, rowO = true,
                    columnX = true, columnO = true;
            //Iterate through a row and check
            for (int j = 0; j < gameBoard[i].length; j++) {
                if (gameBoard[i][j].getType() != SquareType.X)
                    rowX = false;
                if (gameBoard[i][j].getType() != SquareType.O)
                    rowO = false;
                if (gameBoard[j][i].getType() != SquareType.X)
                    columnX = false;
                if (gameBoard[j][i].getType() != SquareType.O)
                    columnO = false;
                if (i == j && gameBoard[i][j].getType() != SquareType.X)
                    diagonalX = false;
                if (i == j && gameBoard[i][j].getType() != SquareType.O)
                    diagonalO = false;
            }
            //Check for victory
            if (rowX || columnX) return SquareType.X;
            else if (rowO || columnO) return SquareType.O;
        }
        //Check for diagonal victory
        if(diagonalX) return SquareType.X;
        else if(diagonalO) return SquareType.O;


        return SquareType.NONE;
    }


}
