package com.practica_1.Backend.ConexionArchivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.practica_1.Backend.Exception.ArchivoInexistenteException;

public class ConexionArchivos {

    public String leerArchivo(String path) throws ArchivoInexistenteException, IOException {

        File archivo = new File(path);
        String texto = null;

        if (!archivo.exists()) {
            throw new ArchivoInexistenteException("Archivo Inexistente");
        }

        try (FileReader fileReader = new FileReader(archivo);
            BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String linea = bufferedReader.readLine();
            while(linea != null) {
                texto = texto + linea;
                linea = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new IOException();
        }

        return texto;
    }


}
