package com.practica_1.Frontend.JLabel;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;


public class PanelRecuadro extends JPanel {


    public PanelRecuadro(int tamaño) {
        setPreferredSize(new Dimension(tamaño, tamaño));
        setBackground(Color.WHITE);
    }
    
}
