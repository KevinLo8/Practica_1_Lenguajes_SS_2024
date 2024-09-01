package com.practica_1.Frontend.JDialog;

import javax.swing.*;

import com.practica_1.Backend.ActionListeners.ActionListenerCrear;
import com.practica_1.Frontend.FramePrincipal;

public class DialogEspacio extends JDialog {

    FramePrincipal frame;

    public DialogEspacio(FramePrincipal frame) {
        super(frame);
        this.frame = frame;

        initComponets();

    }

    @SuppressWarnings("rawtypes")
    private void initComponets() {

        JLabel lbl1 = new JLabel("Seleccione el alto de cuadro");
        JLabel lbl2 = new JLabel("Seleccione el ancho de cuadro");

        JButton btn1 = new JButton("Crear");

        String[] numeros = new String[19];

        for (int i = 2; i < 21; i++) {
            numeros[i - 2] = String.valueOf(i);
        }

        JComboBox cbx1 = new JComboBox<String>(numeros);
        JComboBox cbx2 = new JComboBox<String>(numeros);

        btn1.addActionListener(new ActionListenerCrear(frame, Integer.valueOf((String)cbx1.getSelectedItem()), Integer.valueOf((String) cbx2.getSelectedItem())));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(lbl1)
                    .addComponent(cbx1)
                    .addComponent(lbl2)
                    .addComponent(cbx2)
                    .addComponent(btn1)
                )
                .addContainerGap(10, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addComponent(lbl1)
                .addGap(10)
                .addComponent(cbx1)
                .addGap(10)
                .addComponent(lbl2)
                .addGap(10)
                .addComponent(cbx2)
                .addGap(10)
                .addComponent(btn1)
                .addContainerGap(10, Short.MAX_VALUE)
        );

        pack();

        int pos_X = frame.getLocationOnScreen().x + (frame.getWidth() - getWidth()) / 2;
        int pos_Y = frame.getLocationOnScreen().y + (frame.getHeight() - getHeight()) / 2;
        setLocation(pos_X, pos_Y);

    }
}
