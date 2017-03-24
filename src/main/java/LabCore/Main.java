package LabCore;

import javax.swing.*;
import java.awt.*;

/**
 * Created by alecxanrys
 */
public class Main implements Runnable{

    static PathFinder pathFinder;
    static LOSChecker losChecker;
    private static int xSize, ySize;

    /**
     * @param x
     *         x index
     * @param y
     *         y index
     * @return boolean out offset or not
     */
    private boolean OffsetOut(int x,int y){
        return ((xSize-x-1)/2)<=y && y<=(ySize-1+(xSize-x-1)/2);
    }

    public static void main(String args[]){
        SwingUtilities.invokeLater(new Main());
    }

    /**
     * even and odd problem
     * even is main problem, for odd count of line work fine
     *
     * @see PathFinder
     */
    @Override
    public void run(){

        xSize=6;
        ySize=7;
        int[][] a={{-1,0},{-1,1},{0,-1},{0,1},{1,0},{1,1}};

        Field field=new Field.Builder(xSize,ySize).Map(4).Shifts(a).build();


        JFrame jf=new JFrame("Lab1");
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        int lineSize=80;
        jf.setSize(ySize*lineSize+lineSize/2,xSize*lineSize+180);
        jf.setVisible(true);
        jf.setResizable(false);

        // создаем  панель.
        JPanel p=new JPanel();

        jf.add(p);

        p.setLayout(null);

        for (int x=(xSize-1); x>=0; x--) {
            for (int y=((ySize+xSize/2)-1); y>=0; y--) {
                if (OffsetOut(x,y)) {
                    CellAction action=new CellAction();
                    JButton cell=new JButton(x+":"+y);
                    cell.addActionListener(action);
                    cell.setActionCommand(x+":"+y);
                    p.add(cell);
                    switch (field.map[x][y].ground) {
                        case 0: {
                            cell.setBackground(Color.GREEN);
                            break;
                        }
                        case 1: {
                            cell.setBackground(Color.YELLOW);
                            break;
                        }
                        case 2: {
                            cell.setBackground(Color.RED);
                            break;
                        }
                        case 3: {
                            cell.setBackground(Color.GRAY);
                            break;
                        }
                        case 4: {
                            cell.setBackground(Color.BLACK);
                            break;
                        }
                        default: {
                            cell.setBackground(Color.PINK);
                            break;
                        }
                    }
                    if (xSize%2==1) {
                        cell.setBounds(lineSize*(y-(xSize-1-x)/2)+(lineSize/2)*(Math.abs(x%2-1)),lineSize*x,lineSize,lineSize);
                    }
                    else {
                        cell.setBounds(lineSize*(y-(xSize-1-x)/2)+(lineSize/2)*(x%2),lineSize*x,lineSize,lineSize);
                    }
                }

            }
        }
        JTextArea text=new JTextArea("Здесь производится логгирование действии с картой");
        text.setEditable(false);

        JScrollPane scrollPane=new JScrollPane(text);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        p.add(scrollPane);
        scrollPane.setBounds(0,xSize*lineSize,ySize*lineSize+lineSize/2,150);

        pathFinder=PathFinder.getSingle(field,text);
        losChecker=LOSChecker.getSingle(field,text);

    }
}
