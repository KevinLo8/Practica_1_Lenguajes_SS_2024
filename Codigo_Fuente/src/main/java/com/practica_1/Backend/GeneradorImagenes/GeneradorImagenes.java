package com.practica_1.Backend.GeneradorImagenes;

import guru.nidi.graphviz.model.*;
import static guru.nidi.graphviz.model.Factory.*;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;

public class GeneradorImagenes {

    public void generarImagenGrafica(String lexema) {
        Graph g = graph().directed();
        for (int i = 0; i < lexema.length() - 1; i++) {
            String char1 = "" + lexema.charAt(i);
            String char2 = "" + lexema.charAt(i + 1);

            g.with(node(char1).link(node(char2)));

        }
        
        Graphviz.fromGraph(g).render(Format.PNG).toImage();
    }
}
