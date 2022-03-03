package com.company.view;

import com.company.model.PrippsModel;
import com.company.model.TileData.TileManager;

import javax.swing.*;
import java.awt.*;

public class PrippsView extends JPanel {

    final private int tileSize = 32;
    PrippsModel model;
    String mapName;
    TileManager t;
    WinView winView = new WinView();
    int[][] mapMatrix;

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


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D) g;

        if(model.getPlayer() == null){
            return;
        }
        drawMap(g);
        drawPlayer(g);
        drawEnemies(g);
        drawPripps(g);
        drawFog(g);

    }

    /**
     * draws player
     * @param g
     * - Max Yoorkevich
     */
    void drawPlayer(Graphics g) {
        var x = (int) (model.getPlayer().getPosition().getX() * tileSize);
        var y = (int) (model.getPlayer().getPosition().getY() * tileSize);
        var size = (int) Math.round(model.getPlayer().getSize() * tileSize);

        g.drawImage(model.getPlayerImage(), x - 5, y - 5, size + 10, size + 10, null);
    }

    void drawEnemies(Graphics g) {

        for (int i = 0; i < model.getEnemyArr().length; i++) {
            var x = (int) Math.round(model.getEnemyArr()[i].getPosition().getX() * tileSize);
            var y = (int) Math.round(model.getEnemyArr()[i].getPosition().getY() * tileSize);
            var size = (int) Math.round(model.getEnemyArr()[i].getSize() * tileSize);

           g.drawImage(model.getEnemyArr()[i].getUnitImage(), x - 10,y - 5, size + 20, size + 10, null);
        }
    }

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

    void drawPripps(Graphics g) {
        if (model.getMap().getMapCounter() == 2) {
            var x = (int) (model.getPrippsPack().getPosition().getX() * tileSize);
            var y = (int) (model.getPrippsPack().getPosition().getY() * tileSize);
            var size = (int) Math.round(model.getPrippsPack().getSize() * tileSize);

            g.drawImage(model.getPrippsPack().unitImage, x - 15, y - 15, size + 64, size + 64, null);
        }
    }

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
}
