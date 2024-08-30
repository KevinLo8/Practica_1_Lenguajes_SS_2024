package com.practica_1.Backend.ActionListeners;

import java.awt.event.*;

import com.practica_1.Frontend.FramePrincipal;

public class ActionListenerCerrar implements ActionListener {

    private FramePrincipal frame;

    public ActionListenerCerrar(FramePrincipal frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.cerrarDialogs();
    }

}
