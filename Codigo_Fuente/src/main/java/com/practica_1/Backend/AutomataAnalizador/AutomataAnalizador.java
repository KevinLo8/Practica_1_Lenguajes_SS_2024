package com.practica_1.Backend.AutomataAnalizador;

import javax.swing.*;

import com.practica_1.Backend.Recuadro.Recuadro;
import com.practica_1.Backend.Tokens.Tokens;
import com.practica_1.Frontend.FramePrincipal;

public class AutomataAnalizador implements Runnable {

    private final char[] SIGNOS_SIMPLES = {'^', '(', ')', '{', '}', '[', ']', ',', '.'};
    private final char[] SIGNOS_DOBLES = {'+', '-', '/', '*', '=', '>', '<'};

    private FramePrincipal frame;
    private JTextArea txa;
    private Recuadro[] token;

    public AutomataAnalizador(FramePrincipal frame, Recuadro[] token) {
        this.frame = frame;
        this.token = token;
    }

    @Override
    public void run() {

        try {
            do {
                String oracion = txa.getText();

                String[] palabras = serpararTokens(oracion);

                int alto = frame.getRecuadro().length;
                int ancho = frame.getRecuadro()[0].length;
                token = revisarTokens(palabras, alto, ancho);

                frame.pintarRecuadros(token);

                Thread.sleep(50);
            } while (true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void setTxa(JTextArea txa) {
        this.txa = txa;
    }

    private String[] serpararTokens(String oracion) {

        String[] palabras = new String[0];
        int i = 0;

        while (i < oracion.length()) {
            char caracter = oracion.charAt(i);
            String palabra = "" + caracter;
            if (caracter == ' ') {
                i++;
            } else {
                if (caracter == '"') {
                    palabra = extraerCadena(oracion, palabra, i);
                    i += palabra.length();
                } else if (esNumero(caracter)) {
                    palabra = extraerNumero(oracion, palabra, i);
                    i += palabra.length();
                } else if (esSigno(caracter)) {
                    i += palabra.length();
                } else if (esSignoDoble(caracter)) {
                    palabra = extraerSignoDoble(oracion, palabra, i);
                    i += palabra.length();
                } else {
                    palabra = extraerPalabra(oracion, palabra, i);
                    i += palabra.length();
                }
                palabras = agregarPalabra(palabras, palabra);
            }
        }

        return palabras;
    }

    private String extraerCadena(String oracion, String palabra, int index){
        char caracter;
        try {
            do {
                index++;
                caracter = oracion.charAt(index);
                palabra = palabra + caracter;
            } while (oracion.charAt(index) != '"');
        } catch (IndexOutOfBoundsException e) {
        }

        return palabra;
    }

    private String extraerPalabra(String oracion, String palabra, int index){
        char caracter;
        try {
            while (esLetra(oracion.charAt(index))) {
                index++;
                caracter = oracion.charAt(index);
                palabra = palabra + caracter;
            }
        } catch (IndexOutOfBoundsException e) {
        }
        switch (palabra) {
            case "Console":
                extraerConsole(oracion, palabra, index);
                break;
            case "Square":
                extraerSquare(oracion, palabra, index);
                break;
        }
        return palabra;
    }

    private String extraerConsole(String oracion, String palabra, int index){
        char caracter = 0;
        try {
            for (int i = 0; i < 10; i++) {
                index++;
                caracter = oracion.charAt(index);
                palabra = palabra + caracter;    
            }
            if (oracion.charAt(index - 7) == 'W') {
                index++;
                caracter = oracion.charAt(index);
                palabra = palabra + caracter;    
            }
        } catch (IndexOutOfBoundsException e) {
        }

        return palabra;
    }

    private String extraerSquare(String oracion, String palabra, int index){
        char caracter;
        try {
            for (int i = 0; i < 6; i++) {
                index++;
                caracter = oracion.charAt(index);
                palabra = palabra + caracter;
            }
            if (oracion.charAt(index + 1) == '(') {
                do {
                    index++;
                    caracter = oracion.charAt(index);
                    palabra = palabra + caracter;    
                } while (caracter != ')');
            }
        } catch (IndexOutOfBoundsException e) {
        }
        return palabra;
    }

    private String extraerNumero(String oracion, String palabra, int index){
        char caracter;
        try {
            while (esNumero(oracion.charAt(index + 1)) && oracion.charAt(index + 1) != '.') {
                index++;
                caracter = oracion.charAt(index);
                palabra = palabra + caracter;
            }
        } catch (IndexOutOfBoundsException e) {
        }

        return palabra;
    }

    private String extraerSignoDoble(String oracion, String palabra, int index){
        char caracter;
        index++;
        try {
            caracter = oracion.charAt(index);
            if (caracter == '=' || caracter == '>') {
                palabra = palabra + caracter;
            }     
        } catch (IndexOutOfBoundsException e) {
        }

        return palabra;
    }

    private Boolean esLetra(char caracter) {
        if (caracter > '@' || caracter < '[') {
            return true;
        } else if (caracter > '`' || caracter < '{') {
            return true; 
        } else {
            return false;
        }
    }

    private Boolean esNumero(char character) {
        String caracter = "" + character;
        try {
            Integer.valueOf(caracter);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private Boolean esSigno(char caracter) {
        for (int i = 0; i < SIGNOS_SIMPLES.length; i++) {
            if (caracter == SIGNOS_SIMPLES[i]) {
                return true;
            }
        }
        return false;
    }

    private Boolean esSignoDoble(char caracter) {
        for (int i = 0; i < SIGNOS_DOBLES.length; i++) {
            if (caracter == SIGNOS_DOBLES[i]) {
                return true;
            }
        }
        return false;
    }

    private String[] agregarPalabra(String[] palabras, String palabra) {
        String[] retorno = new String[palabras.length + 1];

        for (int i = 0; i < palabras.length; i++) {
            retorno[i] = palabras[i];
        }

        retorno[palabras.length] = palabra;

        return retorno;
    }

    private Recuadro[] revisarTokens(String[] palabras, int alto, int ancho){
        Recuadro[] retorno = null;
        Tokens tokens = new Tokens();

        for (int i = 0; i < palabras.length; i++) {
            Recuadro recuadro = new Recuadro();
            recuadro.setLexema(palabras[i]);
            recuadro.setToken(tokens.compararToken(palabras[i]));
        }

        return retorno;
    }
}
