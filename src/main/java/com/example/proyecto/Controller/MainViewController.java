package com.example.proyecto.Controller;

import com.example.proyecto.Model.Token;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.ArrayList;


public class MainViewController {

    ArrayList<String> PC = new ArrayList<>();
    ArrayList<String> TD = new ArrayList<>();
    ArrayList<String> N = new ArrayList<>();
    ArrayList<String> S = new ArrayList<>();
    ArrayList<String> L = new ArrayList<>();

    Token lista1;
    Token lista2;
    Token lista3;
    Token lista4;
    Token lista5;

    @FXML
    private TextArea TA_consultas;

    @FXML
    private Button execute_btn;

    @FXML
    public void initialize() {
        setTokens();
    }

    @FXML
    public void onExecute(ActionEvent actionEvent) {
        busqueda();
    }

    public void busqueda(){
        String input = TA_consultas.getText();
        if (lista1.isin(input)==false){
            if (lista2.isin(input)==false){
                if (lista3.isin(input)==false){
                    if (lista4.isin(input)==false){
                        if (lista5.isin(input)==false){
                            System.out.println("ÉL simbolo no se encuentra en ningun Token");
                        }
                    }
                }
            }
        }

    }


    public void setTokens() {

        PC.add("create");
        PC.add("database");
        PC.add("table");
        PC.add("null");
        PC.add("not null");
        PC.add("primary");
        PC.add("key");
        PC.add("auto_increment");

        TD.add("decimal");
        TD.add("double");
        TD.add("date");
        TD.add("datetime");
        TD.add("float");
        TD.add("real");
        TD.add("int");
        TD.add("tinyint");
        TD.add("smallint");
        TD.add("mediumint");
        TD.add("char");
        TD.add("nchar");
        TD.add("varchar");
        TD.add("text");
        TD.add("tinytext");
        TD.add("mediumtext");
        TD.add("longtext");
        TD.add("year");
        TD.add("time");
        TD.add("timestamp");

        N.add("0");
        N.add("1");
        N.add("2");
        N.add("3");
        N.add("4");
        N.add("5");
        N.add("6");
        N.add("7");
        N.add("8");
        N.add("9");

        S.add(",");
        S.add("(");
        S.add(")");
        S.add(";");

        L.add("a");
        L.add("b");
        L.add("c");
        L.add("d");
        L.add("e");
        L.add("f");
        L.add("g");
        L.add("h");
        L.add("i");
        L.add("j");
        L.add("k");
        L.add("l");
        L.add("m");
        L.add("n");
        L.add("r");
        L.add("s");
        L.add("o");
        L.add("p");
        L.add("q");
        L.add("w");
        L.add("x");
        L.add("y");
        L.add("z");
        L.add("A");
        L.add("B");
        L.add("C");
        L.add("D");
        L.add("E");
        L.add("F");
        L.add("G");
        L.add("H");
        L.add("I");
        L.add("J");
        L.add("K");
        L.add("L");
        L.add("M");
        L.add("N");
        L.add("R");
        L.add("S");
        L.add("O");
        L.add("P");
        L.add("Q");
        L.add("W");
        L.add("X");
        L.add("Y");
        L.add("Z");

        lista1 = new Token("Palabras Clave", PC);
        lista2 = new Token("Tipos de Dato", TD);
        lista3 = new Token("Numeros", N);
        lista4 = new Token("Simbolos", S);
        lista5 = new Token("Letras", L);

    }


}
