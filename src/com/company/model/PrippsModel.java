package com.company.model;

import com.company.model.TileData.TileManager;
import com.company.model.MapManager;

import java.awt.image.BufferedImage;


public class PrippsModel {


    Game game;
    MapManager map;
    Fog fog;


    public PrippsModel() {
        map = new MapManager();
        game = new Game(map);
        map.loadCurrentMap();
        fog = new Fog(game.getPlayer(), map);

    }

    public BufferedImage getTileImage(int num) {
        return map.getTiles().tile[num].image;
    }

    public int[][] getMatrix() {
        return map.getMapMatrix();
    }

    public String getCurrentMap() {
        return map.getCurrentMap();
    }



    public int getMaxCol() {
        return map.getMaxCol();
    }

    public int getMaxRow() {
        return map.getMaxRow();
    }

    public TileManager getTile() {
        return map.getTiles();
    }


    public void startGame(){
        game.spawnPlayer();
        game.spawnEnemies(map.getMapCounter());
    }
    public Player getPlayer() {
        return game.getPlayer();
    }

    public Enemy[] getEnemyArr(){
        return game.enemyManager.getEnemyArray();
    }

    public void setInputDirection(Double inputDirection) {
        game.setInputDirection(inputDirection);
    }

    public void update(double deltaTime) {
        game.update(deltaTime);
    }

    public BufferedImage getPlayerImage(){
        return game.getPlayer().getUnitImage();
    }

    public Fog getFog() {
        return fog;
    }
}
