import javax.swing.*;
import java.util.*;

/**
 * Created by Пользователь on 22.09.2016.
 */
class PathFinder {

    private Field field;
    private JTextArea text;

    private int Cost(Cell current, Cell next) {
        switch (next.ground) {
            case 0: {
                return 1;
            }//standard
            case 1: {
                return 2;
            }//difficult
            case 2: {
                return 5;
            }//dangerous
            case 3: {
                return 4;
            }//ruin
            default: {
                return 1000;
            }
        }
    }

    private int Heuristic(Cell a, Cell b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

    PathFinder(Field field, JTextArea text) {
        this.text = text;
        this.field = field;
    }

    /**
     * A* realisation. Visit
     * http://www.redblobgames.com/pathfinding/a-star/introduction.html
     *
     * @param x1 x start
     * @param y1 y start
     * @param x2 x finish
     * @param y2 y finish
     */
    ArrayList<Cell> FindPath(int x1, int y1, int x2, int y2) {
        if (field.map[x1][y1].ground == 4 || field.map[x2][y2].ground == -1) {
            text.append("\nПервая точка маршрута непроходима или неверна");
            return null;
        } else if (field.map[x2][y2].ground == 4 || field.map[x1][y1].ground == -1) {
            text.append("\nПоследняя точка маршрута непроходима или неверна");
            return null;
        } else {

            Cell pointA = field.map[x1][y1];
            Cell pointB = field.map[x2][y2];

            //recount the size of queue, don't need offset point
            PriorityQueue<Cell> frontier = new PriorityQueue<>(field.map.length * field.map[0].length, (o1, o2) -> {
                if (o1.cost < o2.cost) {
                    return -1;
                } else if (o1.cost > o2.cost) {
                    return 1;
                } else return 0;
            });

            Hashtable<Cell, Cell> cameFrom = new Hashtable<>();
            Hashtable<Cell, Integer> costSoFar = new Hashtable<>();

            cameFrom.put(pointA, new Cell(-1, -1, -1));
            costSoFar.put(pointA, 0);
            pointA.cost = 0;
            frontier.offer(pointA);

            Cell current;

            while (!frontier.isEmpty()) {
                current = frontier.poll();

                if (current.equals(pointB)) {
                    text.append("\nPath was found success");
                    break;
                }
                ArrayList<Cell> neighbors = field.Neighbors(current);

                for (Cell next : neighbors) {
                    int newCost = costSoFar.get(current) + Cost(current, next);

                    //!ALERT, costSoFar can haven't next
                    if (!costSoFar.containsKey(next) || newCost < costSoFar.get(next)) {
                        costSoFar.put(next, newCost);
                        next.cost = newCost + Heuristic(pointB, next);
                        frontier.offer(next);
                        cameFrom.put(next, current);
                    }
                }

            }

            //write a path
            current = pointB;
            ArrayList<Cell> path = new ArrayList<>();
            path.add(current);
            while (!current.equals(pointA)) {
                current = cameFrom.get(current);
                path.add(current);
            }

            for (Cell aPath : path) {
                text.append("\nStep " + aPath.toString());
            }

            return path;
        }

    }
}
