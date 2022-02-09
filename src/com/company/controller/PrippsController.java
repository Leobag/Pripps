package com.company.controller;

import com.company.model.*;
import com.company.model.PrippsModel;
import com.company.view.PrippsView;


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

    private JPanel mainPanel;
    private final JButton playButton = new JButton();
    private final JButton optionsButton = new JButton();
    private final JButton quitButton = new JButton();
    private final JLabel header = new JLabel("Pripps Maze Game", SwingConstants.CENTER);

    PrippsView view;
    PrippsModel model;



    public static void main(String[] args) {
        PrippsController f = new PrippsController();
    }

    private PrippsController(){
        setLayout();
        model = new PrippsModel();
        view = new PrippsView(model);
    }

    private void setLayout(){
        super.setLayout(new GridLayout(1, 3, 10, 10));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700); //1360, 807 fungerar bra efter mapen men den använder pack() istället
        setResizable(false);
        setLocationRelativeTo(null);
        header.setFont(new Font("Comic Sans MS", Font.PLAIN, 28));


        //super.setPreferredSize(new java.awt.Dimension(600, 600));
        mainPanel = new JPanel(new GridLayout(4,1,10,10));
        add(mainPanel);
        mainPanel.setBackground(Color.BLACK);
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

        try{
            playButton.setIcon(new ImageIcon(getClass().getResource("/Images/PlayButton.png")));
            playButton.setContentAreaFilled(false);
            playButton.setBorder(BorderFactory.createEmptyBorder());
            optionsButton.setIcon(new ImageIcon(getClass().getResource("/Images/OptionsButton.png")));
            optionsButton.setContentAreaFilled(false);
            optionsButton.setBorder(BorderFactory.createEmptyBorder());
            quitButton.setIcon(new ImageIcon(getClass().getResource("/Images/QuitButton.png")));
            quitButton.setContentAreaFilled(false);
            quitButton.setBorder(BorderFactory.createEmptyBorder());
        }
        catch (Exception e){
            System.out.println("Image not found");
        }

        setVisible(true);
        //musicPlayer();


    }

    public void musicPlayer() {
        URL lol = getClass().getResource("/Music/menumusic.wav");

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
            contentPane.add(view);
            pack();
            setLocationRelativeTo(null);
            repaint();
            setVisible(true);
        }
        if(e.getActionCommand().equals("quitButton")){
            System.exit(0);
        }
    }
}
