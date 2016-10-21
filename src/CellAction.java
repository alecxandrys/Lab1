import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Пользователь on 02.10.2016.
 */
class CellAction extends AbstractAction {
    CellAction() {
        super();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String index[] = e.getActionCommand().split(":");
        int i = Integer.parseInt(index[0]);
        int j = Integer.parseInt(index[1]);

        if (Main.chosen == null) {
            Main.chosen = new Cell(i, j,-1);
        } else {
            Main.pathFinder.FindPath(Main.chosen.x, Main.chosen.y, i, j);
            Main.losChecker.LOS(Main.chosen.x, Main.chosen.y, i, j);
            Main.chosen = null;
        }
    }
}
