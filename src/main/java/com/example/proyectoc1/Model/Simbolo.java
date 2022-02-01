package com.example.proyecto.Model;

public class Simbolo {
    private String nombreSimbolo;
    private boolean esInicial;
    private boolean esTerminal;

    public Simbolo(String nombreSimbolo, boolean esInicial, boolean esTerminal) {
        this.nombreSimbolo = nombreSimbolo;
        this.esInicial = esInicial;
        this.esTerminal = esTerminal;
    }

    public String getNombreSimbolo() {
        return nombreSimbolo;
    }

    public void setNombreSimbolo(String nombreSimbolo) {
        this.nombreSimbolo = nombreSimbolo;
    }

    public boolean isEsInicial() {
        return esInicial;
    }

    public void setEsInicial(boolean esInicial) {
        this.esInicial = esInicial;
    }

    public boolean isEsTerminal() {
        return esTerminal;
    }

    public void setEsTerminal(boolean esTerminal) {
        this.esTerminal = esTerminal;
    }

    @Override
    public String toString() {
        return nombreSimbolo;
    }
}
