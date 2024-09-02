package com.practica_1.Backend.GeneradorImagenes;

import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;

import java.awt.image.*;

import guru.nidi.graphviz.model.*;

import javax.swing.*;

import guru.nidi.graphviz.attribute.*;
import guru.nidi.graphviz.attribute.Rank.RankDir;
import guru.nidi.graphviz.engine.*;

public class GeneradorImagenes {

    public ImageIcon generarImagenGrafica(String lexema) {
        Graph g = graph("example1").directed()
                .graphAttr().with(Rank.dir(RankDir.LEFT_TO_RIGHT))
                .nodeAttr().with(Font.name("arial"))
                .linkAttr().with("class", "link-class")
                .with(node(lexema));
        
        BufferedImage bufferedImage = Graphviz.fromGraph(g).scale(2).render(Format.PNG).toImage();
        ImageIcon imageIcon = new ImageIcon(bufferedImage);

        return imageIcon;
    }
}
