package com.example.proyecto.Model;

import java.util.List;

public class Gramatica {
    private Simbolo simboloInicial;
    private List<Simbolo> simbolosNoTerminales;
    private List<Simbolo> simbolosTerminales;
    private List<String> producciones;

    public Gramatica(Simbolo simboloInicial, List<Simbolo> simbolosNoTerminales, List<Simbolo> simbolosTerminales, List<String> producciones) {
        this.simboloInicial = simboloInicial;
        this.simbolosNoTerminales = simbolosNoTerminales;
        this.simbolosTerminales = simbolosTerminales;
        this.producciones = producciones;
    }

    public Simbolo getSimboloInicial() {
        return simboloInicial;
    }

    public void setSimboloInicial(Simbolo simboloInicial) {
        this.simboloInicial = simboloInicial;
    }

    public List<Simbolo> getSimbolosNoTerminales() {
        return simbolosNoTerminales;
    }

    public void setSimbolosNoTerminales(List<Simbolo> simbolosNoTerminales) {
        this.simbolosNoTerminales = simbolosNoTerminales;
    }

    public List<Simbolo> getSimbolosTerminales() {
        return simbolosTerminales;
    }

    public void setSimbolosTerminales(List<Simbolo> simbolosTerminales) {
        this.simbolosTerminales = simbolosTerminales;
    }

    public List<String> getProducciones() {
        return producciones;
    }

    public void setProducciones(List<String> producciones) {
        this.producciones = producciones;
    }

    public void calcularConjuntoPrimero(){

    }

    public void calcularConjuntoSiguiente(){

    }
}
