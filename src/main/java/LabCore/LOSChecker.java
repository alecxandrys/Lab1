package LabCore;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;

/**
 * Created by alecxanrys
 */
public class LOSChecker{
    private JTextArea text;
    private Field field;

    private int Distance(int deltaX,int deltaY){
        int d=(Math.abs(deltaX)+Math.abs(deltaY)+Math.abs(deltaX+deltaY))/2;
        text.append("\nДистанция смещения "+deltaX+":"+deltaY+"="+d);
        return d;
    }


    public LOSChecker(Field field,JTextArea text){
        this.field=field;
        this.text=text;
    }

    public void LOS(int x1,int y1,int x2,int y2){
        Cell pointA=field.map[x1][y1];
        Cell pointB=field.map[x2][y2];

        LinkedList<Cell> frontier=new LinkedList<>();

        frontier.offer(pointA);

        Hashtable<Cell,Cell> cameFrom=new Hashtable<>();

        cameFrom.put(pointA,new Cell(-1,-1,-1));

        Cell current;

        while (!frontier.isEmpty()) {
            current=frontier.poll();

            ArrayList<Cell> neighbors=field.Neighbors(current);

            for (Cell next : neighbors) {
                //ignore cover if cell is target
                if ((next.ground==-1 || next.ground==3 || next.ground==4) && !next.equals(pointB)) continue;
                if (!cameFrom.containsKey(next)) {
                    frontier.offer(next);
                    cameFrom.put(next,current);
                }
            }
        }

        current=pointB;
        ArrayList<Cell> path=new ArrayList<>();
        path.add(current);

        //check when a or b point rounded by unreached and ruin.
        if (cameFrom.get(current)==null) {
            text.append("\nLine of View don't exist");
            return;
        }

        while (!current.equals(pointA)) {
            current=cameFrom.get(current);
            path.add(current);
        }

        if (path.size()-1==Distance(pointA.x-pointB.x,pointA.y-pointB.y)) {
            text.append("\nLine of View exist");
        }
        else {
            text.append("\nLine of View don't exist");
        }

    }
}
