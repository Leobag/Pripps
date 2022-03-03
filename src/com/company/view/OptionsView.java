package com.company.view;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;

/**
 *
 */

public class OptionsView extends JFrame {

    JPanel optionsPanel;
    private boolean isMuted = false;


    public OptionsView(Clip clip) {
        super.setLayout(new GridLayout(1, 3, 30, 10));

        setSize(900, 700);
        setResizable(false);
        setLocationRelativeTo(null);

        optionsPanel = new JPanel();

        JButton muteButton = new JButton();
        JButton returnButton = new JButton();
        optionsPanel.setBackground(Color.BLACK);
        //optionsPanel.addMouseListener((MouseListener) this);
        optionsPanel.add(muteButton);
        optionsPanel.add(returnButton);
        muteButton.addActionListener((event) -> {
            if (isMuted) {
                clip.start();
                isMuted = false;
            } else {
                clip.stop();
                isMuted = true;
            }
        });
        muteButton.setActionCommand("muteButton");
        returnButton.addActionListener((event)->dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));

        returnButton.setActionCommand("optionsButton");
        muteButton.setIcon(new ImageIcon(getClass().getResource("/Images/Tiles/mute.png")));
        muteButton.setContentAreaFilled(false);
        muteButton.setBorder(BorderFactory.createEmptyBorder());
        returnButton.setIcon(new ImageIcon(getClass().getResource("/Images/Tiles/return.png")));
        returnButton.setContentAreaFilled(false);
        returnButton.setBorder(BorderFactory.createEmptyBorder());
        add(optionsPanel);
        setVisible(true);


    }


}
