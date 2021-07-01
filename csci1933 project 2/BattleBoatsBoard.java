//Written by turnq070
public class BattleBoatsBoard {
    public Cell[][] boardMatrix;
    public Boat[] boatsArray;
    public int shots = 0;
    public int turn = 1;
    public int numRemainingShips;
    public char[][] gui;
    public int remainingDrones;


    public BattleBoatsBoard(int selection) {
        if (selection == 1) {
            boardMatrix = new Cell[8][8];
            gui = new char[8][8];
            boatsArray = new Boat[5];
            boatsArray[0] = new Boat(5, getRandomBoolean(), '0');
            boatsArray[1] = new Boat(4, getRandomBoolean(), '1');
            boatsArray[2] = new Boat(3, getRandomBoolean(), '2');
            boatsArray[3] = new Boat(3, getRandomBoolean(), '3');
            boatsArray[4] = new Boat(2, getRandomBoolean(), '4');
            numRemainingShips = 5;
            remainingDrones = 1;


        } else if (selection == 2) {
            boardMatrix = new Cell[12][12];
            gui = new char[12][12];
            boatsArray = new Boat[10];
            boatsArray[0] = new Boat(5, getRandomBoolean(), '0');
            boatsArray[1] = new Boat(5, getRandomBoolean(), '1');
            boatsArray[2] = new Boat(4, getRandomBoolean(), '2');
            boatsArray[3] = new Boat(4, getRandomBoolean(), '3');
            boatsArray[4] = new Boat(3, getRandomBoolean(), '4');
            boatsArray[5] = new Boat(3, getRandomBoolean(), '5');
            boatsArray[6] = new Boat(3, getRandomBoolean(), '6');
            boatsArray[7] = new Boat(3, getRandomBoolean(), '7');
            boatsArray[8] = new Boat(2, getRandomBoolean(), '8');
            boatsArray[9] = new Boat(2, getRandomBoolean(), '9');
            numRemainingShips = 10;
            remainingDrones = 2;

        }
        for (int i = 0; i < boardMatrix.length; i++) {
            for (int j = 0; j < boardMatrix[i].length; j++) {
                this.boardMatrix[i][j] = new Cell(i, j, '-');
                this.gui[i][j] = '-';
            }
        }
    }

    public void placeBoats() {
        for (int i = 0; i < boatsArray.length; i++) {
            boatsArray[i] = getValidBoat(boatsArray[i]);
            placeBoat(boatsArray[i]);
            //System.out.println("i: " + i + " " + boatsArray[i]);
        }
    }

    public Boat getValidBoat(Boat b) {
        Boat test = new Boat(b.getHull().length, b.getVertical(), b.num);
        while (true) {
            test.getHull()[0] = new Cell(getRandomCoord(1), getRandomCoord(1), 'B');
            if (!isOutOfBounds(test) && !isOverLapping(test)) {
                break;
            }

        }
        return test;
    }

    public boolean isOutOfBounds(Boat b) {
        if (b.getVertical()) {
            return b.getHull()[0].row > (boardMatrix.length - b.getHull().length);
        }
        return b.getHull()[0].col > (boardMatrix.length - b.getHull().length);
    }

