//Written by turnq070

import java.util.Scanner;

public class BattleBoatsGame {

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        System.out.println("Hello welcome to BattleBoats! Please enter 1 to play in standard mode or 2 to play in expert");
        String input = scnr.nextLine();
        while (!input.equals("1") && !input.equals("2")) {
            System.out.println("Invalid input. Please enter 1 to play in standard mode or 2 to play in expert.");
            input = scnr.nextLine();
        }
        BattleBoatsBoard board = new BattleBoatsBoard(Integer.parseInt(input));
//        System.out.println("\n" + board);
//        for (int i = 0; i < board.boatsArray.length; i++) {
//            System.out.println("boat# " + (i + 1) + ": " + board.boatsArray[i].toString());
//        }

//        board.boatsArray[0].getHull()[0] = new Cell(1,1, 'B');
//        board.placeBoat(board.boatsArray[0]);
        board.placeBoats();
        int gameMode = Integer.parseInt(input);
        boolean gameOver = false;
        int inputRow;
        int inputCol;
        while (!gameOver) {
            System.out.println("\n" + board.display() + "\n");
            System.out.println("Guess a location in the form: x y");
            input = scnr.nextLine();
            if (input.equals("print")) {
                System.out.println(board);
            } else if (input.equals("drone")) {
                while (true){
                    System.out.println("enter row or col to select which to scan:");
                    input = scnr.nextLine();
                    if(input.equals("row") || input.equals("col")){
                        break;
                    }
                    System.out.println("invalid input.");
                }
                int inputIndex;
                while (true){
                    System.out.println("enter index to scan:");
                    inputIndex = scnr.nextInt();
                    if(inputIndex > 0 && inputIndex < board.boardMatrix.length){
                        break;
                    }
                    System.out.println("invalid input.");
                }
                System.out.println(board.drone(input, inputIndex));

            } else {
                inputRow = Integer.parseInt(input.split(" ")[0]);
                inputCol = Integer.parseInt(input.split(" ")[1]);
                board.fire(inputRow, inputCol, gameMode);
            }
            if (board.numRemainingShips <= 0) {
                gameOver = true;
            }
        }
        System.out.println("Game completed in " + board.turn + " turns and " + board.shots + " shots!");
    }
}
