package com.practica_1.Frontend.JDialog;

import javax.swing.*;

import com.practica_1.Backend.ActionListeners.*;
import com.practica_1.Frontend.FramePrincipal;

public class DialogSeleccionArchivo extends JDialog {

    FramePrincipal frame;

    public DialogSeleccionArchivo(FramePrincipal frame) {
        super(frame);
        this.frame = frame;

        initComponets();

    }

    private void initComponets() {

        JTextField txf1 = new JTextField();

        JButton btn1 = new JButton("Seleccionar");
        JButton btn2 = new JButton("cargar");

        String[] numeros = new String[19];

        for (int i = 2; i < 21; i++) {
            numeros[i - 2] = String.valueOf(i);
        }

        btn1.addActionListener(new ActionListenerSeleccionar(frame, txf1));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txf1)
                        .addComponent(btn1)
                    )
                    .addComponent(btn2)
                )
                .addContainerGap(10, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(txf1)
                    .addComponent(btn1)
                )
                .addGap(10)
                .addComponent(btn2)
                .addContainerGap(10, Short.MAX_VALUE)
        );

        pack();

        int pos_X = frame.getLocationOnScreen().x + (frame.getWidth() - getWidth()) / 2;
        int pos_Y = frame.getLocationOnScreen().y + (frame.getHeight() - getHeight()) / 2;
        setLocation(pos_X, pos_Y);

    }
}
