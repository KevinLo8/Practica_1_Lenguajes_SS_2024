package com.practica_1.Backend.Recuadro;

import java.awt.*;
import java.io.*;

import javax.imageio.*;
import javax.swing.*;

public class Recuadro {

    private String token;
    private String lexema;
    private int linea;
    private int columna;
    private String color;
    
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

    public ImageIcon retornarImagen(String color, int tamaño) {

        ImageIcon imageOut = null;
        try {
            InputStream stream = getClass().getResourceAsStream(color);
            ImageIcon image = new ImageIcon(ImageIO.read(stream));
            imageOut = new ImageIcon(image.getImage().getScaledInstance(tamaño, tamaño, Image.SCALE_DEFAULT));
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageOut;
    }
}
