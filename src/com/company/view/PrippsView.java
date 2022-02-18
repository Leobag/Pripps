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
    }

    /**
     * draws player
     * @param g
     * - Max Yoorkevich
     */
    void drawPlayer(Graphics g) {
        var x = (int) Math.round(model.getPlayer().getPosition().x * tileSize);
        var y = (int) Math.round(model.getPlayer().getPosition().y * tileSize);
        var size = (int) Math.round(model.getPlayer().getSize() * tileSize);

        g.setColor(Color.RED);
        g.fillRect(x, y, size, size);
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
