package com.company.view;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

/**
 * A class that creates a frame when "OPTIONS" is pressed on the starting menu.
 *
 * @author Sebastian Sela
 */
public class OptionsView extends JFrame {

    JPanel optionsPanel;
    JButton returnButton;
    private boolean isMuted = false;


    /**
     * Class constructor
     *
     * @param clip Taking in clip with a sound file for the game menu.
     */
    public OptionsView(Clip clip) {
        super.setLayout(new GridLayout(1, 3, 30, 10));

        setSize(900, 700);
        setResizable(false);
        setLocationRelativeTo(null);

        optionsPanel = new JPanel();

        JButton muteButton = new JButton();
        returnButton = new JButton();
        optionsPanel.setBackground(Color.BLACK);
        optionsPanel.add(muteButton);
        optionsPanel.add(returnButton);
        muteButton.addActionListener((event) -> {
            if (isMuted) {
                clip.start();
                isMuted = false;
            } else {
                if (clip != null) {
                    clip.stop();

                }
                isMuted = true;
            }
        });
        muteButton.setActionCommand("muteButton");
        returnButton.addActionListener((event) -> dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));


        returnButton.setActionCommand("returnButton");
        muteButton.setIcon(new ImageIcon(getClass().getResource("/Images/Tiles/mute.png")));
        muteButton.setContentAreaFilled(false);
        muteButton.setBorder(BorderFactory.createEmptyBorder());
        returnButton.setIcon(new ImageIcon(getClass().getResource("/Images/Tiles/return.png")));
        returnButton.setContentAreaFilled(false);
        returnButton.setBorder(BorderFactory.createEmptyBorder());
        add(optionsPanel);
        setVisible(true);


    }

    public boolean isMuted() {
        return isMuted;
    }

    public JButton getReturnButton() {
        return returnButton;
    }
}
