package com.example.proyecto.Controller;

import com.example.proyecto.Model.*;
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
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java_cup.runtime.Symbol;

import java.io.*;
import java.util.ArrayList;

public class MainViewController {

    @FXML
    private TextArea TA_consultas;

    @FXML
    private Button execute_btn;

    @FXML
    private Label mensaje;

    @FXML
    private Button ver_btn;

    @FXML
    private Label message;

    @FXML
    private Rectangle cmd;

    private AnalizadorLexico analizadorLexico;

    private Token simbolosNoValidos;

    private ArrayList<Token> tokensEncontrados;

    private void iniciarLexer(String ruta) {
        File archivo = new File(ruta);
//        JFlex.Main.generate(archivo);
    }

    @FXML
    public void initialize() throws Exception {
        // Genera las clases de LexerCup y JCup
//        Path path = Paths.get("");
//        String ruta = path.toAbsolutePath().toString().replace("\\", "/") + "/src/main/java/com/example/proyecto/Model/Lexer.flex";
//        String rutaCup = path.toAbsolutePath().toString().replace("\\", "/") + "/src/main/java/com/example/proyecto/Model/Sintax.cup";
//        String[] rutaS = {"-parser","Sintax",rutaCup};
//        String ruta3 = path.toAbsolutePath().toString().replace("\\", "/") + "/src/main/java/com/example/proyecto/Model/LexerCup.flex";
//        iniciarLexer(ruta3);
//        java_cup.Main.main(rutaS);
        cmd.setVisible(false);
        analizadorLexico = new AnalizadorLexico();
    }

    void evaluarSintaxis() {
        cmd.setVisible(true);
        String ST = TA_consultas.getText();
        Sintax s = new Sintax(new LexerCup(new StringReader(ST)));

        try {
            s.parse();
            message.setTextFill(Color.web("white"));
            message.setText("> Sintaxis Correcta");
        } catch (Exception e) {
            Symbol sym = s.getS();
            message.setTextFill(Color.web("EE6023"));
            message.setText("> Error de sintaxis en la linea: " + (sym.right + 1));
        }
    }

    void lexema() throws IOException {
        Token pcoma = new Token("Punto y Coma", new ArrayList<>());
        Token coma = new Token("Coma", new ArrayList<>());
        Token pa = new Token("Parentesis apertura", new ArrayList<>());
        Token pc = new Token("Parentesis cierre", new ArrayList<>());
        Token identificador = new Token("Identificador", new ArrayList<>());
        Token longitud = new Token("Longitud", new ArrayList<>());
        Token palabrasReservadas = new Token("Palabras Reservadas", new ArrayList<>());
        Token tiposDato = new Token("Tipos de Dato", new ArrayList<>());

        simbolosNoValidos = new Token("No válidos", new ArrayList<>());

        Lexer lexer = new Lexer(new StringReader(TA_consultas.getText()));

        while (true) {
            Tokens tokens = lexer.yylex();
            if (tokens == null) {
                tokensEncontrados = new ArrayList<>();
                tokensEncontrados.add(pa);
                tokensEncontrados.add(pc);
                tokensEncontrados.add(pcoma);
                tokensEncontrados.add(identificador);
                tokensEncontrados.add(longitud);
                tokensEncontrados.add(palabrasReservadas);
                tokensEncontrados.add(tiposDato);
                tokensEncontrados.add(coma);
                showMensaje();
                return;
            }
            switch (tokens) {
                case ERROR:
                    simbolosNoValidos.addSimbolo(lexer.lexeme);
                    break;
                case Identificador:
                    identificador.addSimbolo(lexer.lexeme);
                    break;
                case Longitud:
                    longitud.addSimbolo(lexer.lexeme);
                    break;
                case ParentesisApertura:
                    pa.addSimbolo(lexer.lexeme);
                    break;
                case ParentesisCierre:
                    pc.addSimbolo(lexer.lexeme);
                    break;
                case COMA:
                    coma.addSimbolo(lexer.lexeme);
                    break;
                case DataType:
                    tiposDato.addSimbolo(lexer.lexeme);
                    break;
                case PCOMA:
                    pcoma.addSimbolo(lexer.lexeme);
                    break;
                case Reservadas:
                    palabrasReservadas.addSimbolo(lexer.lexeme);
                    break;
                default:
                    break;
            }
        }

    }

    @FXML
    void executeOnMouseClicked(MouseEvent event) throws IOException {
        message.setText("");
        cmd.setVisible(false);
        if (!TA_consultas.getText().equals("")) {
            lexema();
            if (simbolosNoValidos.getSimbolos().isEmpty())
                evaluarSintaxis();
        }
    }

    public void showMensaje() {
        mensaje.setText("");

        if (!simbolosNoValidos.getSimbolos().isEmpty()) {
            mensaje.setTextFill(Color.web("FF8B00"));
            int cantidadSimbolos = simbolosNoValidos.getSimbolos().size();
            if (cantidadSimbolos == 1) {
                mensaje.setText("Se ha encontrado " + cantidadSimbolos + " Símbolo no válido");
            } else {
                mensaje.setText("Se han encontrado " + cantidadSimbolos + " símbolos no válidos");
            }
        } else {
            mensaje.setTextFill(Color.web("white"));
            mensaje.setText("Todos los símbolos introducidos han sido aceptados");
        }
        ver_btn.setVisible(true);
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

            controlador.initialize(tokensEncontrados, simbolosNoValidos.getSimbolos());
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.getIcons().add(new Image("assets/mysqllogo.jpg"));
            stage.setTitle("Detalles");
            stage.setResizable(false);
            stage.showAndWait();
        } catch (IOException ex) {
            System.out.println("IO Exception: " + ex.getMessage());
        }
    }
}