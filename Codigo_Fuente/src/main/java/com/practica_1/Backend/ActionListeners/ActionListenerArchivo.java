package com.practica_1.Backend.ActionListeners;

import java.awt.event.*;

import com.practica_1.Frontend.FramePrincipal;

public class ActionListenerArchivo implements ActionListener {

    private FramePrincipal framePrincipal;

    public ActionListenerArchivo(FramePrincipal framePrincipal) {
        this.framePrincipal = framePrincipal;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        framePrincipal.preguntarArchivo();
    }

}
