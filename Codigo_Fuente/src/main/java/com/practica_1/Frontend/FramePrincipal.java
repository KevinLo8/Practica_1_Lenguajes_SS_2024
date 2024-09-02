package com.practica_1.Frontend;

import java.awt.*;

import javax.swing.*;

import com.practica_1.Backend.ActionListeners.*;
import com.practica_1.Backend.AutomataAnalizador.AutomataAnalizador;
import com.practica_1.Backend.Recuadro.Recuadro;
import com.practica_1.Frontend.JDialog.*;
import com.practica_1.Frontend.JLabel.PanelRecuadro;

public class FramePrincipal extends JFrame {

    private AutomataAnalizador analizador;
    private Thread hiloAnalizador;
    private PanelRecuadro[][] recuadro;

    private Recuadro[] tokens;

    private DialogEspacio dialogEspacio;
    private DialogSeleccionArchivo dialogSeleccionArchivo;
    private DialogBorrarTexto dialogBorrarTexto;
    private DialogReporte dialogReporte;
    private DialogInfo dialogInfo;
    private JPanel pnl1;
    private JScrollPane scp1;
    private JMenuItem jMI1, jMI2, jMI3, jMI4, jMI5;
    private JTextArea txa;

    //Se crea una constante con la dimension del la pantalla
    private final Dimension DIMENSION = Toolkit.getDefaultToolkit().getScreenSize();
    private final int SIZE = 700;
    private final int GAP = 50;
    private final int SIZE_PANEL = SIZE - GAP * 2;

    /**
     * Se crea el constructor del frame
     */
    public FramePrincipal(){

        tokens = new Recuadro[0];
        analizador = new AutomataAnalizador(this);
        hiloAnalizador = new Thread(analizador);

        initComponentes();

    }

