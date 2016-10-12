import javax.swing.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;


/**
 * Created by Пользователь on 22.09.2016.
 */
class LOSChecker {
    private JTextArea text;
    private Cell map[][];


    /**
     * @param current on of the cell on map
     * @return neighbors of current cell
     * @see PathFinder
     */
    private ArrayList<Cell> Neighbors(Cell current) {
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
                    try {
                        neighbors.add(map[x - 1][y]);
                        //TODO here we have a bug "out of border"
                        neighbors.add(map[x - 1][y + 1]);
                        neighbors.add(map[x][y + 1]);
                        neighbors.add(map[x][y - 1]);
                        neighbors.add(map[x + 1][y - 1]);
                        neighbors.add(map[x + 1][y]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        text.append("Catch error "+current.toString());
                    }
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

    private int Distance(int deltaX, int deltaY) {
        int d = (Math.abs(deltaX) + Math.abs(deltaY) + Math.abs(deltaX + deltaY)) / 2;
        text.append("\nДистанция смещения " + deltaX + ":" + deltaY + "=" + d);
        return d;
    }


    LOSChecker(Cell map[][], JTextArea text) {
        this.map = map;
        this.text = text;
    }

    ArrayList<Cell> LOS(int x1, int y1, int x2, int y2) {
        Cell pointA = map[x1][y1];
        Cell pointB = map[x2][y2];

        LinkedList<Cell> frontier = new LinkedList<>();

        frontier.offer(pointA);

        Hashtable<Cell, Cell> cameFrom = new Hashtable<>();

        cameFrom.put(pointA, new Cell(-1, -1, -1));

        Cell current;

        while (!frontier.isEmpty()) {
            current = frontier.poll();

            ArrayList<Cell> neighbors = Neighbors(current);

            for (Cell next : neighbors) {
                //ignore cover if cell is target
                if ((next.ground == -1 || next.ground == 3 || next.ground == 4) && !next.equals(pointB)) continue;
                if (!cameFrom.containsKey(next)) {
                    frontier.offer(next);
                    cameFrom.put(next, current);
                }
            }
        }

        current = pointB;
        ArrayList<Cell> path = new ArrayList<>();
        path.add(current);

        //check when a or b point rounded by unreached and ruin.
        if (cameFrom.get(current)==null)
        {
            text.append("\nLine of View don't exist");
            return null;
        }

        while (!current.equals(pointA)) {
            current = cameFrom.get(current);
            path.add(current);
        }

        if (path.size() - 1 == Distance(pointA.x - pointB.x, pointA.y - pointB.y)) {
            text.append("\nLine of View exist");
            return path;
        } else {
            text.append("\nLine of View don't exist");
            return null;
        }

    }
}
