/**
 * Created by Пользователь on 22.09.2016.
 */
class PathFinder {

    Cell pointA;
    Cell pointB;
    byte map[][];

    private class Graph
    {

    }

    private int Heuristic(Cell a,Cell b)
    {

        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

    PathFinder(Cell a,Cell b,byte map[][])
    {

    }

    public void updateMap(byte map[][])
    {
        this.map=map;
    }

}
