package com.practica_1.Backend.ActionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import com.practica_1.Frontend.FramePrincipal;

public class ActionListenerSeleccionar implements ActionListener {

    private JTextField txf1;
    private FramePrincipal frame;

    public ActionListenerSeleccionar(FramePrincipal frame, JTextField txf1) {
        this.frame = frame;
        this.txf1 = txf1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

}
