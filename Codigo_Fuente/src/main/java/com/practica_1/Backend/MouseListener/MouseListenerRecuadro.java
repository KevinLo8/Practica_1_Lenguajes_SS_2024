package com.practica_1.Backend.MouseListener;

import java.awt.event.*;

import com.practica_1.Frontend.JLabel.PanelRecuadro;

public class MouseListenerRecuadro implements MouseListener {

    private PanelRecuadro panel;

    public MouseListenerRecuadro(PanelRecuadro panel) {
        this.panel = panel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        panel.generarInfo();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
