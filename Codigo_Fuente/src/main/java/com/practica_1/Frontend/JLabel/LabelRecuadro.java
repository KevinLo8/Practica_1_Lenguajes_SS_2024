package com.practica_1.Frontend.JLabel;

import javax.swing.*;

import com.practica_1.Backend.Colores.Colores;
import com.practica_1.Backend.Recuadro.Recuadro;

public class LabelRecuadro extends JLabel {

    private Recuadro recuadro;

    public LabelRecuadro(int tamaño) {
        recuadro = new Recuadro();
        setIcon(recuadro.retornarImagen(Colores.FFFFFF, tamaño - 2));
    }
    
}
