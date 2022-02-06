package com.example.proyecto.Controller;

import com.example.proyecto.Model.Token;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HelloController {

    @FXML
    private TreeView<String> tokens;

    public void initialize(ArrayList<Token> tokensEncontrados, ArrayList<String> simbolosNoValidos) {
        TreeItem rootItem = new TreeItem("Resumen");

        if (!tokensEncontrados.isEmpty()) {
            TreeItem encontrados = new TreeItem("Tokens encontrados: " + tokensEncontrados.size());
            for (Token token : tokensEncontrados) {
                TreeItem tokenItem = new TreeItem(token + ": " + token.getSimbolos().size());
                Map<String, Integer> ocurrencias = new HashMap<>();

                for (String simbolo : token.getSimbolos()) {
                    ocurrencias.merge(simbolo, 1, Integer::sum);
                }

                ocurrencias.forEach((simbolo, ocurrencia) -> tokenItem.getChildren().add(new TreeItem(simbolo + ": " + ocurrencia)));
                encontrados.getChildren().add(tokenItem);
            }
            rootItem.getChildren().add(encontrados);
        }

        if (!simbolosNoValidos.isEmpty()) {
            TreeItem simbolos = new TreeItem("Símbolos No validos");

            Map<String, Integer> ocurrencias = new HashMap<>();

            for (String simbolo : simbolosNoValidos) {
                ocurrencias.merge(simbolo, 1, Integer::sum);
            }

            ocurrencias.forEach((simbolo, ocurrencia) -> simbolos.getChildren().add(new TreeItem(simbolo + ": " + ocurrencia)));

            rootItem.getChildren().add(simbolos);
        }

        tokens.setRoot(rootItem);
        tokens.setShowRoot(false);
    }
}