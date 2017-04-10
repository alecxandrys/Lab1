package LabFX;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by alecxanrys
 */
public class Starter extends Application{
    private int chosenIndex=0;
    private ResultSet maps;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Lab #5-JavaFX. Chosen dialog");
        int sessionID=(int) Math.floor(Math.random()*1000);//generate ID

        Connection connection;
        Statement statement=null;
        //URL к базе состоит из протокола:подпротокола://[хоста]:[порта_СУБД]/[БД] и других_сведений
        String url="jdbc:mysql://localhost:3306/MySQL";
        //Имя пользователя БД
        String name="root";
        //Пароль
        String password="MySQL";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection=DriverManager.getConnection(url,name,password);
            statement=connection.createStatement();
            String sql="INSERT INTO sys.sessia set ID="+sessionID;
            statement.executeUpdate(sql);
            maps=statement.executeQuery("SELECT * FROM sys.pre_generated_map");
        } catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }

        ArrayList<String> variant=new ArrayList<>();
        variant.add("Random");
        while (maps!=null && maps.next()) {
            variant.add(maps.getString("Name"));
        }

        Button start=new Button();

        Statement finalStatement=statement;
        start.setOnAction(event -> {
            Main next=new Main();
            if (chosenIndex==0){maps=null;}
            next.setCondition(finalStatement,maps,sessionID);
            next.start(primaryStage);
        });

        ChoiceBox<String> cb=new ChoiceBox<>(FXCollections.observableArrayList(variant));
        cb.getSelectionModel().selectedIndexProperty().addListener((observable,oldValue,newValue) -> {
            chosenIndex=(int) newValue;
            if (chosenIndex==0) {
                start.setText("Start on random map");
            }
            else {
                try {
                    maps.absolute(chosenIndex);
                    start.setText("Start on "+maps.getString("Name"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });


        BorderPane root=new BorderPane();
        root.setTop(cb);
        root.setBottom(start);
        Scene fieldScene=new Scene(root,300,100);
        primaryStage.setScene(fieldScene);
        primaryStage.show();
    }
}
