package com.example.proyecto.Model;

import java.util.List;

public class Produccion {
    private Simbolo simboloProduccion;
    private List<Simbolo> simbolos;

    public Produccion(Simbolo simboloProduccion, List<Simbolo> simbolos) {
        this.simboloProduccion = simboloProduccion;
        this.simbolos = simbolos;
    }

    public Simbolo getSimboloProduccion() {
        return simboloProduccion;
    }

    public void setSimboloProduccion(Simbolo simboloProduccion) {
        this.simboloProduccion = simboloProduccion;
    }

    public List<Simbolo> getSimbolos() {
        return simbolos;
    }

    public void setSimbolos(List<Simbolo> simbolos) {
        this.simbolos = simbolos;
    }
}
