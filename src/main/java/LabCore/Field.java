package LabCore;

import java.util.ArrayList;

/**
 * Created by alecxanrys
 * -1:offset
 * 0:standard
 * 1:difficult
 * 2:dangerous
 * 3:ruin
 * 4:unreached
 */
public class Field{
    public final Cell map[][];
    private final ArrayList<Shift> shifts;

    private final int xSize, ySize;

    /**
     * @param xSize
     *         map's height-VERTICAL
     * @param ySize
     *         map's weight-HORIZONTAL
     *         <p>
     *         Math for offset (((xSize-i)/2)<=j && j<=(ySize - 1 + (xSize-i)/2))
     *         -1 is some magic
     */
    private Field(Builder builder){
        //set offset for HORIZONTAL line
        xSize=builder.xSize;
        ySize=builder.ySize;
        map=builder.map;
        shifts=builder.shifts;
    }



    public static class Builder
    {
        private final int xSize;
        private final int ySize;
        private Cell map[][];
        private ArrayList<Shift> shifts=new ArrayList<>();
        public Builder(int xSize,int ySize)
        {
            this.xSize=xSize;
            this.ySize=ySize;
        }
        public Builder GeneratedMap(int type){//4 by default
            map = new Cell[xSize][ySize + xSize / 2];
            for (int x=(xSize-1); x>=0; x--) {
                for (int y=((ySize+xSize/2)-1); y>=0; y--) {
                    if (OffsetOut(x,y)) {
                        map[x][y]=new Cell(x,y,(byte) Math.round(Math.random()*type));
                    }
                    else {
                        map[x][y]=new Cell(x,y,-1);
                    }

                }
            }
            return this;
        }
        public Builder PreGenerated(String rawData)
        {
            return this;
        }
        public Builder Shifts(int[][] shift)
        {
            for (int[] aShift : shift) {
                shifts.add(new Shift(aShift[0],aShift[1]));
            }
            return this;
        }
        public Field build()
        {
            return new Field(this);
        }
        private boolean OffsetOut(int x,int y){
            return ((xSize-x-1)/2)<=y && y<=(ySize-1+(xSize-x-1)/2);
        }
    }


    /**
     * @param current
     *         around this find neighbors
     * @return ArrayList of Neighbors
     * @see Main
     * @see PathFinder
     * @see LOSChecker
     */
    ArrayList<Cell> Neighbors(Cell current){
        ArrayList<Cell> neighbors=new ArrayList<>(6);

        Cell trying;
        int x=current.x;
        int y=current.y;

        for (Shift shift : shifts) {
            try {
                trying=map[x+shift.x][y+shift.y];
                if (trying.ground!=-1) {
                    neighbors.add(trying);
                }
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
        }

        neighbors.trimToSize();
        return neighbors;
    }

    private static class Shift{
        int x;
        int y;

        Shift(int x,int y){
            this.x=x;
            this.y=y;
        }
    }
}

