package com.example.proyecto.Model;

import java.util.*;

public class AnalizadorSemantico {
    private Token tokens;
    private Token identificadores;
    private ArrayList<String> errores;

    public AnalizadorSemantico(Token tokens, Token identificadores) {
        this.tokens = tokens;
        this.errores = new ArrayList<>();
        this.identificadores = identificadores;
    }

    public boolean semanticaValida() {
        boolean semanticaValida = true;

        String databaseCreated = identificadores.getSimbolos().get(0);
        String databaseUsed = identificadores.getSimbolos().get(1);

        if (databaseCreated.equals(databaseUsed)) {
            boolean isCreate = false;
            ArrayList<String> tablas = new ArrayList<>();

            identificadores.getSimbolos().remove(databaseCreated);
            identificadores.getSimbolos().remove(databaseCreated);
            for (int i = 0; i < tokens.getSimbolos().size(); i++) {
                if (tokens.getSimbolos().get(i).equalsIgnoreCase("TABLE")) {
                    isCreate = true;
                }
                if (isCreate) {
                    String nombreTabla = tokens.getSimbolos().get(i + 1);
                    tablas.add(nombreTabla);
                    tokens.getSimbolos().set(i + 1, "Tabla");
                    identificadores.getSimbolos().remove(nombreTabla);
                    isCreate = false;
                }
            }

            if (tablas.size() == new HashSet<>(tablas).size()) {
                ArrayList<ArrayList<String>> tokensPorTablas = new ArrayList<>();
                boolean nuevaTabla = false;
                ArrayList<String> tabla = null;
                for (String simbolo : tokens.getSimbolos()) {
                    if (simbolo.equals("Tabla")) {
                        tabla = new ArrayList<>();
                        tokensPorTablas.add(tabla);
                        nuevaTabla = true;
                    }
                    if (nuevaTabla && !simbolo.equals("Tabla") && !simbolo.equalsIgnoreCase("table") && !simbolo.equalsIgnoreCase("create")) {
                        tabla.add(simbolo);
                    }
                }
                int numeroTabla = 0;
                int nAutoIncrements = 0;
                ArrayList<ArrayList<String>> identificadoresPorTablas = new ArrayList<>();
                for (ArrayList<String> tokens : tokensPorTablas) {
                    int index = 0;
                    ArrayList<String> identificadoresTabla = new ArrayList<>();
                    for (String token : tokens) {
                        if (identificadores.isin(token)) {
                            identificadoresTabla.add(token);
                        }
                        if (token.equalsIgnoreCase("AUTO_INCREMENT")) {
                            String tipoDato = tokens.get(index - 4);
                            String atributo = tokens.get(index - 5);
                            if (!tipoDato.equalsIgnoreCase("int")) {
                                semanticaValida = false;
                                errores.add("La columna "+atributo +" de la tabla "+tablas.get(numeroTabla)+" no se le puede aplicar la propiedad AUTO_INCREMENT");
                            }
                            nAutoIncrements++;
                        }
//                        System.out.println(token);
                        index++;
                    }
                    if (nAutoIncrements > 1) {
                        semanticaValida = false;
                        errores.add("No se utilizar la propiedad AUTO_INCREMENT mas de 1 veces en la tabla " + tablas.get(numeroTabla));
                    }
                    identificadoresPorTablas.add(identificadoresTabla);
                    numeroTabla++;
                    nAutoIncrements = 0;
                    index = 0;
                }

                numeroTabla = 0;
                for (ArrayList<String> tokens : identificadoresPorTablas) {
                    String primaryKey = tokens.remove(tokens.size() - 1);
                    if (!tokens.contains(primaryKey)) {
                        errores.add("La columna " + primaryKey + " en la tabla " + tablas.get(numeroTabla) + " no se ha declarado");
                        semanticaValida = false;
                    }
                    int columnasDiferentes = new HashSet<>(tokens).size();
                    if (tokens.size() != columnasDiferentes) {
                        errores.add("No pueden existir columas repetidas en la tabla " + tablas.get(numeroTabla));
                        semanticaValida = false;
                    }
                    numeroTabla++;
                }
            } else {
                errores.add("No pueden existir tablas con el mismo identificador");
                semanticaValida = false;
            }
        } else {
            errores.add("La base de datos escogida no coincide con la creada");
            semanticaValida = false;
        }
        return semanticaValida;
    }

    public ArrayList<String> getErrores() {
        return errores;
    }
}
