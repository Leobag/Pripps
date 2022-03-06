package com.company.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

/**
 * Communicates with server and displays the top 10 highscores.
 *
 * @author Emil Berzelius
 */
public class HighscoreView extends JFrame {

    private PrippsView view;
    private String[][] highScores;
    private JLabel[] labels;
    JPanel highScorePanel;
    JButton returnButton2;


    public HighscoreView(PrippsView view){

        this.view = view;
        setSize(new Dimension(900, 700));

        setLocationRelativeTo(null);

        highScorePanel = new JPanel();
        highScorePanel.setSize(900,700);
        highScorePanel.setLayout(new GridLayout(11,1,4,4));

        labels = new JLabel[10];
        returnButton2 = new JButton();
        returnButton2.setIcon(new ImageIcon(getClass().getResource("/Images/Tiles/return.png")));
        returnButton2.setContentAreaFilled(false);
        returnButton2.setBorder(BorderFactory.createEmptyBorder());
        returnButton2.addActionListener((event) -> dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));
        highScorePanel.setBackground(Color.ORANGE);
        highScorePanel.add(returnButton2);

        add(highScorePanel);


        this.setHighscores();
        this.setLabels();



        setVisible(true);

    }

    /**
     * Retreives the actual highscore-list from the server, taken from the
     * Client class in model.
     *
     * - Leonard Bagiu
     */

    public void setHighscores(){
    this.highScores = view.model.getHighScoreList();
    }

    /**
     * Loops through the first 10 value of the highscore-list, displaying
     * the name and score as a string on a JLabel.
     *
     * - Leonard Bagiu
     */

    private void setLabels(){
        if(highScores == null){
            return;
        }
        int maxLen = highScores.length;
        if(highScores.length > 10){
            maxLen = 10;
        }

        for (int i = 0; i < maxLen; i++){
            String name = highScores[i][0];
            String score = highScores[i][1];
            labels[i] = new JLabel();
        labels[i].setText("Name: " + name + " Score: " + score);
        highScorePanel.add(labels[i]);

        }
    }

}
