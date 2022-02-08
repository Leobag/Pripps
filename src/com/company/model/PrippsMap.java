package com.company.model;

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class PrippsMap extends JPanel {

    public PrippsMap(){

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 5, 650);
        g.fillRect(0,0,1160,5);
        g.fillRect(1160,0,5,650);
        g.fillRect(0, 650, 1165, 5);
        g.fillRect(350, 400, 5, 250);
        g.fillRect(350, 400, 140, 5);
        repaint();
    }
}