    public boolean isOverLapping(Boat b) {
        if (b.getVertical()) {
            for (int i = 0; i < b.getHull().length; i++) {
                if (boardMatrix[b.getHull()[0].row + i][b.getHull()[0].col].getStatus() == 'B') {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < b.getHull().length; i++) {
                if (boardMatrix[b.getHull()[0].row][b.getHull()[0].col + i].getStatus() == 'B') {
                    return true;
                }
            }
        }
        return false;
    }

    public void placeBoat(Boat b) {
        if (b.getVertical()) {
            for (int i = 0; i < b.getHull().length; i++) {
                b.getHull()[i] = new Cell(b.getHull()[0].row + i, b.getHull()[0].col, 'B');
                this.boardMatrix[b.getHull()[i].row][b.getHull()[i].col] = b.getHull()[i];
            }
        } else {
            for (int i = 0; i < b.getHull().length; i++) {
                b.getHull()[i] = new Cell(b.getHull()[0].row, b.getHull()[0].col + i, 'B');
                this.boardMatrix[b.getHull()[i].row][b.getHull()[i].col] = b.getHull()[i];
            }
        }
    }

    public boolean getRandomBoolean() {
        return Math.random() >= 0.5;
    }

    public int getRandomCoord(int selection) {
        if (selection == 1) {
            return (int) Math.floor(Math.random() * 8);
        }
        return (int) Math.floor(Math.random() * 12);
    }

    public String toString() {
        String out = "";
        for (int i = 0; i < boardMatrix.length; i++) {
            for (int j = 0; j < boardMatrix[i].length; j++) {
                if ((boardMatrix[i][j].status) == 'B') {
                    determineBoat(i, j);
                }
                out = out.concat(String.valueOf(boardMatrix[i][j].status));
            }
            out = out.concat("\n");
        }
        return out;
    }

    public void determineBoat(int i, int j) {
        for (int k = 0; k < boatsArray.length; k++) {
            for (int l = 0; l < boatsArray[k].getHull().length; l++) {
                if (boardMatrix[i][j].equals(boatsArray[k].getHull()[l])) {
                    boardMatrix[i][j].set_status((char) (k + '0'));
                }
            }
        }
    }

    public String display() {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < gui.length; i++) {
            for (int j = 0; j < gui.length; j++) {
                out.append(gui[i][j]);

            }
            out.append("\n");
        }
        return out.toString();
    }

    public void fire(int inputRow, int inputCol, int gameMode) {

        if ((inputRow > 7 && gameMode == 1) || (inputCol > 7 && gameMode == 1) || inputCol < 0 || inputRow < 0) {
            System.out.println("Penalty");
            this.turn++;
            this.turn++;
            this.shots++;
        } else if ((this.boardMatrix[inputRow][inputCol].getStatus() == 'H') || this.boardMatrix[inputRow][inputCol].getStatus() == 'M') {
            System.out.println("Penalty");
            this.turn++;
            this.turn++;
            this.shots++;
        } else if (this.boardMatrix[inputRow][inputCol].getStatus() == 'B' ||
                this.boardMatrix[inputRow][inputCol].getStatus() == '0' ||
                this.boardMatrix[inputRow][inputCol].getStatus() == '1' ||
                this.boardMatrix[inputRow][inputCol].getStatus() == '2' ||
                this.boardMatrix[inputRow][inputCol].getStatus() == '3' ||
                this.boardMatrix[inputRow][inputCol].getStatus() == '4' ||
                this.boardMatrix[inputRow][inputCol].getStatus() == '5' ||
                this.boardMatrix[inputRow][inputCol].getStatus() == '6' ||
                this.boardMatrix[inputRow][inputCol].getStatus() == '7' ||
                this.boardMatrix[inputRow][inputCol].getStatus() == '8' ||
                this.boardMatrix[inputRow][inputCol].getStatus() == '9') {
            System.out.println("Hit!");
            this.turn++;
            this.shots++;
            this.boardMatrix[inputRow][inputCol].set_status('H');
            this.gui[inputRow][inputCol] = 'X';
            for (int i = 0; i < this.boatsArray.length; i++) {
                for (int j = 0; j < this.boatsArray[i].getHull().length; j++) {
                    if (this.boatsArray[i].getHull()[j].equals(this.boardMatrix[inputRow][inputCol])) {
                        if (this.boatsArray[i].isSunk()) {
                            System.out.println("Sunk!");
                            this.numRemainingShips--;
                        }
                    }
                }
            }

        } else if (this.boardMatrix[inputRow][inputCol].getStatus() == '-') {
            System.out.println("Miss!");
            this.turn++;
            this.shots++;
            this.boardMatrix[inputRow][inputCol].set_status('M');
            this.gui[inputRow][inputCol] = 'O';
        }
    }

    public String drone(String input, int index) {
        int count = 0;
        if (input.equals("row")) {
            for (int i = 0; i < boardMatrix[index].length; i++) {
                if (boardMatrix[index][i].getStatus() != '-') {
                    count++;
                }
            }
        } else if (input.equals("col")) {
            for (int i = 0; i < boardMatrix[index].length; i++) {
                if (boardMatrix[i][index].getStatus() != '-') {
                    count++;
                }
            }
        }
        return String.valueOf(count);
    }
}