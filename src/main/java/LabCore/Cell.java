package LabCore;
/**
 * Created by alecxanrys
 */

public class Cell {
    final int x;
    final int y;
    final public int ground;
    int cost = -1;


    Cell(int x, int y, int ground) {
        this.x = x;
        this.y = y;
        this.ground = ground;
    }

    @Override
    public String toString() {
        return getClass().getName() + " x=" + this.x + " y=" + this.y;
    }
}
