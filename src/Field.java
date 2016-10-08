/**
 * Created by Пользователь on 22.09.2016.
 */

/**
 * -1:offset
 * 0:standard
 * 1:difficult
 * 2:dangerous
 * 3:ruin
 * 4:unreached
 */
class Field {
    Cell map[][];

    /**
     * @param xSize map's height-VERTICAL
     * @param ySize map's weight-HORIZONTAL
     *              <p>
     *              Math for offset (((xSize-i)/2)<=j && j<=(ySize - 1 + (xSize-i)/2))
     *              -1 is some magic
     */
    Field(int xSize, int ySize) {
        //set offset for HORIZONTAL line
        map = new Cell[xSize][ySize + xSize / 2];
        Creation(xSize, ySize);
    }

    private void Creation(int xSize, int ySize) {
        for (int i = (xSize - 1); i >= 0; i--) {
            for (int j = ((ySize + xSize / 2) - 1); j >= 0; j--) {
                if (Main.OffsetOut(xSize, ySize, i, j)) {
                    map[i][j] = new Cell(i, j, (byte) Math.round(Math.random() * 4));
                } else {
                    map[i][j] = new Cell(i, j, -1);
                }

            }
        }


    }
}

class Cell {
    final int x;
    final int y;
    final int ground;
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