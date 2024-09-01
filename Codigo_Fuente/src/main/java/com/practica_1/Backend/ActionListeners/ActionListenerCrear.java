package com.practica_1.Backend.ActionListeners;

import java.awt.Color;
import java.awt.event.*;

import javax.swing.*;

import com.practica_1.Frontend.FramePrincipal;
import com.practica_1.Frontend.JLabel.PanelRecuadro;

public class ActionListenerCrear implements ActionListener {

    private FramePrincipal framePrincipal;
    private int alto, ancho;

    public ActionListenerCrear(FramePrincipal framePrincipal, int alto, int ancho) {
        this.framePrincipal = framePrincipal;
        this.alto = alto;
        this.ancho = ancho;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        PanelRecuadro[][] recuadro = new PanelRecuadro[alto][ancho];

        int tamaño;
        if (alto > ancho) {
            tamaño = framePrincipal.getSIZE_PANEL() / alto;
        } else {
            tamaño = framePrincipal.getSIZE_PANEL() / ancho;
        }

        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                PanelRecuadro lbl = new PanelRecuadro(tamaño, framePrincipal);
                lbl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                recuadro[i][j] = lbl;
            }
        }
        framePrincipal.crearEdicion(recuadro);
    }

}
