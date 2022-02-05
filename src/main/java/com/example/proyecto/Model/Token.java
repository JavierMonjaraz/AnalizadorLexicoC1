package com.example.proyecto.Model;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Token {

    private String nombre;
    private ArrayList<String> simbolos;
    private String regex;

    public Token(String nombre, ArrayList<String> simbolos) {
        this.nombre = nombre;
        this.simbolos = simbolos;
        this.regex = "";
    }

    public Token(String nombre, String regex) {
        this.nombre = nombre;
        this.regex = regex;
        this.simbolos = new ArrayList<>();
    }

    public Token(String nombre) {
        this.nombre = nombre;
        this.regex="";
        this.simbolos = new ArrayList<>();
    }

    public void addSimbolo(String item) {
        this.simbolos.add(item);
    }



    public ArrayList<String> getSimbolos() {
        return simbolos;
    }

    public void setSimbolos(ArrayList<String> simbolos) {
        this.simbolos = simbolos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isin(String busqueda) {
        boolean isValid = false;
        if (!simbolos.isEmpty()) {
            isValid = this.simbolos.contains(busqueda);
        } else if (!regex.equals("")) {
            isValid = Pattern.matches(regex, busqueda);
        }
        return isValid;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
