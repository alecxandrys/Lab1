import javax.swing.*;
import java.util.*;

/**
 * Created by Пользователь on 22.09.2016.
 */
class PathFinder {

    private Cell map[][];
    private JTextArea text;

    /**
     * We have a problem with odd and even number fo line/
     * @see Main
     * @see Field
     * @param current around this find neighbors
     * @return ArrayList of Neighbors
     */
    private ArrayList<Cell> Neighbors (Cell current)
    {
        ArrayList<Cell> neighbors= new ArrayList<>(6);
        int x=current.x;
        int y=current.y;

        //first line
        //map.length=xSize
        if (x==0)
        {
            //odd number of line (last not shifted)
            if ((map.length%2)==1)
            {
                //offset from left
                if (y == ((map.length) / 2)) {
                    neighbors.add(map[x][y + 1]);
                    neighbors.add(map[x + 1][y]);
                }
                //offset from right is zero
                else if (y == (map[0].length-1)) {
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
            else
            {
                //left offset is max (xSize/2)
                if (y == ((map.length) / 2)) {
                    neighbors.add(map[x][y+1]);
                    neighbors.add(map[x+1][y-1]);
                }
                //right offset is zero
                else if (y == (map[0].length-1)) {
                    neighbors.add(map[x][y-1]);
                    neighbors.add(map[x+1][y-2]);
                    neighbors.add(map[x+1][y-1]);
                } else {
                    neighbors.add(map[x][y+1]);
                    neighbors.add(map[x+1][y-1]);
                    neighbors.add(map[x+1][y-2]);
                    neighbors.add(map[x][y-1]);
                }
            }
        }
        //last line
        //max index is map.length-1
        else if (x==(map.length-1))
        {
            //odd number of line (last not shifted)
            if ((map.length%2)==1)
            {
                //left offset is zero
                if (y == 0)
                {
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
            else
            {
                //left offset is zero
                if (y == 0)
                {
                    neighbors.add(map[x - 1][y + 1]);
                    neighbors.add(map[x-1][y + 2]);
                    neighbors.add(map[x][y + 1]);
                }
                //right offset is max (xSize/2)
                else if (y == (map[x].length - map.length / 2))
                {
                    neighbors.add(map[x][y - 1]);
                    neighbors.add(map[x - 1][y + 1]);
                } else {
                    neighbors.add(map[x][y - 1]);
                    neighbors.add(map[x - 1][y+1]);
                    neighbors.add(map[x - 1][y +2]);
                    neighbors.add(map[x][y + 1]);
                }
            }
        }
        //not last or first line
        else
        {
            //odd number of line (last not shifted)
            if ((map.length%2)==1)
            {
                //left edge
                if (y==(map.length-x)/2)
                {
                    if (x%2==1)
                    {
                        neighbors.add(map[x-1][y]);
                        neighbors.add(map[x-1][y+1]);
                        neighbors.add(map[x][y+1]);
                        neighbors.add(map[x+1][y]);
                        neighbors.add(map[x+1][y-1]);
                    }
                    else
                    {
                        neighbors.add(map[x-1][y+1]);
                        neighbors.add(map[x][y+1]);
                        neighbors.add(map[x+1][y]);
                    }
                }
                else if (y==(map[x].length-((map.length-x)/2)))
                {
                    if (x%2==1)
                    {
                        neighbors.add(map[x-1][y]);
                        neighbors.add(map[x][y-1]);
                        neighbors.add(map[x+1][y-1]);
                    }
                    else
                    {
                        neighbors.add(map[x-1][y+1]);
                        neighbors.add(map[x-1][y]);
                        neighbors.add(map[x][y-1]);
                        neighbors.add(map[x+1][y-1]);
                        neighbors.add(map[x+1][y]);
                    }
                }
                //not necessary to divine odd and even line (same shift ever)
                else
                {
                        neighbors.add(map[x-1][y]);
                        neighbors.add(map[x-1][y+1]);
                        neighbors.add(map[x][y+1]);
                        neighbors.add(map[x][y-1]);
                        neighbors.add(map[x+1][y-1]);
                        neighbors.add(map[x+1][y]);

                }
            }
            //even number of line (last shifted)
            else
            {
                if (y==(map.length-x)/2)
                {
                    if (x%2==1)
                    {
                        neighbors.add(map[x-1][y+1]);
                        neighbors.add(map[x-1][y+2]);
                        neighbors.add(map[x][y+1]);
                        neighbors.add(map[x+1][y]);
                        neighbors.add(map[x+1][y+1]);
                    }
                    else
                    {
                        neighbors.add(map[x-1][y]);
                        neighbors.add(map[x][y+1]);
                        neighbors.add(map[x+1][y-1]);
                    }
                }
                else if (y==(map[x].length-((map.length-x)/2)))
                {
                    if (x%2==1)
                    {
                        neighbors.add(map[x-1][y+1]);
                        neighbors.add(map[x][y-1]);
                        neighbors.add(map[x+1][y]);
                    }
                    else
                    {
                        neighbors.add(map[x-1][y]);
                        neighbors.add(map[x-1][y-1]);
                        neighbors.add(map[x][y-1]);
                        neighbors.add(map[x+1][y-1]);
                        neighbors.add(map[x+1][y-2]);
                    }
                }
                else
                {
                    if (x%2==1)
                    {
                        neighbors.add(map[x-1][y+1]);
                        neighbors.add(map[x-1][y+2]);
                        neighbors.add(map[x][y-1]);
                        neighbors.add(map[x][y+1]);
                        neighbors.add(map[x+1][y]);
                        neighbors.add(map[x+1][y+1]);
                    }
                    else
                    {
                        neighbors.add(map[x-1][y]);
                        neighbors.add(map[x-1][y+1]);
                        neighbors.add(map[x][y-1]);
                        neighbors.add(map[x][y+1]);
                        neighbors.add(map[x-1][y-1]);
                        neighbors.add(map[x-1][y-2]);
                    }
                }
            }

        }

        neighbors.trimToSize();
        return neighbors;
    }

    private int Cost (Cell current,Cell next)
    {
        switch (next.ground){
            case 0:{return 1;}//standard
            case 1:{return 2;}//difficult
            case 2:{return 5;}//dangerous
            case 3:{return 4;}//ruin
            default:{text.append("\nUnreached point "+next.toString());return 1000;}
        }
    }

    private int Heuristic(Cell a,Cell b)
    {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

    PathFinder(Cell map[][],JTextArea text)
    {
        this.text=text;
        this.map=map;
    }

    /** A* realisation. Visit
     * http://www.redblobgames.com/pathfinding/a-star/introduction.html
     * @param x1 x start
     * @param y1 y start
     * @param x2 x finish
     * @param y2 y finish
     */
    ArrayList<Cell> FindPath(int x1, int y1, int x2, int y2)
    {
        if (map[x1][y1].ground==4 || map[x2][y2].ground==-1)
        {
            text.append("\nПервая точка маршрута непроходима или неверна");
            return null;
        }
        else if (map[x2][y2].ground==4 || map[x1][y1].ground==-1)
        {
            text.append("\nПоследняя точка маршрута непроходима или неверна");
            return null;
        }
        else {

            Cell pointA = map[x1][y1];
            Cell pointB = map[x2][y2];

            //recount the size of queue, don't need offset point
            PriorityQueue<Cell> frontier=new PriorityQueue<>(map.length * map[0].length, (o1, o2) -> {
                if (o1.cost<o2.cost)
                {
                    return -1;
                }
                else if (o1.cost>o2.cost)
                {
                    return 1;
                }
                else return 0;
            });

            Hashtable<Cell,Cell> cameFrom=new Hashtable<>();
            Hashtable<Cell,Integer> costSoFar=new Hashtable<>();

            cameFrom.put(pointA,new Cell(-1,-1,-1));
            costSoFar.put(pointA,0);
            pointA.cost=0;
            frontier.offer(pointA);

            Cell current;

            while(!frontier.isEmpty())
            {
                current=frontier.poll();

                if (current.equals(pointB))
                {
                    text.append("\nPath was found success");
                    break;
                }
                ArrayList<Cell> neighbors=Neighbors(current);

                for(Cell next:neighbors)
                {
                    int newCost=costSoFar.get(current)+Cost(current,next);

                    //!ALERT, costSoFar can haven't next
                    if(!costSoFar.containsKey(next) || newCost<costSoFar.get(next))
                    {
                        costSoFar.put(next,newCost);
                        next.cost=newCost+Heuristic(pointB,next);
                        frontier.offer(next);
                        cameFrom.put(next,current);
                    }
                }

            }

            //write a path
            current=pointB;
            ArrayList<Cell> path=new ArrayList<>();
            path.add(current);
            while (!current.equals(pointA))
            {
                current=cameFrom.get(current);
                path.add(current);
            }

            for (Cell aPath : path) {
                text.append("\nStep " + aPath.toString());
            }

            return path;
        }

    }

    public void UpdateMap(Cell map[][])
    {
        this.map=map;
    }

}
