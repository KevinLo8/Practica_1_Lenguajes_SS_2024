package com.practica_1.Backend.TokensData;

public class TokensData {
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
            return "Paréntesis";
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

    public String retornarColor(String token) {
      
        if (token == null) {
            return null;
        }

        switch (token) {
            case "Suma":
                return "#FF33FF";
            case "Resta":
                return "#C19A6B";
            case "Exponente":
                return "#FCD0B4";
            case "División":
                return "#B4D941";
            case "Modulo":
                return "#D9AB41";
            case "Multiplicación":
                return "#D80073";
            case "Igual":
                return "#6A00FF";
            case "Diferente":
                return "#3F2212";
            case "Mayor que":
                return "#D9D441";
            case "Menor que":
                return "#D94A41";
            case "Mayor o Igual que":
                return "#E3C800";
            case "Menor o Igual que" :
                return "#F0A30A";
            case "Y":
                return "#414ED9";
            case "O":
                return "#41D95D";
            case "Negación":
                return "#A741D9";
            case "Asignación Simple":
                return "#41D9D4";
            case "Asignación Compuesta":
                return "#FFFFFF";
            case "Palabre Reservada":
                return "#60A917";
            case "Entero":
                return "#1BA1E2";
            case "Decimal":
                return "#FFFF88";
            case "Cadena":
                return "#E51400";
            case "Booleano":
                return "#FA6800";
            case "Carácter":
                return "#0050EF";
            case "Comentario":
                return "#B3B3B3";
            case "Paréntesis":
                return "#9AD8DB";
            case "Llaves":
                return "#DBD29A";
            case "Corchetes":
                return "#DBA49A";
            case "Coma":
                return "#B79ADB";
            case "Punto":
                return "#9ADBA6";
        }
        return null;

    }
}
