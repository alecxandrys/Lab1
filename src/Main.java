import javax.swing.*;
import java.awt.*;

/**
 * Created by Пользователь on 22.09.2016.
 */
public class Main implements Runnable{

    static Chosen chosen=null;
    static PathFinder pathFinder;

    static boolean OffsetOut(int xSize, int ySize, int i, int j)
    {
        return ((xSize - i) / 2) <= j && j <= (ySize-1 + (xSize - i) / 2);
    }

    public static void main(String args[ ])
    {
        SwingUtilities.invokeLater (new Main());
    }

    @Override
    public void run() {
        int xSize = 7;
        int ySize = 7;
        Field field=new Field(xSize, ySize);


        JFrame jf = new JFrame("Lab1");
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        int lineSize = 80;
        jf.setSize(ySize * lineSize + lineSize /2, xSize * lineSize +200);
        jf.setVisible(true);
        jf.setResizable(false);

        // создаем  панель.
        JPanel p = new JPanel();

        jf.add(p);

        p.setLayout(null);

        for (int i = 0; i< xSize; i++)
        {
            for (int j = 0; j<(ySize + xSize /2); j++)
            {
                if(OffsetOut(xSize, ySize,i,j))
                {
                    CellAction action=new CellAction();
                    JButton cell =new JButton(i+":"+j);
                    cell.addActionListener(action);
                    cell.setActionCommand(i+":"+j);
                    p.add(cell);
                    switch (field.map[i][j].ground) {
                        case 0:{cell.setBackground(Color.GREEN);break;}
                        case 1:{cell.setBackground(Color.YELLOW);break;}
                        case 2:{cell.setBackground(Color.RED);break;}
                        case 3:{cell.setBackground(Color.GRAY);break;}
                        case 4:{cell.setBackground(Color.BLACK);break;}
                        default:{cell.setBackground(Color.PINK);break;}
                    }
                    if (i%2==0)
                    {
                        cell.setBounds(lineSize *(j-(xSize -i)/2), lineSize *i, lineSize, lineSize);
                    }
                    else
                    {
                        cell.setBounds(lineSize *(j-(xSize -i)/2)+ lineSize /2, lineSize *i, lineSize, lineSize);
                    }
                }

            }
        }
        JTextArea text=new JTextArea("Здесь производится логгирование действии с картой");
        p.add(text);
        text.setBounds(0, xSize * lineSize, ySize * lineSize + lineSize /2,150);
        text.setEditable(false);
        text.append("\nДистанция смещения (3,5)="+ LOSChecker.Distance(3,5));

        pathFinder=new PathFinder(field.map,text);

    }

}

class Chosen
{
    int x,y;
    Chosen(int x,int y)
    {
        this.x=x;
        this.y=y;
    }
}
