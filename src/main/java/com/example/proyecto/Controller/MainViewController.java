package com.example.proyecto.Controller;

import com.example.proyecto.Model.AnalizadorLexico;
import com.example.proyecto.Model.Token;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class MainViewController {

    @FXML
    private TextArea TA_consultas;

    @FXML
    private Button execute_btn;

    @FXML
    private Label mensaje;

    @FXML
    private Button ver_btn;

    private AnalizadorLexico analizadorLexico;
    @FXML
    public void initialize() {
        analizadorLexico = new AnalizadorLexico();
    }

    @FXML
    void executeOnMouseClicked(MouseEvent event) {
        analizadorLexico.busqueda(TA_consultas,ver_btn);
        analizadorLexico.showMensaje(mensaje,ver_btn);
    }


    @FXML
    void salirOnMouseClicked(MouseEvent event) {
        System.exit(1);
    }

    @FXML
    public void openTokensView() throws IOException {
        try {
            FXMLLoader fxmlLoader2 = new FXMLLoader();
            fxmlLoader2.setLocation(MainViewController.class.getResource("/com/example/proyecto/Views/resumen.fxml"));
            Parent root = fxmlLoader2.load();
            ResumenController controlador = fxmlLoader2.getController();

            controlador.initialize(analizadorLexico.getTokensEncontrados(),analizadorLexico.getSimbolosNoValidos());
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.getIcons().add(new Image("assets/mysqllogo.jpg"));
            stage.setTitle("Detalles");
            stage.setResizable(false);
            stage.showAndWait();
            ver_btn.setVisible(false);
            mensaje.setText("");
        } catch (IOException ex) {
            System.out.println("IO Exception: " + ex.getMessage());
        }
    }
}