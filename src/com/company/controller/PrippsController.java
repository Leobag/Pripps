package com.company.controller;

import com.company.model.PrippsModel;
import com.company.view.PrippsView;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;


public class PrippsController extends JFrame implements MouseListener, ActionListener, KeyListener {

    private JPanel mainPanel;
    private final JButton playButton = new JButton();
    private final JButton optionsButton = new JButton();
    private final JButton quitButton = new JButton();
    private final JLabel header = new JLabel("Pripps Maze Game", SwingConstants.CENTER);

    PrippsView view;
    PrippsModel model;

    private double inputUp;
    private double inputDown;
    private double inputLeft;
    private double inputRight;
    private boolean gamePaused = false;
    private Clip clip;
    private long previousTimeMillis;
    private long currentTimeMillis;


    public static void main(String[] args) {
        PrippsController f = new PrippsController();
    }

    private PrippsController() {
        model = new PrippsModel();
        view = new PrippsView(model);
        setLayout();
    }

    private void setLayout() {
        super.setLayout(new GridLayout(1, 3, 10, 10));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700); //1360, 807 fungerar bra efter mapen men den använder pack() istället
        setResizable(false);
        setLocationRelativeTo(null);
        header.setFont(new Font("Comic Sans MS", Font.PLAIN, 28));


        //super.setPreferredSize(new java.awt.Dimension(600, 600));
        mainPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        add(mainPanel);
        mainPanel.setBackground(Color.BLACK);
        mainPanel.addMouseListener(this);
        addKeyListener(this);
        setFocusable(true);
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

        playButton.setIcon(new ImageIcon(getClass().getResource("/Images/Tiles/PlayButton.png")));
        playButton.setContentAreaFilled(false);
        playButton.setBorder(BorderFactory.createEmptyBorder());
        optionsButton.setIcon(new ImageIcon(getClass().getResource("/Images/Tiles/OptionsButton.png")));
        optionsButton.setContentAreaFilled(false);
        optionsButton.setBorder(BorderFactory.createEmptyBorder());
        quitButton.setIcon(new ImageIcon(getClass().getResource("/Images/Tiles/QuitButton.png")));
        quitButton.setContentAreaFilled(false);
        quitButton.setBorder(BorderFactory.createEmptyBorder());

        setVisible(true);
        musicPlayer();
    }

    public void musicPlayer() {
        URL lol = getClass().getResource("/Music/menumusic.wav");

        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(lol));
            clip.start();

        } catch (Exception e) {
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
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {

            case KeyEvent.VK_W -> inputUp = 1;
            case KeyEvent.VK_A -> inputLeft = 1;
            case KeyEvent.VK_S -> inputDown = 1;
            case KeyEvent.VK_D -> inputRight = 1;
            case KeyEvent.VK_ESCAPE -> gamePaused = !gamePaused;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> inputUp = 0;
            case KeyEvent.VK_S -> inputDown = 0;
            case KeyEvent.VK_A -> inputLeft = 0;
            case KeyEvent.VK_D -> inputRight = 0;
        }
    }

    /**
     * Game loop after playbutton is pressed. Loop reads inputs, updates player and repaints
     * - Max Yoorkevich
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("playButton")) {
            clip.stop();
            Container contentPane = getContentPane();
            contentPane.removeAll();
            contentPane.add(view);
            pack();
            setLocationRelativeTo(null);
            setVisible(true);
            model.startGame();

            startGameLoop();
        }
        if (e.getActionCommand().equals("quitButton")) {
            System.exit(0);
        } if(e.getActionCommand().equals("submit")){
            model.setSubmittedName(view.getWinView().getSubmittedName().getText());
        }
    }

    private void openWinPanel(JPanel panel){
        Container contentPane = getContentPane();
        contentPane.removeAll();
        contentPane.add(panel);
        view.getWinView().getSubmit().addActionListener(this);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * sets direction for player.
     * the direction angle is calculated for degrees in Game (movePlayer). New thread had to be made
     * since main thread used all resources for loop, thus blocking draw functionality
     * - Max Yoorkevich
     */
    private void handleInput() {
        var inputX = inputRight - inputLeft;
        var inputY = inputDown - inputUp;
        if (inputX == 0 && inputY == 0) {
            model.setInputDirection(null);
        } else {
            model.setInputDirection(Math.atan2(inputY, inputX));
        }
    }
    private void startGameLoop(){
        new Thread(() -> {
            previousTimeMillis = System.currentTimeMillis();
            while (true) {
                currentTimeMillis = System.currentTimeMillis();
                handleInput();
                if (!gamePaused) {
                    model.update((currentTimeMillis - previousTimeMillis) / 1000d);
                }
                repaint();
                previousTimeMillis = currentTimeMillis;
                if(model.winCondition()){
                    openWinPanel(view.getWinView());
                    break;
                }
            }
        }).start();
    }
}
