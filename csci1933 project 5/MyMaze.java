// Names: Daniel Turnquist
// x500s: turnq070

import java.util.Random;

public class MyMaze{
    Cell[][] maze;

    public MyMaze(int rows, int cols) {
        maze = new Cell[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                maze[row][col] = new Cell();
            }
        }
    }

    /* Create a new maze using the algorithm found in the writeup. */
    public static MyMaze makeMaze(int rows, int cols) {
        MyMaze mazeObject = new MyMaze(rows, cols);
        //the recording of cells will be done by recording their coordinates
        Stack1Gen<int[]> coordinateStack = new Stack1Gen<>();
        coordinateStack.push(new int[]{0, 0});
        mazeObject.maze[0][0].setVisited(true);
        int[] current = coordinateStack.top();
        int[] neighbor;
        while (!coordinateStack.isEmpty()) {
            current = coordinateStack.top();
            //check if the current spot is a dead end
            if (mazeObject.deadEndCheck(current[0], current[1])) {
                //pops if so
                coordinateStack.pop();
            } else {
                //gets a random unvisited neighbor
                neighbor = mazeObject.getRandomUnvisitedNeighbor(current[0], current[1]);
                //pushes said neighbor
                coordinateStack.push(neighbor);
                //set neighbor to visited
                mazeObject.maze[neighbor[0]][neighbor[1]].setVisited(true);
                //removes wall between current spot and neighboring spot
                mazeObject.removeWall(current[0], current[1], neighbor[0], neighbor[1]);
            }
        }
        //resets all spots to unvisited
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                mazeObject.maze[row][col].setVisited(false);
            }
        }
        //this is done to make the maze print properly
        mazeObject.maze[rows-1][cols-1].setRight(false);
        return mazeObject;
    }

    private int[] getRandomUnvisitedNeighbor(int row, int col){
        int[] result = null;
        //initializes the three possibilities for shifting
        int[] indexShiftValues = new int[]{-1,0,1};
        Random random = new Random();
        int rowShift = 0;
        int colShift = 0;
        while (result == null){
            try {
                //this part tries to find a random neighbor 1 cell away
                rowShift = indexShiftValues[random.nextInt(3)];
                colShift = indexShiftValues[random.nextInt(3)];
                //checks that the neighboring cell is unvisited, and that it is not "diagonally" next to the cell
                if (!maze[row + rowShift][col + colShift].getVisited() && (rowShift == 0 || colShift == 0)){
                    result = new int[]{row + rowShift, col + colShift};
                }
            } catch (IndexOutOfBoundsException ignored){
            }
        }
        return result;
    }

    private boolean deadEndCheck(int row, int col){
        //checks each neighboring spot for whether it's visited or out of bounds
        try {
            if(!maze[row+1][col].getVisited()){
                return false;
            }
        } catch (IndexOutOfBoundsException ignored){
        }
        try {
            if(!maze[row-1][col].getVisited()){
                return false;
            }
        } catch (IndexOutOfBoundsException ignored){
        }
        try {
            if(!maze[row][col-1].getVisited()){
                return false;
            }
        } catch (IndexOutOfBoundsException ignored){
        }
        try {
            if(!maze[row][col+1].getVisited()){
                return false;
            }
        } catch (IndexOutOfBoundsException ignored){
        }
        return true;
    }

    private void removeWall(int aRow, int aCol, int bRow, int bCol){
        //if these are in the same row
        if (aRow == bRow){
            maze[aRow][Math.min(aCol, bCol)].setRight(false);
            return;
        }
        //if in same column
        maze[Math.min(aRow, bRow)][aCol].setBottom(false);

    }

    /* Print a representation of the maze to the terminal */
    public void printMaze() {
        StringBuilder sb = new StringBuilder();
        //adds the implicit top wall
        for (int i = 0; i < maze[0].length; i++) {
            sb.append(" ___");
        }
        sb.append("\n");
        for (int row = 0; row < maze.length; row++) {
            //each cell is two lines tall
            for (int i = 0; i < 2; i++) {
                //makes sure the entrance is unobstructed
                if (row != 0){
                    sb.append("|");
                } else {
                    sb.append(" ");
                }
                for (int col = 0; col < maze[row].length; col++) {
                    if (i == 0){
                        sb.append(" ");
                        if (maze[row][col].getVisited()){
                            sb.append("* ");
                        } else {
                            sb.append("  ");
                        }
                        if (maze[row][col].getRight()){
                            sb.append("|");
                        } else {
                            sb.append(" ");
                        }
                    } else {
                        if (maze[row][col].getBottom()){
                            sb.append("___");
                        } else {
                            sb.append("   ");
                        }
                        if (maze[row][col].getRight()){
                            sb.append("|");
                        } else {
                            sb.append(" ");
                        }
                    }
                }
                sb.append("\n");
            }
        }
        //this gets rid of the extra \n at the end
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb.toString());
    }

    /* Solve the maze using the algorithm found in the writeup. */
    public void solveMaze() {
        //sets finish to the last cell
        int[] finish = new int[]{maze.length-1, maze[0].length-1};
        Q1Gen<int[]> coordQueue = new Q1Gen<>();
        coordQueue.add(new int[]{0, 0});
        int[] current;
        while (!coordQueue.isEmpty()){
            current = coordQueue.remove();
            //sets the maze at current cell to visited
            maze[current[0]][current[1]].setVisited(true);
            if (current[0] == finish[0] && current[1] == finish[1] ){
                break;
            }
            enQueueNeighbors(coordQueue, current);
        }
        printMaze();
    }

    public void enQueueNeighbors(Q1Gen<int[]> queue, int[] cords){
        //declares the 4 possible neighbors
        int[] upN, downN, rightN, leftN;
        //will store neighbors
        int[][] neighbors = new int[4][];
        //sets the neighbors
        upN = new int[]{cords[0] - 1, cords[1]};
        neighbors[0] = upN;
        downN = new int[]{cords[0] + 1, cords[1]};
        neighbors[1] = downN;
        rightN = new int[]{cords[0], cords[1] + 1};
        neighbors[2] = rightN;
        leftN = new int[]{cords[0], cords[1] - 1};
        neighbors[3] = leftN;
        for (int i = 0; i < neighbors.length; i++) {
            //checks that there is not a wall between the current cell and the candidate neighbor
            if (!wallBetween(cords, neighbors[i])) {
                try {
                    //checks that the candidate neighbor hasn't been visited
                    if (!maze[neighbors[i][0]][neighbors[i][1]].getVisited()){
                        queue.add(neighbors[i]);
                    }
                } catch (ArrayIndexOutOfBoundsException ignored){
                }
            }
        }
    }

    public boolean wallBetween(int[] firstCellCoords, int[] secondCellCoords){
        try {
            //if same row
            if (firstCellCoords[0] == secondCellCoords[0]) {
                return maze[firstCellCoords[0]][Math.min(firstCellCoords[1], secondCellCoords[1])].getRight();
            }
            //if same column
            return maze[Math.min(firstCellCoords[0], secondCellCoords[0])][firstCellCoords[1]].getBottom();
        } catch (ArrayIndexOutOfBoundsException exception){
            return true;
        }
    }

    public static void main(String[] args){
        /* Any testing can be put in this main function */
        //makeMaze(20, 20).solveMaze();

    }
}
