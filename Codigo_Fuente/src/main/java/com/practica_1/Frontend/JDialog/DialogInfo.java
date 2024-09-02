package com.practica_1.Frontend.JDialog;

import java.awt.Font;

import javax.swing.*;

import com.practica_1.Backend.ActionListeners.*;
import com.practica_1.Backend.GeneradorImagenes.GeneradorImagenes;
import com.practica_1.Backend.Recuadro.Recuadro;
import com.practica_1.Frontend.FramePrincipal;

public class DialogInfo extends JDialog {

    FramePrincipal frame;

    public DialogInfo(FramePrincipal frame, Recuadro recuadro) {
        super(frame);
        this.frame = frame;

        initComponets(recuadro);

    }

    private void initComponets(Recuadro recuadro) {

        JLabel lbl1 = new JLabel(recuadro.getToken());
        JLabel lbl2 = new JLabel("Fila:" + recuadro.getLinea() + ", Columna:" + recuadro.getColumna());
        JLabel lbl3 = new JLabel();

        lbl1.setFont(new Font(lbl1.getName(), Font.PLAIN, 30));

        JButton btn1 = new JButton("Cerrar");

        btn1.addActionListener(new ActionListenerCerrar(frame));

        GeneradorImagenes generador = new GeneradorImagenes();
        ImageIcon imagen = generador.generarImagenGrafica(recuadro.getLexema());

        lbl3.setIcon(imagen);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(lbl1)
                    .addComponent(lbl2)
                    .addComponent(lbl3)
                    .addComponent(btn1)
                )
                .addContainerGap(20, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(lbl1)
                .addGap(5)
                .addComponent(lbl2)
                .addGap(20)
                .addComponent(lbl3)
                .addGap(20)
                .addComponent(btn1)
                .addContainerGap(20, Short.MAX_VALUE)
        );

        pack();

        int pos_X = frame.getLocationOnScreen().x + (frame.getWidth() - getWidth()) / 2;
        int pos_Y = frame.getLocationOnScreen().y + (frame.getHeight() - getHeight()) / 2;
        setLocation(pos_X, pos_Y);

    }
}
