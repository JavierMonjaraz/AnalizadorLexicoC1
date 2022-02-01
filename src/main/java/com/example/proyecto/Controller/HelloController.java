package com.example.proyecto.Controller;

import com.example.proyecto.Model.Simbolo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("XD");
    }

    void CargarGramatica(){
        Simbolo simboloInicial = new Simbolo("Query",true,true);
        Simbolo simboloNoTerminal1 = new Simbolo("CDB",false,false);
        Simbolo simboloNoTerminal2 = new Simbolo("C",false,false);
        Simbolo simboloNoTerminal3 = new Simbolo("BD",false,false);
        Simbolo simboloNoTerminal4 = new Simbolo("ID",false,false);
        Simbolo simboloNoTerminal5 = new Simbolo("RESTOID",false,false);
        Simbolo simboloNoTerminal6 = new Simbolo("L",false,false);
        Simbolo simboloNoTerminal7 = new Simbolo("CTB",false,false);
        Simbolo simboloNoTerminal8 = new Simbolo("RCTB",false,false);
        Simbolo simboloNoTerminal9 = new Simbolo("TB",false,false);
        Simbolo simboloNoTerminal10 = new Simbolo("ATRIBUTOS",false,false);
        Simbolo simboloNoTerminal11 = new Simbolo("RATRIBUTOS",false,false);
        Simbolo simboloNoTerminal12 = new Simbolo("ATRIBUTO",false,false);
        Simbolo simboloNoTerminal13 = new Simbolo("AUTOI",false,false);
        Simbolo simboloNoTerminal14 = new Simbolo("NULL",false,false);
        Simbolo simboloNoTerminal15 = new Simbolo("TIPODATO",false,false);
        Simbolo simboloNoTerminal16 = new Simbolo("NUMERICO",false,false);
        Simbolo simboloNoTerminal17 = new Simbolo("STRING",false,false);
        Simbolo simboloNoTerminal18 = new Simbolo("FECHA",false,false);
    }
}