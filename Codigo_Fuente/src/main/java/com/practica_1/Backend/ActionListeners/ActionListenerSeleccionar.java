package com.practica_1.Backend.ActionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JTextField;

public class ActionListenerSeleccionar implements ActionListener {

    private JTextField txf1;

    public ActionListenerSeleccionar(JTextField txf1) {
        this.txf1 = txf1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(fileChooser);
        txf1.setText(fileChooser.getSelectedFile().getPath());
    }

}
