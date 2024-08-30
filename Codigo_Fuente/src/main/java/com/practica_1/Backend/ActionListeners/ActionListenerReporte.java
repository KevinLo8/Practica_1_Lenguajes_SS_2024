package com.practica_1.Backend.ActionListeners;

import java.awt.event.*;

import com.practica_1.Frontend.FramePrincipal;

public class ActionListenerReporte implements ActionListener {

    private FramePrincipal framePrincipal;

    public ActionListenerReporte(FramePrincipal framePrincipal) {
        this.framePrincipal = framePrincipal;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        framePrincipal.mostrarReporte();
    }

}
