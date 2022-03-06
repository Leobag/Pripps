package com.company.view;

import javax.swing.*;
import java.awt.*;

/**
 * Creates the view, which is a JPanel displayed when the user has won the game.
 * Used to acquire the name of the player, so it may be added to the server.
 *
 * @author Emil Berzelius
 * @version 06-03-22
 */
public class WinView extends JPanel {

    JButton submit;
    JTextField name;


    public WinView() {

        setSize(900, 700);
        setLayout(new GridLayout(3, 1, 10, 10));
        JLabel winMessage = new JLabel("Congratz to your pripps, you won the game");
        JLabel namePlease = new JLabel("Please enter your name to add your time");
        name = new JTextField(20);
        submit = new JButton("Submit");
        submit.setActionCommand("submit");
        setBackground(Color.GREEN);
        add(winMessage);
        add(namePlease);
        add(name);
        add(submit);


    }

    public JButton getSubmit() {
        return submit;
    }

    public JTextField getSubmittedName() {
        return name;
    }
}
