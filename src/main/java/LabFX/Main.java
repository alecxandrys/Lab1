package main.java.LabFX;


import main.java.LabCore.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.*;

/**
 * Created by alecxanrys
 */

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private TextArea log;
    private int chosenX=-1,chosenY=-1;

    private PathFinder pathFinder;
    private LOSChecker losChecker;

    private int xSize, ySize;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Lab #5-JavaFX. main scene");

        xSize = 6;
        ySize = 7;

        int lineSize = 80;

        Field field = new Field(xSize, ySize);

        Pane pane = new Pane();

        for (int x = (xSize - 1); x >= 0; x--) {
            for (int y = ((ySize + xSize / 2) - 1); y >= 0; y--) {
                if (OffsetOut(x, y)) {
                    Button button = new Button(x + ":" + y);

                    button.setOnAction(event -> {
                        Button butt = (Button) event.getSource();
                        String str = butt.getText();
                        log.appendText("\nВыбрана клетка: " + str);

                        String index[] = str.split(":");

                        int i = Integer.parseInt(index[0]);
                        int j = Integer.parseInt(index[1]);

                        if (chosenX == -1) {
                            chosenX=i;
                            chosenY=j;
                        } else {
                            pathFinder.FindPath(chosenX,chosenY, i, j);
                            losChecker.LOS(chosenX,chosenY, i, j);
                            chosenX = -1;
                            chosenY=-1;
                        }
                    });

                    button.setPrefSize(lineSize, lineSize);

                    switch (field.map[x][y].ground) {
                        case 0: {
                            button.setBackground((new Background(new BackgroundFill(Color.GREEN, new CornerRadii(0), new Insets(0)))));
                            break;
                        }
                        case 1: {
                            button.setBackground((new Background(new BackgroundFill(Color.YELLOW, new CornerRadii(0), new Insets(0)))));
                            break;
                        }
                        case 2: {
                            button.setBackground((new Background(new BackgroundFill(Color.RED, new CornerRadii(0), new Insets(0)))));
                            break;
                        }
                        case 3: {
                            button.setBackground((new Background(new BackgroundFill(Color.GRAY, new CornerRadii(0), new Insets(0)))));
                            break;
                        }
                        case 4: {
                            button.setBackground((new Background(new BackgroundFill(Color.BLACK, new CornerRadii(0), new Insets(0)))));
                            break;
                        }
                        default: {
                            button.setBackground((new Background(new BackgroundFill(Color.PINK, new CornerRadii(0), new Insets(0)))));
                            break;
                        }
                    }
                    if (xSize % 2 == 1) {
                        button.setLayoutX(lineSize * (y - (xSize - 1 - x) / 2) + (lineSize / 2) * (Math.abs(x % 2 - 1)));
                        button.setLayoutY(lineSize * x);
                    } else {
                        button.setLayoutX(lineSize * (y - (xSize - 1 - x) / 2) + (lineSize / 2) * (x % 2));
                        button.setLayoutY(lineSize * x);
                    }

                    pane.getChildren().add(button);
                }
            }
        }

        log = new TextArea("Здесь выводится лог действии");
        log.setEditable(false);

        BorderPane root = new BorderPane();
        root.setBottom(log);
        root.setTop(pane);

        Scene fieldScene = new Scene(root, ySize * lineSize + lineSize / 2, xSize * lineSize + 180);

        MyTextArea text=new MyTextArea();
        pathFinder = new PathFinder(field, text);
        losChecker = new LOSChecker(field, text);

        primaryStage.setScene(fieldScene);
        primaryStage.show();
    }

    private boolean OffsetOut(int x, int y) {
        return ((xSize - x - 1) / 2) <= y && y <= (ySize - 1 + (xSize - x - 1) / 2);
    }

    private class MyTextArea extends JTextArea
    {
        @Override
        public void append(String str) {
            log.appendText(str);
        }
    }
}
