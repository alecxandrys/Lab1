/**
 * Created by Пользователь on 22.09.2016.
 */
class PathFinder {

    private Cell map[][];

    private class Graph
    {

    }

    private int Heuristic(Cell a,Cell b)
    {

        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

    PathFinder(Cell map[][])
    {
        this.map=map;
    }

    void FindPath(int x1, int y1, int x2, int y2)
    {
        Cell pointA = map[x1][y1];
        Cell pointB = map[x2][y2];

    }

    public void updateMap(Cell map[][])
    {
        this.map=map;
    }

}