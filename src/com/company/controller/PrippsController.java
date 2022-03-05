package com.company.controller;

import com.company.model.PrippsModel;
import com.company.view.OptionsView;
import com.company.view.PrippsView;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;


public class PrippsController extends JFrame implements MouseListener, ActionListener, KeyListener {

    private JPanel mainPanel;
    private final JButton playButton = new JButton();
    private final JButton optionsButton = new JButton();
    private final JButton quitButton = new JButton();
    private final JLabel header = new JLabel("Pripps Maze Game", SwingConstants.CENTER);

    PrippsView view;
    PrippsModel model;
    SaveData saveData;
    OptionsView optionsView;


    private double inputUp;
    private double inputDown;
    private double inputLeft;
    private double inputRight;
    private boolean gamePaused = false;
    private Clip menuClip;
    private Clip gameClip;
    private long previousTimeMillis;
    private long currentTimeMillis;


    public static void main(String[] args) {
        PrippsController f = new PrippsController();
    }

    PrippsController() {
        model = new PrippsModel();
        view = new PrippsView(model);
        setLayout();
    }

    /**
     * Sets the initial Layout of the game menu and sets listeners on all the buttons of the game.
     * This is done through creating the JPanel mainPanel and attaching the buttons to it.
     * The mainPanel is then reused if the player dies or completes the game.
     * - Emil Berzelius
     */
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

        playButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/Images/Tiles/PlayButton.png"))));
        playButton.setContentAreaFilled(false);
        playButton.setBorder(BorderFactory.createEmptyBorder());
        optionsButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/Images/Tiles/OptionsButton.png"))));
        optionsButton.setContentAreaFilled(false);
        optionsButton.setBorder(BorderFactory.createEmptyBorder());
        quitButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/Images/Tiles/QuitButton.png"))));
        quitButton.setContentAreaFilled(false);
        quitButton.setBorder(BorderFactory.createEmptyBorder());
        view.getWinView().getSubmit().addActionListener(this);

        setVisible(true);
        musicPlayer();
    }

    /** A method for playing the game menu music, using URL and Clip.
     *
     * @author Sebastian Sela
     */
    public void musicPlayer() {

        try {
            menuClip = AudioSystem.getClip();
            menuClip.open(AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getResource("/Music/putin.wav"))));
            menuClip.start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException audioException) {
            audioException.printStackTrace();
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

    /**
     * Listens for a key to be pressed and sets input variables to calculate
     * movement directions with radians.
     *
     * Uses Key ESCAPE to pause game, K to save and L to load a saved game.
     *
     * @param e - the KeyEvent detected.
     */
    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {

            case KeyEvent.VK_W -> inputUp = 1;
            case KeyEvent.VK_A -> inputLeft = 1;
            case KeyEvent.VK_S -> inputDown = 1;
            case KeyEvent.VK_D -> inputRight = 1;
            case KeyEvent.VK_ESCAPE -> gamePaused = !gamePaused;
            case KeyEvent.VK_K -> saveGame();
            case KeyEvent.VK_L -> loadGame();
        }
    }

    /**
     * Sets input variables again to calculate movement direction.
     * @param e - KeyEvent detected.
     */
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
     * Discovers if buttons in our starting menu are pressed.
     * If the playButton is pressed the current JPanel is removed and our game View
     * is added to the JFrame instead as well as starting the game loop.
     *
     * The submit JButton is used to submit a name when completing the game.
     * When communicated to the server the mainPanel is placed upon the JFrame again.
     *
     * - Max Yoorkevich
     * - Emil Berzelius
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("playButton")) {
            Container contentPane = getContentPane();
            contentPane.removeAll();
            contentPane.add(view);
            pack();
            setLocationRelativeTo(null);
            setVisible(true);
            model.startGameTimer();
            model.startGame();

            startGameLoop();
        }
        if (e.getActionCommand().equals("quitButton")) {
            System.exit(0);
        }
        if (e.getActionCommand().equals("submit")) {
            model.enterHighScore(view.getWinView().getSubmittedName().getText());
            openStartPanel();
        }
        if (e.getActionCommand().equals("optionsButton")) {
            optionsView = new OptionsView(menuClip);
        }
    }

    /**
     * Opens the starting panel, mainPanel.
     */
    private void openStartPanel() {
        model.resetGame();
        getContentPane().removeAll();
        setSize(900, 700); //1360, 807 fungerar bra efter mapen men den använder pack() istället
        getContentPane().add(mainPanel);
        setLocationRelativeTo(null);

    }

    /**
     * Sets the winning panel on the JFrame.
     * @param panel
     */
    private void openWinPanel(JPanel panel){
        getContentPane().removeAll();
        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * repaints the player a final time, setting the player image instead to an explosion.
     * Then pauses the thread for one second so the user may see the high quality graphics.
     */
    private void displayExplosion() {
        model.getPlayer().setMovementImages();
        repaint();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("i cant sleep");
        }


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

    /**
     * Uses save method to store current information from the game.
     */
    public void saveGame(){
        saveData = new SaveData(model.getPlayer().getPosition(), model.getMap().getMapCounter(), model.getPosArray());
        try {
            ResourceManager.save(saveData, "data.txt");
            System.out.println("Game successfully saved");
            System.out.println(Arrays.toString(saveData.getEnemyPosition()));
        } catch (IOException | NullPointerException saveException) {
            System.out.println("Couldn't save: " + saveException.getMessage());
        }
    }

    /**
     * Uses load method to get information from file and updates the game.
     */
    public void loadGame(){
        try {
            saveData = (SaveData) ResourceManager.load("data.txt");
            model.getPlayer().setPosition(saveData.getPosition().getX(), saveData.getPosition().getY());
            model.getMap().setMapCounter(saveData.getMapCounter());
            model.getMap().loadMap(model.getCurrentMap());
            model.getPlayer().updateHitBox((int)(saveData.getPosition().getX() * 32), (int)(saveData.getPosition().getY() * 32), 16);
            model.setPosArray(saveData.getEnemyPosition());
            System.out.println("Game successfully loaded");
        } catch (IOException | ClassNotFoundException | NullPointerException loadException) {
            System.out.println("Couldn't load: " + loadException.getMessage());
        }
    }

    private void startGameLoop() {
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
                if (model.winCondition()) {
                    model.stopGameTimer();
                    openWinPanel(view.getWinView());
                    break;
                } else if (model.getPlayer().getDead()) {
                    displayExplosion();
                    openStartPanel();
                    break;
                }

            }
        }).start();
    }
}
