package com.example.proyecto.Model;

import java.util.ArrayList;
import java.util.List;

public class Token {

    String nombre;
    ArrayList<String> simbolos;

    public Token(String nombre, ArrayList<String> simbolos){
        this.nombre = nombre;
        this.simbolos = simbolos;
    }

    public void addSimbolo(String item){
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

    public boolean isin(String busqueda){
        boolean isin = this.simbolos.contains(busqueda);
        if (isin) {
            System.out.println("El elemento SÍ existe.  En el Token : " + getNombre());
            return true;
        } else {
            return false;
        }
    }


}
