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
    public static Stage secondStage;
    private static FXMLLoader fxmlloader;
    private static Scene secondScene;
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

    public static void newStage(String fxml, String title) {
        try {
            Parent node = loadFXML(fxml);
            secondStage= new Stage();
            secondScene = new Scene(node);
            secondStage.setScene(secondScene);
            secondStage.setTitle(title);
            secondStage.initOwner(primaryStage);
            secondStage.initModality(Modality.WINDOW_MODAL);
            secondStage.initStyle(StageStyle.UNDECORATED);
            secondStage.centerOnScreen();
            secondStage.showAndWait();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public static FXMLLoader getFxmlloader() {
        return fxmlloader;
    }

    public static void main(String[] args) {
        launch();
    }
}