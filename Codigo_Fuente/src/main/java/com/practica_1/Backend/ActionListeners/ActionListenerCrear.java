package com.practica_1.Backend.ActionListeners;

import java.awt.Color;
import java.awt.event.*;

import javax.swing.*;

import com.practica_1.Frontend.FramePrincipal;
import com.practica_1.Frontend.JDialog.DialogEspacio;
import com.practica_1.Frontend.JLabel.LabelRecuadro;

public class ActionListenerCrear implements ActionListener {

    private FramePrincipal framePrincipal;
    @SuppressWarnings("rawtypes")
    private JComboBox cbx1, cbx2;
    private DialogEspacio dialogEspacio;

    @SuppressWarnings("rawtypes")
    public ActionListenerCrear(FramePrincipal framePrincipal, DialogEspacio dialogEspacio, JComboBox cbx1, JComboBox cbx2) {
        this.framePrincipal = framePrincipal;
        this.dialogEspacio = dialogEspacio;
        this.cbx1 = cbx1;
        this.cbx2 = cbx2;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int alto = Integer.valueOf((String) cbx1.getSelectedItem());
        int ancho  = Integer.valueOf((String) cbx2.getSelectedItem());

        JLabel[][] recuadro = new JLabel[alto][ancho];

        int tamaño;
        if (alto > ancho) {
            tamaño = framePrincipal.getSIZE_PANEL() / alto;
        } else {
            tamaño = framePrincipal.getSIZE_PANEL() / ancho;
        }

        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                LabelRecuadro lbl = new LabelRecuadro(tamaño);
                lbl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                recuadro[i][j] = lbl;
            }
        }
        framePrincipal.crearEdicion(recuadro);
        dialogEspacio.dispose();
    }

}
