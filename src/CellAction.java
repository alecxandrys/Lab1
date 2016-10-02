import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Пользователь on 02.10.2016.
 */
class CellAction extends AbstractAction {
    CellAction()
    {
        super();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String index[]=e.getActionCommand().split(":");
        int i=Integer.parseInt(index[0]);
        int j=Integer.parseInt(index[1]);

        if (Main.chosen.empty)
        {
            Main.chosen.empty=false;
            Main.chosen.x=i;
            Main.chosen.y=j;
        }
        else
        {

        }
    }
}
