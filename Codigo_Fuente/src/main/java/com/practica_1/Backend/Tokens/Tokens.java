package com.practica_1.Backend.Tokens;

public class Tokens {
    private String suma = "+";
    private String resta = "-";
    private String exponente = "^";
    private String division = "/";
    private String modulo = "Mod";
    private String multiplicacion = "*";
    private String igual = "=";
    private String diferente = "<>";
    private String mayorQue = ">";
    private String menorQue = "<";
    private String mayorIgualQue = ">=";
    private String menorIgualQue = "<=";
    private String y = "And";
    private String o = "Or";
    private String negacion = "Not";
    private String asignacionSimple = "==";
    private String[] asignacionCompuesta = {"+=", "-=", "*=", "/="};
    private String[] palabraReservada = {"Module", "End", "Sub", "Main", "Dim", "As",
            "Integer", "String", "Boolean", "Double", "Char", "Console.WriteLine",
            "Console.ReadLine", "If", "ElseIf", "Else", "Then", "While", "Do", "Loop", "For", "To", "Next", "Function", "Return", "Const"};
    private String[] Booleano = {"True", "False"};
    private String[] parentesis = {"(", ")"};
    private String[] llaves = {"{", "}"};
    private String[] corchetes = {"[", "]"};
    private String coma = ",";
    private String punto = ".";

    public String compararToken(String token){

        try {
            Integer.valueOf(token);
            return "Entero";
        } catch (NumberFormatException e) {
        }

        try {
            Double.valueOf(token);
            return "Decimal";
        } catch (NumberFormatException e) {
        }


        if (token.charAt(0) == '"') {
            return "Cadena";
        } else if (token.length() == 3 && token.charAt(0) == 39 && token.charAt(token.length() - 1) == 39) {
            return "Carácter";
        } else if (token.charAt(0) == 39) {
            return "Comentario";
        } else if (token.equals(suma)) {
            return "Suma";
        } else if (token.equals(resta)) {
            return "Resta";
        } else if (token.equals(exponente)) {
            return "Exponente";
        } else if (token.equals(division)) {
            return "División";
        } else if (token.equals(modulo)) {
            return "Modulo";
        } else if (token.equals(multiplicacion)) {
            return "Multiplicación";
        } else if (token.equals(igual)) {
            return "Igual";
        } else if (token.equals(diferente)) {
            return "Diferente";
        } else if (token.equals(mayorQue)) {
            return "Mayor que";
        } else if (token.equals(menorQue)) {
            return "Menor que";
        } else if (token.equals(mayorIgualQue)) {
            return "Mayor o Igual que";
        } else if (token.equals(menorIgualQue)) {
            return "Menor o Igual que";
        } else if (token.equals(y)) {
            return "Y";
        } else if (token.equals(o)) {
            return "O";
        } else if (token.equals(negacion)) {
            return "Negación";
        } else if (token.equals(asignacionSimple)) {
            return "Asignación Simple";
        } else if (perteneceA(token, asignacionCompuesta)) {
            return "Asignación Compuesta";
        } else if (perteneceA(token, palabraReservada)) {
            return "Palabre Reservada";
        } else if (perteneceA(token, Booleano)) {
            return "Booleano";
        } else if (perteneceA(token, parentesis)) {
            return "Parentesis";
        } else if (perteneceA(token, llaves)) {
            return "Llaves";
        } else if (perteneceA(token, corchetes)) {
            return "Corchetes";
        } else if (token.equals(coma)) {
            return "Coma";
        } else if (token.equals(punto)) {
            return "Punto";
        }
        return null;
    }

    private boolean perteneceA(String token, String[] tokens) {
        for (int i = 0; i < tokens.length; i++) {
            if (token.equals(tokens[i])) {
                return true;
            }
        }
        return false;
    }
}
