package com.example.proyecto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {
    private static Scene scene;
    private static Stage primaryStage;
    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage=stage;
        try {
            scene = new Scene(loadFXML("MainView"));
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.getIcons().add(new Image("assets/mysqllogo.jpg"));
            primaryStage.setTitle("Analizador Léxico");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void setFXML(String fxml, String title) throws IOException{
        scene.setRoot(loadFXML(fxml));
        primaryStage.sizeToScene();
        primaryStage.centerOnScreen();
        primaryStage.setTitle(title);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(Main.class.getResource("Views/"+fxml+".fxml"));
        return fxmlloader.load();
    }


    public static void main(String[] args) {
        launch();
    }
}