package com.company.controller;

import com.company.model.PrippsMap;


import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.URL;


public class PrippsController extends JFrame implements MouseListener, ActionListener {

    private PrippsMap map;
    private JPanel mainPanel;
    private final JButton playButton = new JButton("Play");
    private final JButton optionsButton = new JButton("Options");
    private final JButton quitButton = new JButton("Quit");
    private final JLabel header = new JLabel("Pripps Maze Game", SwingConstants.CENTER);



    public static void main(String[] args) {
        PrippsController f = new PrippsController();
    }

    private PrippsController(){
        setLayout();
    }

    private void setLayout(){
        super.setLayout(new GridLayout(1, 3, 10, 10));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1180, 695);
        setLocation(50, 50);
        header.setFont(new Font("Comic Sans MS", Font.PLAIN, 28));


        //super.setPreferredSize(new java.awt.Dimension(600, 600));
        mainPanel = new JPanel(new GridLayout(4,1,10,10));
        add(mainPanel);
        mainPanel.addMouseListener(this);
        mainPanel.add(header);
        mainPanel.add(playButton);
        mainPanel.add(optionsButton);
        mainPanel.add(quitButton);
        playButton.addActionListener(this);
        playButton.setActionCommand("playButton");
        quitButton.addActionListener(this);
        quitButton.setActionCommand("quitButton");
        optionsButton.addActionListener(this);
        optionsButton.setActionCommand("optionsButton");
        playButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        optionsButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        quitButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));



        setVisible(true);
        musicPlayer();


    }

    public void musicPlayer() {
        URL lol = getClass().getResource("menumusic.wav");

        try{
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(lol));
            clip.start();
        } catch (Exception e){
            e.printStackTrace();
        }
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
            Container contentPane = getContentPane();
            contentPane.removeAll();
            map = new PrippsMap();
            contentPane.add(map);
            repaint();
            setVisible(true);
        }
        if(e.getActionCommand().equals("quitButton")){
            System.exit(0);
        }
    }
}
