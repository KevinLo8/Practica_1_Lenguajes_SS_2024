package com.practica_1.Frontend.JDialog;

import java.awt.Dimension;

import javax.swing.*;

import com.practica_1.Backend.ActionListeners.*;
import com.practica_1.Backend.Recuadro.Recuadro;
import com.practica_1.Frontend.FramePrincipal;

public class DialogReporte extends JDialog {

    FramePrincipal frame;
    private Recuadro[] tokens;

    public DialogReporte(FramePrincipal frame, Recuadro[] tokens) {
        super(frame);
        this.frame = frame;
        this.tokens = tokens;

        initComponets();

    }

    private void initComponets() {

        JScrollPane scp1 = new JScrollPane();
        scp1.setPreferredSize(new Dimension(800, 400));

        String[] titulos = {"Token","Lexema","Linea","Columna","Cuadro"};

        String[][] data = new String[0][5];
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].getToken() != null) {
                data = agregarData(tokens[i], data);
            }
        }

        JTable tbl1 = new JTable(data, titulos);

        scp1.setViewportView(tbl1);

        JButton btn1 = new JButton("Cerrar");

        btn1.addActionListener(new ActionListenerCerrar(frame));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(scp1)
                    .addComponent(btn1)    
                )
                .addContainerGap(20, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(scp1)
                .addGap(20)
                .addComponent(btn1)
                .addContainerGap(20, Short.MAX_VALUE)
        );

        pack();

        int pos_X = frame.getLocationOnScreen().x + (frame.getWidth() - getWidth()) / 2;
        int pos_Y = frame.getLocationOnScreen().y + (frame.getHeight() - getHeight()) / 2;
        setLocation(pos_X, pos_Y);

    }

    private String[][] agregarData(Recuadro token, String[][] data) {
        String[][] retorno = new String[data.length + 1][5];

        for (int i = 0; i < data.length; i++) {
            retorno[i][0] =  data[i][0];
            retorno[i][1] =  data[i][1];
            retorno[i][2] =  data[i][2];
            retorno[i][3] =  data[i][3];
            retorno[i][4] =  data[i][4];
        }

        retorno[data.length][0] = token.getToken();
        retorno[data.length][1] = token.getLexema();
        retorno[data.length][2] = String.valueOf(token.getLinea());
        retorno[data.length][3] = String.valueOf(token.getColumna());
        retorno[data.length][4] = token.getColor();

        return retorno;
    }
}
