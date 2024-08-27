package com.practica_1.Frontend;

import java.awt.*;

import javax.swing.*;

import com.practica_1.Backend.ActionListeners.ActionListenerImagen;
import com.practica_1.Backend.ActionListeners.ActionListenerNuevo;
import com.practica_1.Backend.ActionListeners.ActionListenerReporte;
import com.practica_1.Backend.ActionListeners.ActionListenerSalir;
import com.practica_1.Backend.AutomataAnalizador.AutomataAnalizador;
import com.practica_1.Backend.Recuadro.Recuadro;
import com.practica_1.Frontend.JDialog.DialogEspacio;

public class FramePrincipal extends JFrame {

    private AutomataAnalizador analizador;
    private Thread hiloAnalizador;
    private JLabel[][] recuadro;

    private Recuadro[] token;

    private DialogEspacio dialogEspacio;
    private JPanel pnl1, pnl2;

    //Se crea una constante con la dimension del la pantalla
    private final Dimension DIMENSION = Toolkit.getDefaultToolkit().getScreenSize();
    private final int SIZE = 700;
    private final int GAP = 50;
    private final int SIZE_PANEL = SIZE - GAP * 2;

    /**
     * Se crea el constructor del frame
     */
    public FramePrincipal(){

        token = new Recuadro[0];
        analizador = new AutomataAnalizador(this, token);
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
        JMenuItem jMI1 = new JMenuItem("Nueva edición");
        JMenuItem jMI2 = new JMenuItem("Salir");
        JMenuItem jMI3 = new JMenuItem("Generar reporte");
        JMenuItem jMI4 = new JMenuItem("Generar imagen");

        //Se agregan los listeners a los botones
        jMI1.addActionListener(new ActionListenerNuevo(this)); 
        jMI2.addActionListener(new ActionListenerSalir());
        jMI3.addActionListener(new ActionListenerReporte());
        jMI4.addActionListener(new ActionListenerImagen());
                
        //Se arma la barra de menú
        jM1.add(jMI1);
        jM1.add(jMI2);
        jM2.add(jMI3);
        jM2.add(jMI4);

        //Se agrega la barra de menú al frame
        setJMenuBar(jMenuBar);

        //Se generan los paneles principales
        pnl1 = new JPanel();
        pnl2 = new JPanel();

        //Se configuran los paneles
        pnl1.setPreferredSize(new Dimension(SIZE_PANEL, SIZE_PANEL));
        pnl2.setPreferredSize(new Dimension(SIZE_PANEL, SIZE_PANEL));

        //Se genera el orden en que ira todo
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addContainerGap(GAP, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(pnl1)
                    .addComponent(pnl2)
                )
                .addContainerGap(GAP, Short.MAX_VALUE)
        );

        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER))
                .addContainerGap(GAP, Short.MAX_VALUE)
                .addComponent(pnl1)
                .addGap(GAP * 2)
                .addComponent(pnl2)
                .addContainerGap(GAP, Short.MAX_VALUE)
        );

    }

    public int getSIZE_PANEL() {
        return SIZE_PANEL;
    }

    public JLabel[][] getRecuadro() {
        return recuadro;
    }

    public void setToken(Recuadro[] recuadros) {
        this.token = recuadros;
    }

    public void preguntarTamaño() {
        if (dialogEspacio != null) {
            dialogEspacio.dispose();    
        }
        dialogEspacio = new DialogEspacio(this);
        dialogEspacio.setVisible(true);    
    }

    public void crearEdicion(JLabel[][] recuadro) {
        this.recuadro = recuadro;
        pnl1.removeAll();
        pnl2.removeAll();
        JTextArea txa = new JTextArea();
        txa.setPreferredSize(new Dimension(SIZE_PANEL - 6, SIZE_PANEL - 6));
        txa.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pnl1.add(txa);
        pnl2.setLayout(new GridLayout(recuadro.length, recuadro[0].length));
        for (int i = 0; i < recuadro.length; i++) {
            for (int j = 0; j < recuadro[0].length; j++) {
                pnl2.add(recuadro[i][j]);

            }
        }

        analizador.setTxa(txa);
        hiloAnalizador.start();

        repaint();
        validate();
    }

    public void imprimirTamaño(){
        System.out.println(token.length);
    }

    public void pintarRecuadros(Recuadro[] token2) {

    }

}
