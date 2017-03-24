package LabCore;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by alecxanrys
 */
class CellAction extends AbstractAction{

    private static Cell chosen;

    CellAction(){
        super();
    }

    @Override
    public void actionPerformed(ActionEvent e){
        String index[]=e.getActionCommand().split(":");
        int i=Integer.parseInt(index[0]);
        int j=Integer.parseInt(index[1]);

        if (chosen==null) {
            chosen=new Cell(i,j,-1);
        }
        else {
            Main.pathFinder.FindPath(chosen.x,chosen.y,i,j);
            Main.losChecker.LOS(chosen.x,chosen.y,i,j);
            chosen=null;
        }
    }
}
