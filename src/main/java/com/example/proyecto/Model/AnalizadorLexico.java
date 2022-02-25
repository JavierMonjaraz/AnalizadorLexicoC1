package com.example.proyecto.Model;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class AnalizadorLexico {
    private ArrayList<Token> tokens = new ArrayList<>();
    private Token palabrasReservadas;
    private Token tiposDato;
    private ArrayList<String> simbolosNoValidos = new ArrayList<>();
    private ArrayList<Token> tokensEncontrados = new ArrayList<>();

    public AnalizadorLexico(){
        setTokens();
    }

    private void setTokens(){
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

        simbolosPalabrasReservadas.add("CREATE");
        simbolosPalabrasReservadas.add("DATABASE");
        simbolosPalabrasReservadas.add("TABLE");
        simbolosPalabrasReservadas.add("NULL");
        simbolosPalabrasReservadas.add("NOT");
        simbolosPalabrasReservadas.add("PRIMARY");
        simbolosPalabrasReservadas.add("KEY");
        simbolosPalabrasReservadas.add("AUTO_INCREMENT");

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

        Token identificador = new Token("Identificador", "[a-zA-Z]+(_[a-zA-Z]+)*");
        Token longitud = new Token("Longitud", "(1|2|3|4|5|6|7|8|9)|((1|2|3|4|5|6|7|8|9)(0|1|2|3|4|5|6|7|8|9))|((1|2)((0|1|2|3|4|5))((0|1|2|3|4|5)))");
        palabrasReservadas = new Token("Palabras Reservadas", simbolosPalabrasReservadas);
        tiposDato = new Token("Tipos de Dato", simbolosTiposDato);

        tokens.add(longitud);
        tokens.add(identificador);
        tokens.add(palabrasReservadas);
        tokens.add(tiposDato);
    }

    public void busqueda(TextArea cadena, Button boton) {
        simbolosNoValidos.clear();
        tokensEncontrados.clear();
        boton.setVisible(false);
        String input = cadena.getText().replaceAll("\n", "").replaceAll("\\(", " ( ").replaceAll("\\)", " )").replaceAll(";", "  ; ").replaceAll(",", " ,").replaceAll("\t", " ").replaceAll("  ", " ");
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

    private void agregarSimboloEncontrado(Token token, String simbolo) {
        boolean crearToken = true;
        for (Token tokenEncontrado : tokensEncontrados) {
            if (tokenEncontrado.getNombre().equals(token.getNombre())) {
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

    public void showMensaje(Label mensaje, Button boton) {
        mensaje.setText("");

        if (!simbolosNoValidos.isEmpty()) {
            mensaje.setTextFill(Color.web("FF8B00"));
            int cantidadSimbolos = simbolosNoValidos.size();
            if (cantidadSimbolos == 1) {
                mensaje.setText("Se ha encontrado " + cantidadSimbolos + " Símbolo no válido");
            } else {
                mensaje.setText("Se han encontrado " + cantidadSimbolos + " símbolos no válidos");
            }
        } else {
            mensaje.setTextFill(Color.web("white"));
            mensaje.setText("Todos los símbolos introducidos han sido aceptados");
        }
        boton.setVisible(true);
    }

    public ArrayList<String> getSimbolosNoValidos() {
        return simbolosNoValidos;
    }

    public ArrayList<Token> getTokensEncontrados() {
        return tokensEncontrados;
    }
}
