package com.practica_1.Backend.AutomataAnalizador;

import javax.swing.*;

import com.practica_1.Backend.Recuadro.Recuadro;
import com.practica_1.Backend.TokensData.TokensData;
import com.practica_1.Frontend.FramePrincipal;

public class AutomataAnalizador implements Runnable {

    private final char[] SIGNOS_SIMPLES = {'^', '(', ')', '{', '}', '[', ']', ',', '.'};
    private final char[] SIGNOS_DOBLES = {'+', '-', '/', '*', '=', '>', '<'};

    private FramePrincipal frame;
    private JTextArea txa;
    private Recuadro[] tokens;

    public AutomataAnalizador(FramePrincipal frame) {
        this.frame = frame;
    }

    @Override
    public void run() {

        try {
            do {
                tokens = new Recuadro[0];

                String[] lineas = txa.getText().split("\\n");

                serpararTokens(lineas);

                revisarTokens();

                frame.pintarRecuadros(tokens);

                Thread.sleep(50);
            } while (true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void setTxa(JTextArea txa) {
        this.txa = txa;
    }

    private void serpararTokens(String[] lineas) {

        int numero = 1;
        int i = 0;

        for (int j = 0; j < lineas.length; j++) {
            String linea = lineas[j];
            numero = 1;
            i = 0;
            while (i < linea.length()) {
                char caracter = linea.charAt(i);
                String palabra = "" + caracter;
                if (caracter == ' ') {
                    i++;
                } else {
                    if (caracter == '"') {
                        palabra = extraerCadena(linea, palabra, i);
                        i += palabra.length();
                    } else if (esNumero(caracter)) {
                        palabra = extraerNumero(linea, palabra, i);
                        i += palabra.length();
                    } else if (caracter == 39) {
                        palabra = extraerComilla(linea, palabra, i);
                        i += palabra.length();
                    } else if (esSigno(caracter)) {
                        i += palabra.length();
                    } else if (esSignoDoble(caracter)) {
                        palabra = extraerSignoDoble(linea, palabra, i);
                        i += palabra.length();
                    } else {
                        palabra = extraerPalabra(linea, palabra, i);
                        i += palabra.length();
                    }
                    agregarPalabra(palabra, j + 1, numero);
                    numero++;
                }
            }    
        }
    }

    private String extraerCadena(String linea, String palabra, int index){
        char caracter;
        try {
            do {
                index++;
                caracter = linea.charAt(index);
                palabra = palabra + caracter;
            } while (linea.charAt(index) != '"');
        } catch (IndexOutOfBoundsException e) {
        }

        return palabra;
    }

    private String extraerPalabra(String linea, String palabra, int index){
        char caracter;
        try {
            while (esLetra(linea.charAt(index + 1))) {
                index++;
                caracter = linea.charAt(index);
                palabra = palabra + caracter;
            }
        } catch (IndexOutOfBoundsException e) {
        }
        switch (palabra) {
            case "Console":
                extraerConsole(linea, palabra, index);
                break;
            case "Square":
                extraerSquare(linea, palabra, index);
                break;
        }
        return palabra;
    }

    private String extraerConsole(String linea, String palabra, int index){
        char caracter = 0;
        try {
            for (int i = 0; i < 10; i++) {
                index++;
                caracter = linea.charAt(index);
                palabra = palabra + caracter;    
            }
            if (linea.charAt(index - 7) == 'W') {
                index++;
                caracter = linea.charAt(index);
                palabra = palabra + caracter;    
            }
        } catch (IndexOutOfBoundsException e) {
        }

        return palabra;
    }

    private String extraerSquare(String linea, String palabra, int index){
        char caracter;
        try {
            for (int i = 0; i < 6; i++) {
                index++;
                caracter = linea.charAt(index);
                palabra = palabra + caracter;
            }
            if (linea.charAt(index + 1) == '(') {
                do {
                    index++;
                    caracter = linea.charAt(index);
                    palabra = palabra + caracter;    
                } while (caracter != ')');
            }
        } catch (IndexOutOfBoundsException e) {
        }
        return palabra;
    }

    private String extraerNumero(String linea, String palabra, int index){
        char caracter;
        try {
            while (esNumero(linea.charAt(index + 1)) || linea.charAt(index + 1) == '.') {
                index++;
                caracter = linea.charAt(index);
                palabra = palabra + caracter;
            }
        } catch (IndexOutOfBoundsException e) {
        }

        return palabra;
    }

    private String extraerComilla(String linea, String palabra, int index){
        char caracter = 0;
        try {
            for (int i = 0; i < 2; i++) {
                index++;
                caracter = linea.charAt(index);
                palabra = palabra + caracter; 
            }

            if (caracter != 39) {
                for (int i = index; i < linea.length(); i++) {
                    index++;
                    caracter = linea.charAt(index);
                    palabra = palabra + caracter; 
                }    
            }
        } catch (IndexOutOfBoundsException e) {
        }

        return palabra;
    }

    private String extraerSignoDoble(String linea, String palabra, int index){
        char caracter;
        index++;
        try {
            caracter = linea.charAt(index);
            if (caracter == '=' || caracter == '>') {
                palabra = palabra + caracter;
            }     
        } catch (IndexOutOfBoundsException e) {
        }

        return palabra;
    }

    private Boolean esLetra(char caracter) {
        if (caracter > '@' && caracter < '[') {
            return true;
        } else if (caracter > '`' && caracter < '{') {
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

    private void agregarPalabra(String palabra, int linea, int columna) {
        Recuadro[] save = new Recuadro[tokens.length];

        for (int i = 0; i < tokens.length; i++) {
            save[i] = tokens[i];
        }

        tokens = new Recuadro[save.length + 1];

        for (int i = 0; i < save.length; i++) {
            tokens[i] = save[i];
        }

        Recuadro recuadro = new Recuadro();
        recuadro.setLexema(palabra);
        recuadro.setLinea(linea);
        recuadro.setColumna(columna);

        tokens[tokens.length - 1] = recuadro;
    }

    private void revisarTokens(){
        TokensData tokensData = new TokensData();

        for (int i = 0; i < tokens.length; i++) {
            String lexema = tokens[i].getLexema();
            tokens[i].setToken(tokensData.compararToken(lexema));
            String token = tokens[i].getToken();
            tokens[i].setColor(tokensData.retornarColor(token));
        }
    }
}
