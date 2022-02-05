package com.example.proyecto.Controller;

import com.example.proyecto.Model.Token;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.*;

public class MainViewController {

    private ArrayList<Token> tokens = new ArrayList<>();
    private Token identificador;
    private Token longitud;
    private Token palabrasReservadas;
    private Token tiposDato;
    private ArrayList<String> simbolosNoValidos = new ArrayList<>();
    private ArrayList<Token> tokensEncontrados = new ArrayList<>();

    @FXML
    private TextArea TA_consultas;

    @FXML
    private Button execute_btn;

    @FXML
    private Label mensaje;

    @FXML
    public void initialize() {
        setTokens();
    }

    @FXML
    void executeOnMouseClicked(MouseEvent event) {
        busqueda();
        setMensaje();
    }

    public void busqueda() {
        simbolosNoValidos.clear();
        tokensEncontrados.clear();
        String input = TA_consultas.getText().replaceAll("\n", "").replaceAll("\\(", " ( ").replaceAll("\\)", " )").replaceAll(";", "  ; ").replaceAll(",", " ,").replaceAll("\t", " ").replaceAll("  ", " ");
        String[] dataInput = input.split(" ");

        for (String simbolo : dataInput) {
            boolean simboloValido = false;
            for (Token token : tokens) {
                if ((!(token.getNombre().equals("Identificador")) && token.isin(simbolo)) || (token.getNombre().equals("Identificador") && token.isin(simbolo) && !(palabrasReservadas.isin(simbolo)) && !(tiposDato.isin(simbolo)))) {
                    agregarSimboloEncontrado(token, simbolo);
                    simboloValido = true;
                }
            }

            if (!simboloValido) {
                simbolosNoValidos.add(simbolo);
            }
        }
    }

    void agregarSimboloEncontrado(Token token, String simbolo) {
        boolean crearToken = true;
        for (Token tokenEncontrado : tokensEncontrados) {
            if (tokenEncontrado.getNombre() == token.getNombre()) {
                tokenEncontrado.addSimbolo(simbolo);
                crearToken = false;
            }
        }

        if (crearToken || tokensEncontrados.isEmpty()) {
            Token nuevoToken = new Token(token.getNombre());
            nuevoToken.addSimbolo(simbolo);
            tokensEncontrados.add(nuevoToken);
        }
    }

    void setMensaje() {
        mensaje.setText("");

        if (!simbolosNoValidos.isEmpty()) {
            mensaje.setTextFill(Color.web("FF8B00"));
            int cantidadSimbolos = simbolosNoValidos.size();
            if (cantidadSimbolos == 1) {
                mensaje.setText("Se ha encontrado " + cantidadSimbolos + " Símbolo no válido");
            } else {
                mensaje.setText("Se han encontrado " + cantidadSimbolos + " símbolos no válidos");
            }

            for (String simbolo : simbolosNoValidos) {
                System.out.println(simbolo);
            }
        } else {
            mensaje.setTextFill(Color.web("white"));
            mensaje.setText("Todos los símbolos introducidos han sido aceptados");
            for (Token token : tokensEncontrados) {
                System.out.println("-----------------------------");
                System.out.println("token: " + token + " | ocurrencias: " + token.getSimbolos().size());
                Map<String, Integer> ocurrencias = new HashMap<>();

                for (String simbolo : token.getSimbolos()) {
                    ocurrencias.merge(simbolo, 1, Integer::sum);
                }

                ocurrencias.forEach((simbolo, ocurrencia) -> System.out.println(simbolo + " : " + ocurrencia));
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