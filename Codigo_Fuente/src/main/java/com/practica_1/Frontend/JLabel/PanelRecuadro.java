package com.practica_1.Frontend.JLabel;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

import com.practica_1.Backend.MouseListener.MouseListenerRecuadro;
import com.practica_1.Backend.Recuadro.Recuadro;
import com.practica_1.Frontend.FramePrincipal;


public class PanelRecuadro extends JPanel {

    private FramePrincipal framePrincipal;
    private Recuadro token = null;

    public PanelRecuadro(int tamaño, FramePrincipal framePrincipal) {
        this.framePrincipal = framePrincipal;
        setPreferredSize(new Dimension(tamaño, tamaño));
        setBackground(Color.WHITE);
        addMouseListener(new MouseListenerRecuadro(this));
    }
    
    public void setRecuadro(Recuadro token) {
        this.token = token;
    }

    public void generarInfo() {
        if (token != null) {
            framePrincipal.generarInfo(token);
        }
    }

}
