package com.practica_1.Backend.Recuadro;

public class Recuadro {

    private String token;
    private String lexema;
    private int linea;
    private int columna;
    private String color = "#FFFFFF";
    
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getLexema() {
        return lexema;
    }
    public void setLexema(String lexema) {
        this.lexema = lexema;
    }
    public int getLinea() {
        return linea;
    }
    public void setLinea(int linea) {
        this.linea = linea;
    }
    public int getColumna() {
        return columna;
    }
    public void setColumna(int columna) {
        this.columna = columna;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

}
