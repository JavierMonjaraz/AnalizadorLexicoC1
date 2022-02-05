package com.example.proyecto.Controller;

import com.example.proyecto.Model.Token;
import javafx.scene.control.TextArea;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class MainViewController {

    ArrayList<Token> tokens = new ArrayList<>();
    Token identificador;
    Token longitud;
    Token palabrasReservadas;
    Token tiposDato;


    @FXML
    private TextArea TA_consultas;

    @FXML
    private Button execute_btn;

    @FXML
    public void initialize() {
        setTokens();
    }

    @FXML
    void executeOnMouseClicked(MouseEvent event) {
        busqueda();
    }

    public void busqueda() {
        String input = TA_consultas.getText().replaceAll("\n", "").replaceAll("\\(", " ( ").replaceAll("\\)", " )").replaceAll(";", "  ; ").replaceAll(",", " ,").replaceAll("\t", " ").replaceAll("  ", " ");
        String[] dataInput = input.split(" ");

        ArrayList<String> simbolosNoValidos = new ArrayList<>();
        for (String data : dataInput) {
            boolean simboloValido = false;
            for (Token token : tokens) {
                if ((!(token.getNombre().equals("Identificador")) && token.isin(data)) || (token.getNombre().equals("Identificador") && token.isin(data) && !(palabrasReservadas.isin(data)) && !(tiposDato.isin(data)))) {
                    System.out.println(token + "-> " + data);
                    simboloValido = true;
                }
            }

            if (!simboloValido) {
                simbolosNoValidos.add(data);
            }
        }

        if (!simbolosNoValidos.isEmpty()) {
            System.out.println("Simbolos no validos " + simbolosNoValidos.size());
            for (String simbolo : simbolosNoValidos) {
                System.out.println(simbolo);
            }
        }
    }

    @FXML
    void salirOnMouseClicked(MouseEvent event) {
        System.exit(1);
    }


    public void setTokens() {
        ArrayList<String> simbolosPalabrasReservadas = new ArrayList<>();
        ArrayList<String> simbolosTiposDato = new ArrayList<>();
        ArrayList<String> parentesisApertura = new ArrayList<>();
        ArrayList<String> parentesisCierre = new ArrayList<>();
        ArrayList<String> coma = new ArrayList<>();
        ArrayList<String> puntoComa = new ArrayList<>();

        simbolosPalabrasReservadas.add("create");
        simbolosPalabrasReservadas.add("database");
        simbolosPalabrasReservadas.add("table");
        simbolosPalabrasReservadas.add("null");
        simbolosPalabrasReservadas.add("not");
        simbolosPalabrasReservadas.add("primary");
        simbolosPalabrasReservadas.add("key");
        simbolosPalabrasReservadas.add("auto_increment");

        simbolosTiposDato.add("decimal");
        simbolosTiposDato.add("double");
        simbolosTiposDato.add("date");
        simbolosTiposDato.add("datetime");
        simbolosTiposDato.add("float");
        simbolosTiposDato.add("real");
        simbolosTiposDato.add("int");
        simbolosTiposDato.add("tinyint");
        simbolosTiposDato.add("smallint");
        simbolosTiposDato.add("mediumint");
        simbolosTiposDato.add("char");
        simbolosTiposDato.add("nchar");
        simbolosTiposDato.add("varchar");
        simbolosTiposDato.add("text");
        simbolosTiposDato.add("tinytext");
        simbolosTiposDato.add("mediumtext");
        simbolosTiposDato.add("longtext");
        simbolosTiposDato.add("year");
        simbolosTiposDato.add("time");
        simbolosTiposDato.add("timestamp");

        coma.add(",");
        parentesisApertura.add("(");
        parentesisCierre.add(")");
        puntoComa.add(";");

//        Creación de tokens
        tokens.add(new Token("Punto y Coma", puntoComa));
        tokens.add(new Token("Coma", coma));
        tokens.add(new Token("Parentesis apertura", parentesisApertura));
        tokens.add(new Token("Parentesis cierre", parentesisCierre));

        identificador = new Token("Identificador", "[a-zA-Z]+(_[a-zA-Z]+)*");
        longitud = new Token("Lonigtud", "(1|2|3|4|5|6|7|8|9)|((1|2|3|4|5|6|7|8|9)(0|1|2|3|4|5|6|7|8|9))|((1|2)((0|1|2|3|4|5))((0|1|2|3|4|5)))");
        palabrasReservadas = new Token("Palabras Reservadas", simbolosPalabrasReservadas);
        tiposDato = new Token("Tipos de Dato", simbolosTiposDato);

        tokens.add(longitud);
        tokens.add(identificador);
        tokens.add(palabrasReservadas);
        tokens.add(tiposDato);
    }
}