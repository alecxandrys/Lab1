package LabCore; /**
 * Created by Пользователь on 22.09.2016.
 */

import java.util.ArrayList;

/**
 * -1:offset
 * 0:standard
 * 1:difficult
 * 2:dangerous
 * 3:ruin
 * 4:unreached
 */
public class Field {
    public Cell map[][];
    private ArrayList<Shift> shifts;

    private int xSize,ySize;

    /**
     * @param xSize map's height-VERTICAL
     * @param ySize map's weight-HORIZONTAL
     *              <p>
     *              Math for offset (((xSize-i)/2)<=j && j<=(ySize - 1 + (xSize-i)/2))
     *              -1 is some magic
     */
    public Field(int xSize, int ySize) {
        //set offset for HORIZONTAL line
        this.xSize=xSize;
        this.ySize=ySize;
        map = new Cell[xSize][ySize + xSize / 2];
        Creation();
        shifts = new ArrayList<>(6);
        shifts.add(new Shift(-1, 0));
        shifts.add(new Shift(-1, +1));
        shifts.add(new Shift(0, -1));
        shifts.add(new Shift(0, +1));
        shifts.add(new Shift(+1, 0));
        shifts.add(new Shift(+1, -1));
        shifts.trimToSize();
    }

    private void Creation() {
        for (int x = (xSize - 1); x >= 0; x--) {
            for (int y = ((ySize + xSize / 2) - 1); y >= 0; y--) {
                if (OffsetOut(x, y)) {
                    map[x][y] = new Cell(x, y, (byte) Math.round(Math.random() * 4));
                } else {
                    map[x][y] = new Cell(x, y, -1);
                }

            }
        }


    }

    /**
     * @param current around this find neighbors
     * @return ArrayList of Neighbors
     * @see Main
     * @see PathFinder
     * @see LOSChecker
     */
    ArrayList<Cell> Neighbors(Cell current) {
        ArrayList<Cell> neighbors = new ArrayList<>(6);

        Cell trying;
        int x = current.x;
        int y = current.y;

        for (Shift shift : shifts) {
            try {
                trying = map[x + shift.x][y + shift.y];
                if (trying.ground != -1) {
                    neighbors.add(trying);
                }
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
        }

        neighbors.trimToSize();
        return neighbors;
    }

    private class Shift {
        int x;
        int y;

        Shift(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private boolean OffsetOut(int x, int y) {
        return ((xSize - x-1) / 2) <= y && y <= (ySize - 1 + (xSize - x-1) / 2);
    }
}