    /**
     * Se inician los componentes del frame
     */
    private void initComponentes(){

        //Se configura el frame
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(((int)DIMENSION.getWidth() - SIZE * 2) / 2, ((int)DIMENSION.getHeight() - SIZE) / 2, SIZE * 2, SIZE);
        setTitle("Analizador Léxico");

        //Se inicializa la barra de menú y sus componentes
        JMenuBar jMenuBar = new JMenuBar();
        JMenu jM1 = new JMenu("Archivo");
        JMenu jM2 = new JMenu("Acciones");

        jMenuBar.add(jM1);
        jMenuBar.add(jM2);

        //Se generan los botones del menu
        jMI1 = new JMenuItem("Nueva edición");
        jMI2 = new JMenuItem("Cargar Archivo");
        jMI3 = new JMenuItem("Salir");
        jMI4 = new JMenuItem("Generar reporte");
        jMI5 = new JMenuItem("Generar imagen");

        //Se deshabilitan los botones requeridos
        jMI2.setEnabled(false);
        jMI4.setEnabled(false);

        //Se agregan los listeners a los botones
        jMI1.addActionListener(new ActionListenerNuevo(this)); 
        jMI2.addActionListener(new ActionListenerArchivo(this)); 
        jMI3.addActionListener(new ActionListenerSalir());
        jMI4.addActionListener(new ActionListenerReporte(this));
        jMI5.addActionListener(new ActionListenerImagen());
                
        //Se arma la barra de menú
        jM1.add(jMI1);
        jM1.add(jMI2);
        jM1.add(jMI3);
        jM2.add(jMI4);
        jM2.add(jMI5);

        //Se agrega la barra de menú al frame
        setJMenuBar(jMenuBar);

        //Se generan los paneles principales
        pnl1 = new JPanel();
        scp1 = new JScrollPane();

        //Se configuran los paneles
        pnl1.setPreferredSize(new Dimension(SIZE_PANEL, SIZE_PANEL));
        pnl1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        scp1.setPreferredSize(new Dimension(SIZE_PANEL, SIZE_PANEL));
        scp1.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //Se genera el orden en que ira todo
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addContainerGap(GAP, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(scp1)
                    .addComponent(pnl1)
                )
                .addContainerGap(GAP, Short.MAX_VALUE)
        );

        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER))
                .addContainerGap(GAP, Short.MAX_VALUE)
                .addComponent(scp1)
                .addGap(GAP * 2)
                .addComponent(pnl1)
                .addContainerGap(GAP, Short.MAX_VALUE)
        );

    }

    public int getSIZE_PANEL() {
        return SIZE_PANEL;
    }

    public JPanel[][] getRecuadro() {
        return recuadro;
    }

    public void preguntarTamaño() {
        cerrarDialogs();
        dialogEspacio = new DialogEspacio(this);
        dialogEspacio.setVisible(true);    
    }

    public void crearEdicion(PanelRecuadro[][] recuadro) {
        cerrarDialogs();

        this.recuadro = recuadro;
        pnl1.removeAll();

        jMI2.setEnabled(true);
        jMI4.setEnabled(true);

        txa = new JTextArea();
        txa.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        scp1.setViewportView(txa);
        pnl1.setLayout(new GridLayout(recuadro.length, recuadro[0].length));
        for (int i = 0; i < recuadro.length; i++) {
            for (int j = 0; j < recuadro[0].length; j++) {
                pnl1.add(recuadro[i][j]);
            }
        }

        analizador.setTxa(txa);
        if (!hiloAnalizador.isAlive()) {
            hiloAnalizador.start();
        } else {
        }
        

        repaint();
        validate();
    }

    public void pintarRecuadros(Recuadro[] tokens) {
        this.tokens = tokens;
        int numero = 0;

        for (int i = 0; i < recuadro.length; i++) {
            for (int j = 0; j < recuadro[0].length; j++) {
                numero = hayTokens(numero);
                if (numero != tokens.length) {
                    recuadro[i][j].setBackground(Color.decode(tokens[numero].getColor()));
                    recuadro[i][j].setRecuadro(tokens[numero]);
                } else {
                    recuadro[i][j].setBackground(Color.WHITE);
                    recuadro[i][j].setRecuadro(null);
                }
                numero++;
            }
        }
    }

    private int hayTokens(int numero) {
        for (int i = numero; i < tokens.length; i++) {
            if (tokens[i].getToken() != null) {
                return numero;
            }
        }
        return tokens.length;
    }

    public void preguntarArchivo() {
        cerrarDialogs();
        dialogSeleccionArchivo = new DialogSeleccionArchivo(this);
        dialogSeleccionArchivo.setVisible(true);    
    }

    public void preguntarBorrar(String texto) {
        cerrarDialogs();
        dialogBorrarTexto = new DialogBorrarTexto(this, texto);
        dialogBorrarTexto.setVisible(true);
    }

    public void borrarTextArea(String texto, String opcion) {
        String textoTotal = null;
        switch (opcion) {
            case "Si":
                textoTotal = texto;
                break;
            case "No":
                textoTotal = txa.getText() + texto;
                break;
        }

        txa.setText(textoTotal);
        cerrarDialogs();
    }

    public void cerrarDialogs() {
        if (dialogSeleccionArchivo != null) {
            dialogSeleccionArchivo.dispose();    
        }
        if (dialogEspacio != null) {
            dialogEspacio.dispose();    
        }
        if (dialogBorrarTexto != null) {
            dialogBorrarTexto.dispose();    
        }
        if (dialogReporte != null) {
            dialogReporte.dispose();    
        }
        if (dialogInfo != null) {
            dialogInfo.dispose();    
        }
    }

    public void mostrarReporte() {
        cerrarDialogs();
        dialogReporte = new DialogReporte(this, tokens);
        dialogReporte.setVisible(true);    
    }

    public void generarInfo(Recuadro token) {
        cerrarDialogs();
        dialogInfo = new DialogInfo(this, token);
        dialogInfo.setVisible(true);    
    }
}
