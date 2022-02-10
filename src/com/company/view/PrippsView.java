package com.company.view;

import com.company.model.Game;
import com.company.model.PrippsModel;
import com.company.model.TileData.TileManager;

import javax.swing.*;
import java.awt.*;

public class PrippsView extends JPanel {

    PrippsModel model;
    Game game;

    String mapName;

    TileManager t;

    int[][] mapMatrix;


    public PrippsView(PrippsModel model, Game game) {
        this.model = model;
        this.game = game;
        t = model.getTile();
        mapName = model.getCurrentMap();

        mapMatrix = model.getMatrix();

        setPreferredSize(new Dimension(this.model.getScreenWidth(), this.model.getScreenHeight()));


    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D) g;

        drawMap(g2D);
        drawPlayer(g);
    }

    void drawPlayer(Graphics g) {
        var x = (int) Math.round(game.getPlayer().getPosition().x * 32);
        var y = (int) Math.round(game.getPlayer().getPosition().y * 32);
        var tmpSize = (int) Math.round(game.getPlayer().getSize() * 32);

        g.setColor(Color.RED);
        g.fillRect(x, y, tmpSize, tmpSize);
    }

    public void drawMap(Graphics2D g2D) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < model.getMaxCol() && row < model.getMaxRow()) {

            int tileNum = mapMatrix[col][row];

            g2D.drawImage(model.getTileImage(tileNum), x, y, model.getTileSize(), model.getTileSize(), null);
            col++;
            x += model.getTileSize();

            if (col == model.getMaxCol()) {
                col = 0;
                x = 0;
                row++;
                y += model.getTileSize();
            }
        }

    }

}
