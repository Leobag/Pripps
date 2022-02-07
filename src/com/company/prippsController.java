package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class prippsController extends JFrame implements MouseListener, ActionListener {

    private prippsMap map;
    private JPanel mainPanel;
    private final JButton playButton = new JButton("Play");
    private final JButton optionsButton = new JButton("Options");
    private final JButton quitButton = new JButton("Quit");


    public static void main(String[] args) {
        prippsController f = new prippsController();
    }

    private prippsController(){
        setLayout();
    }

    private void setLayout(){
        super.setLayout(new GridLayout(1, 3, 10, 10));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(1180, 695);
        setLocation(50, 50);


        //super.setPreferredSize(new java.awt.Dimension(600, 600));
        mainPanel = new JPanel(new GridLayout(3,1,10,10));
        add(mainPanel);
        mainPanel.addMouseListener(this);
        mainPanel.add(playButton);
        mainPanel.add(optionsButton);
        mainPanel.add(quitButton);
        playButton.addActionListener(this);
        playButton.setActionCommand("playButton");
        quitButton.addActionListener(this);
        quitButton.setActionCommand("quitButton");
        optionsButton.addActionListener(this);
        optionsButton.setActionCommand("optionsButton");





        setVisible(true);


    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("playButton")){
            System.out.println("okay");
            Container contentPane = getContentPane();
            contentPane.removeAll();
            map = new prippsMap();
            contentPane.add(map);
            repaint();
            setVisible(true);
        }
        if(e.getActionCommand().equals("quitButton")){
            System.exit(0);
        }
    }
}
