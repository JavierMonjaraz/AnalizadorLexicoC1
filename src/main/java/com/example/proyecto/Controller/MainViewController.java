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
import javafx.stage.Modality;
import javafx.stage.Stage;
import java_cup.runtime.Symbol;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

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

    private AnalizadorLexico analizadorLexico;

    void iniciarLexer(String ruta) {
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
        analizadorLexico = new AnalizadorLexico();
    }

    void evaluarSintaxis() {
        String ST = TA_consultas.getText();
        Sintax s = new Sintax(new LexerCup(new StringReader(ST)));

        try {
            s.parse();
            System.out.println("Sintaxis Correcta");
        } catch (Exception e) {
            Symbol sym = s.getS();
//            System.out.println("Error de sintaxis en la linea: "+(sym.right)+ "indice: "+(sym.left)+" Texto: "+sym.value);
            System.out.println("Error de sintaxis en la linea: " + (sym.right + 1));
        }
    }

    void lexema() {
//        File archivo = new File("archivo.txt");
//        PrintWriter escribir;
//        try {
//            escribir = new PrintWriter(archivo);
//            escribir.print(TA_consultas.getText());
//            escribir.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            Reader lector = new BufferedReader(new FileReader("archivo.txt"));
//            Lexer lexer = new Lexer(lector);
//            String resultado = "";
//            while (true) {
//                Tokens tokens = lexer.yylex();
//                if (tokens == null) {
//                    resultado += "FIN";
////                    txtResultado.setText(resultado);
//                    System.out.println(resultado);
//                    return;
//                }
//                switch (tokens) {
//                    case ERROR:
//                        resultado += lexer.lexeme+"Simbolo no definido\n";
//                        break;
//                    case Identificador:
//                    case Longitud:
//                    case ParentesisApertura:
//                    case ParentesisCierre:
//                    case COMA:
//                    case DataType:
//                    case PCOMA:
//                    case Reservadas:
//                        resultado += lexer.lexeme + ": Es un " + tokens + "\n";
//                        break;
//                    default:
//                        resultado += "Token: " + tokens + "\n";
//                        break;
//                }
//            }
//        } finally {
//
//        }
    }

    @FXML
    void executeOnMouseClicked(MouseEvent event) throws IOException {
        analizadorLexico.busqueda(TA_consultas, ver_btn);
        analizadorLexico.showMensaje(mensaje, ver_btn);
        if (analizadorLexico.getSimbolosNoValidos().isEmpty())
            evaluarSintaxis();
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

            controlador.initialize(analizadorLexico.getTokensEncontrados(), analizadorLexico.getSimbolosNoValidos());
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.getIcons().add(new Image("assets/mysqllogo.jpg"));
            stage.setTitle("Detalles");
            stage.setResizable(false);
            stage.showAndWait();
//            ver_btn.setVisible(false);
//            mensaje.setText("");
        } catch (IOException ex) {
            System.out.println("IO Exception: " + ex.getMessage());
        }
    }
}