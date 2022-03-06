package com.company.view;

import com.company.model.PrippsModel;
import com.company.model.TileData.TileManager;

import javax.swing.*;
import java.awt.*;


/**
 * The purpose of the PrippsView class is to handle graphics for the objects in the program.
 * @author - Emil Berzelius
 */
public class PrippsView extends JPanel {

    final private int tileSize = 32;
    PrippsModel model;
    String mapName;
    TileManager t;
    WinView winView = new WinView();
    HighscoreView highscoreView;
    int[][] mapMatrix;

    /**
     * Sets the screen size according to the map size.
     * @param model- uses model to reach all components drawn.
     */
    public PrippsView(PrippsModel model) {
        this.model = model;
        t = model.getTile();
        mapName = model.getCurrentMap();

        mapMatrix = model.getMatrix();


        final int screenWidth = tileSize * model.getMaxCol();
        final int screenHeight = tileSize * model.getMaxRow();

        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(Color.BLACK);
    }

    /**
     * The main paiting function used to draw all components of the game.
     *
     * @param g - Graphics object used by repaint.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D) g;

        if(model.getPlayer() == null){
            return;
        }
        drawMap(g2D);
        drawPlayer(g);
        drawEnemies(g);
        drawPripps(g);
        //drawFog(g);

    }

    /**
     * Draws player according to position
     * @param g - draw object used to draw player.
     */
    void drawPlayer(Graphics g) {
        var x = (int) (model.getPlayer().getPosition().getX() * tileSize);
        var y = (int) (model.getPlayer().getPosition().getY() * tileSize);
        var size = (int) Math.round(model.getPlayer().getSize() * tileSize);

        g.drawImage(model.getPlayerImage(), x - 5, y - 5, size + 10, size + 10, null);
    }

    /**
     * Draws enemies according to position.
     *
     * @param g - draw object used to draw enemies.
     */

    void drawEnemies(Graphics g) {

        for (int i = 0; i < model.getEnemyArr().length; i++) {
            var x = (int) Math.round(model.getEnemyArr()[i].getPosition().getX() * tileSize);
            var y = (int) Math.round(model.getEnemyArr()[i].getPosition().getY() * tileSize);
            var size = (int) Math.round(model.getEnemyArr()[i].getSize() * tileSize);

           g.drawImage(model.getEnemyArr()[i].getUnitImage(), x - 10,y - 5, size + 20, size + 10, null);
        }
    }

    /**
     * Fog object calculates 5 rectangles surrounding the player. 4 of them is always drawn as black rectangles
     * the fifth one is drawn as a black arc surrounding the player making only a small triangle of the map
     * visible to the player.
     *
     * @param g - draw object used to draw Fog of War.
     */
    void drawFog(Graphics g) {

        if (model.getPlayer() == null) return;

        g.fillRect(model.getFog().getFogSquares()[0].x, model.getFog().getFogSquares()[0].y,
                model.getFog().getFogSquares()[0].width, model.getFog().getFogSquares()[0].height);

        g.fillRect(model.getFog().getFogSquares()[1].x, model.getFog().getFogSquares()[1].y,
                model.getFog().getFogSquares()[1].width, model.getFog().getFogSquares()[1].height);

        g.fillRect(model.getFog().getFogSquares()[2].x, model.getFog().getFogSquares()[2].y,
                model.getFog().getFogSquares()[2].width, model.getFog().getFogSquares()[2].height);

        g.fillRect(model.getFog().getFogSquares()[3].x, model.getFog().getFogSquares()[3].y,
                model.getFog().getFogSquares()[3].width, model.getFog().getFogSquares()[3].height);


        switch (model.getPlayer().getDirection()) {
            case "north": {
                g.fillArc(model.getFog().getFogSquares()[4].x - 32 * 5 - 8, model.getFog().getFogSquares()[4].y - 32 * 4 - 16,
                        model.getFog().getFogSquares()[4].width, model.getFog().getFogSquares()[4].height, 135, 270);
            }
            break;
            case "south": {
                g.fillArc(model.getFog().getFogSquares()[4].x - 32 * 5 - 8, model.getFog().getFogSquares()[4].y - 32 * 6,
                        model.getFog().getFogSquares()[4].width, model.getFog().getFogSquares()[4].height, 225, -270);

            }
            break;
            case "east":
            case "southEast":
            case "northEast": {
                g.fillArc(model.getFog().getFogSquares()[4].x - 32 * 6, model.getFog().getFogSquares()[4].y - 32 * 5 - 8,
                        model.getFog().getFogSquares()[4].width, model.getFog().getFogSquares()[4].height, 45, 270);

            }
            break;
            case "west":
            case "northWest":
            case "southWest": {
                g.fillArc(model.getFog().getFogSquares()[4].x - 32 * 4 - 16, model.getFog().getFogSquares()[4].y - 32 * 5 - 8,
                        model.getFog().getFogSquares()[4].width, model.getFog().getFogSquares()[4].height, 135, -270);
            }
            break;
        }

    }

    /**
     * Draws PrippsPack if the final map is reached.
     *
     * @param g - draw object used to draw the winning condition PrippsPack
     */
    void drawPripps(Graphics g) {
        if (model.getMap().getMapCounter() == 2) {
            var x = (int) (model.getPrippsPack().getPosition().getX() * tileSize);
            var y = (int) (model.getPrippsPack().getPosition().getY() * tileSize);
            var size = (int) Math.round(model.getPrippsPack().getSize() * tileSize);

            g.drawImage(model.getPrippsPack().unitImage, x - 15, y - 15, size + 64, size + 64, null);
        }
    }

    /**
     * Acquires the mapMatrix and paints it according to tile numbers.
     * @param g2D - draw object used to paint the map.
     */

    public void drawMap(Graphics2D g2D) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < model.getMaxCol() && row < model.getMaxRow()) {

            int tileNum = mapMatrix[col][row];

            g2D.drawImage(model.getTileImage(tileNum), x, y, tileSize, tileSize, null);
            col++;
            x += tileSize;

            if (col == model.getMaxCol()) {
                col = 0;
                x = 0;
                row++;
                y += tileSize;
            }
        }
    }

    public WinView getWinView() {
        return this.winView;
    }

    public HighscoreView getHighscoreView(){
        return highscoreView;
    }

    public void createHighScoreView(){
        highscoreView = new HighscoreView(this);
    }
}
