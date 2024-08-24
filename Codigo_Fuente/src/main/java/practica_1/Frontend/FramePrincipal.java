package practica_1.Frontend;

import java.awt.*;

import javax.swing.*;

import practica_1.Backend.ActionListeners.ActionListenerImagen;
import practica_1.Backend.ActionListeners.ActionListenerNuevo;
import practica_1.Backend.ActionListeners.ActionListenerReporte;
import practica_1.Backend.ActionListeners.ActionListenerSalir;

public class FramePrincipal extends JFrame {

    //Se crea una constante con la dimension del la pantalla
    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private int size = 700;

    /**
     * Se crea el constructor del frame
     */
    public FramePrincipal(){

        initComponentes();

    }

    /**
     * Se inician los componentes del frame
     */
    private void initComponentes(){

        //Se configura el frame
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(((int)dim.getWidth() - size * 2) / 2, ((int)dim.getHeight() - size) / 2, size * 2, size);
        setTitle("Registro de Trajetas");

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
        jMI1.addActionListener(new ActionListenerNuevo()); 
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

    }

}
