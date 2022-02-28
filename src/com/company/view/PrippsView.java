package com.company.view;

import com.company.model.PrippsModel;
import com.company.model.TileData.TileManager;

import javax.swing.*;
import java.awt.*;

public class PrippsView extends JPanel {

    PrippsModel model;

    String mapName;

    TileManager t;

    int[][] mapMatrix;

    final private int tileSize = 32;
    
    public PrippsView(PrippsModel model) {
        this.model = model;
        t = model.getTile();
        mapName = model.getCurrentMap();

        mapMatrix = model.getMatrix();
        
        final int screenWidth = tileSize * model.getMaxCol();
        final int screenHeight = tileSize * model.getMaxRow();
        
        setPreferredSize(new Dimension(screenWidth, screenHeight));
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D) g;

        drawMap(g2D);
        drawPlayer(g);
        drawEnemies(g);
        drawFog(g);
    }

    /**
     * draws player
     * @param g
     * - Max Yoorkevich
     */
    void drawPlayer(Graphics g) {
        var x = (int) (model.getPlayer().getPosition().x * tileSize);
        var y = (int) (model.getPlayer().getPosition().y * tileSize);
        var size = (int) Math.round(model.getPlayer().getSize() * tileSize);

        g.drawImage(model.getPlayerImage(), x,y, size, size, null);
    }

    void drawEnemies(Graphics g) {

        for(int i = 0; i < model.getEnemyArr().length; i++){
            var x = (int) Math.round(model.getEnemyArr()[i].getPosition().x * tileSize);
            var y = (int) Math.round(model.getEnemyArr()[i].getPosition().y * tileSize);
            var size = (int) Math.round(model.getEnemyArr()[i].getSize() * tileSize);
            g.setColor(Color.BLACK);
            g.fillRect(x,y,size,size);
        }
    }

    void drawFog(Graphics g) {

        if(model.getPlayer() == null) return;

        g.fillRect(model.getFog().getFogSquares()[0].x, model.getFog().getFogSquares()[0].y,
                model.getFog().getFogSquares()[0].width, model.getFog().getFogSquares()[0].height);

        g.fillRect(model.getFog().getFogSquares()[1].x, model.getFog().getFogSquares()[1].y,
                model.getFog().getFogSquares()[1].width, model.getFog().getFogSquares()[1].height);

        g.fillRect(model.getFog().getFogSquares()[2].x, model.getFog().getFogSquares()[2].y,
                model.getFog().getFogSquares()[2].width, model.getFog().getFogSquares()[2].height);

        g.fillRect(model.getFog().getFogSquares()[3].x, model.getFog().getFogSquares()[3].y,
                model.getFog().getFogSquares()[3].width, model.getFog().getFogSquares()[3].height);



        switch (model.getPlayer().getDirection()){
            case "up" : {
                g.fillArc(model.getFog().getFogSquares()[4].x - 32 * 5 - 8, model.getFog().getFogSquares()[4].y - 32 * 4 - 16,
                        model.getFog().getFogSquares()[4].width, model.getFog().getFogSquares()[4].height, 135, 270);
            } break;
            case "down" : {
                g.fillArc(model.getFog().getFogSquares()[4].x  - 32 * 5 - 8, model.getFog().getFogSquares()[4].y  - 32 * 6,
                        model.getFog().getFogSquares()[4].width, model.getFog().getFogSquares()[4].height, 225, -270);

            } break;
            case "right" : {
                g.fillArc(model.getFog().getFogSquares()[4].x  - 32 * 6, model.getFog().getFogSquares()[4].y  - 32 * 5 - 8,
                        model.getFog().getFogSquares()[4].width, model.getFog().getFogSquares()[4].height, 45, 270);

            } break;
            case "left" : {
                g.fillArc(model.getFog().getFogSquares()[4].x  - 32 * 4 - 16 , model.getFog().getFogSquares()[4].y  - 32 * 5 - 8,
                        model.getFog().getFogSquares()[4].width, model.getFog().getFogSquares()[4].height, 135, -270);
            } break;
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
}
