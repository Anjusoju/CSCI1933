//Written by turnq070
public class Cell {
    public int row;
    public int col;
    public char status;
    public char getStatus(){
        return status;
    }
    public void set_status(char c){
        status = c;
    }
    public boolean equals(Cell other){
        return this.row == other.row && this.col == other.col;
    }
    public Cell(int row, int col, char status){
        this.row = row;
        this.col = col;
        this.status = status;
    }
    public String toString(){
        return "row: " + row + ", col: " + col + ", status: " + status;
    }

}




