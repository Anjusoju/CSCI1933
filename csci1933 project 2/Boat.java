//Written by turnq070
public class Boat {
    public char num;
    private Cell[] hull;
    private boolean vertical;
    public Boat(int length, boolean vertical, char index){
        hull = new Cell[length];
        this.vertical = vertical;
        this.num = index;
    }
    public Cell[] getHull(){
        return this.hull;
    }
    public boolean getVertical(){
        return this.vertical;
    }
    public void setVertical(boolean vertical){
        this.vertical = vertical;
    }
    public String toString(){
        return "length: " + hull.length + ", vertical?: " + vertical + ", row and col: " + hull[0].row + "," + hull[0].col;
    }
    public boolean isSunk(){
        for (int i = 0; i < hull.length; i++) {
            if(hull[i].getStatus() == num) {
                return false;
            }
        }
        return true;
    }
}
