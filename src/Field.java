/**
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
    /**
     * We have a problem with odd and even number fo line/
     *
     * @param current around this find neighbors
     * @return ArrayList of Neighbors
     * @see Main
     * @see PathFinder
     * @see LOSChecker
     */
    ArrayList<Cell> Neighbors(Cell current) {
        ArrayList<Cell> neighbors = new ArrayList<>(6);
        int x = current.x;
        int y = current.y;

        //first line
        //map.length=xSize
        if (x == 0) {
            //odd number of line (last not shifted)
            if ((map.length % 2) == 1) {
                //offset from left
                if (y == ((map.length) / 2)) {
                    neighbors.add(map[x][y + 1]);
                    neighbors.add(map[x + 1][y]);
                }
                //offset from right is zero
                else if (y == (map[0].length - 1)) {
                    neighbors.add(map[x + 1][y]);
                    neighbors.add(map[x + 1][y - 1]);
                    neighbors.add(map[x][y - 1]);
                } else {
                    neighbors.add(map[x][y + 1]);
                    neighbors.add(map[x + 1][y]);
                    neighbors.add(map[x + 1][y - 1]);
                    neighbors.add(map[x][y - 1]);
                }
            }
            //even number of line (last shifted)
            else {
                //left offset is max (xSize/2)
                if (y == ((map.length) / 2)) {
                    neighbors.add(map[x][y + 1]);
                    neighbors.add(map[x + 1][y - 1]);
                }
                //right offset is zero
                else if (y == (map[0].length - 1)) {
                    neighbors.add(map[x][y - 1]);
                    neighbors.add(map[x + 1][y - 2]);
                    neighbors.add(map[x + 1][y - 1]);
                } else {
                    neighbors.add(map[x][y + 1]);
                    neighbors.add(map[x + 1][y - 1]);
                    neighbors.add(map[x + 1][y - 2]);
                    neighbors.add(map[x][y - 1]);
                }
            }
        }
        //last line
        //max index is map.length-1
        else if (x == (map.length - 1)) {
            //odd number of line (last not shifted)
            if ((map.length % 2) == 1) {
                //left offset is zero
                if (y == 0) {
                    neighbors.add(map[x - 1][y + 1]);
                    neighbors.add(map[x][y + 1]);
                }
                //right offset is max (xSize/2)
                else if (y == (map[x].length - map.length / 2)) {
                    neighbors.add(map[x][y - 1]);
                    neighbors.add(map[x - 1][y + 1]);
                    neighbors.add(map[x - 1][y]);
                } else {
                    neighbors.add(map[x][y - 1]);
                    neighbors.add(map[x - 1][y]);
                    neighbors.add(map[x - 1][y + 1]);
                    neighbors.add(map[x][y + 1]);
                }
            }
            //even number of line (last shifted)
            else {
                //left offset is zero
                if (y == 0) {
                    neighbors.add(map[x - 1][y + 1]);
                    neighbors.add(map[x - 1][y + 2]);
                    neighbors.add(map[x][y + 1]);
                }
                //right offset is max (xSize/2)
                else if (y == (map[x].length - map.length / 2)) {
                    neighbors.add(map[x][y - 1]);
                    neighbors.add(map[x - 1][y + 1]);
                } else {
                    neighbors.add(map[x][y - 1]);
                    neighbors.add(map[x - 1][y + 1]);
                    neighbors.add(map[x - 1][y + 2]);
                    neighbors.add(map[x][y + 1]);
                }
            }
        }
        //not last or first line
        else {
            //odd number of line (last not shifted)
            if ((map.length % 2) == 1) {
                //left edge
                if (y == (map.length - x) / 2) {
                    if (x % 2 == 1) {
                        neighbors.add(map[x - 1][y]);
                        neighbors.add(map[x - 1][y + 1]);
                        neighbors.add(map[x][y + 1]);
                        neighbors.add(map[x + 1][y]);
                        neighbors.add(map[x + 1][y - 1]);
                    } else {
                        neighbors.add(map[x - 1][y + 1]);
                        neighbors.add(map[x][y + 1]);
                        neighbors.add(map[x + 1][y]);
                    }
                } else if (y == (map[x].length - ((map.length - x) / 2))) {
                    if (x % 2 == 1) {
                        neighbors.add(map[x - 1][y]);
                        neighbors.add(map[x][y - 1]);
                        neighbors.add(map[x + 1][y - 1]);
                    } else {
                        neighbors.add(map[x - 1][y + 1]);
                        neighbors.add(map[x - 1][y]);
                        neighbors.add(map[x][y - 1]);
                        neighbors.add(map[x + 1][y - 1]);
                        neighbors.add(map[x + 1][y]);
                    }
                }
                //not necessary to divide odd and even line (same shift ever)
                else {
                    neighbors.add(map[x - 1][y]);
                    neighbors.add(map[x - 1][y + 1]);
                    neighbors.add(map[x][y + 1]);
                    neighbors.add(map[x][y - 1]);
                    neighbors.add(map[x + 1][y - 1]);
                    neighbors.add(map[x + 1][y]);

                }
            }
            //even number of line (last shifted)
            else {
                if (y == (map.length - x) / 2) {
                    if (x % 2 == 1) {
                        neighbors.add(map[x - 1][y + 1]);
                        neighbors.add(map[x - 1][y + 2]);
                        neighbors.add(map[x][y + 1]);
                        neighbors.add(map[x + 1][y]);
                        neighbors.add(map[x + 1][y + 1]);
                    } else {
                        neighbors.add(map[x - 1][y]);
                        neighbors.add(map[x][y + 1]);
                        neighbors.add(map[x + 1][y - 1]);
                    }
                } else if (y == (map[x].length - ((map.length - x) / 2))) {
                    if (x % 2 == 1) {
                        neighbors.add(map[x - 1][y + 1]);
                        neighbors.add(map[x][y - 1]);
                        neighbors.add(map[x + 1][y]);
                    } else {
                        neighbors.add(map[x - 1][y]);
                        neighbors.add(map[x - 1][y - 1]);
                        neighbors.add(map[x][y - 1]);
                        neighbors.add(map[x + 1][y - 1]);
                        neighbors.add(map[x + 1][y - 2]);
                    }
                } else {
                    if (x % 2 == 1) {
                        neighbors.add(map[x - 1][y + 1]);
                        neighbors.add(map[x - 1][y + 2]);
                        neighbors.add(map[x][y - 1]);
                        neighbors.add(map[x][y + 1]);
                        neighbors.add(map[x + 1][y]);
                        neighbors.add(map[x + 1][y + 1]);
                    } else {
                        neighbors.add(map[x - 1][y]);
                        neighbors.add(map[x - 1][y + 1]);
                        neighbors.add(map[x][y - 1]);
                        neighbors.add(map[x][y + 1]);
                        neighbors.add(map[x - 1][y - 1]);
                        neighbors.add(map[x - 1][y - 2]);
                    }
                }
            }

        }

        neighbors.trimToSize();
        return neighbors;
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