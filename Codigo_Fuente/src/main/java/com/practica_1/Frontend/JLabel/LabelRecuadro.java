package com.practica_1.Frontend.JLabel;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;


public class LabelRecuadro extends JPanel {


    public LabelRecuadro(int tamaño) {
        setPreferredSize(new Dimension(tamaño - 2, tamaño - 2));
        setBackground(Color.WHITE);
    }
    
}
