package com.company.model;

import com.company.model.TileData.TileManager;
import com.company.model.server.Client;
import com.company.model.server.Server;

import java.awt.image.BufferedImage;


public class PrippsModel {
    /**
     * PrippsModel is a centralized model which facilitates communication
     * with all other classes in the model packet.
     *
     * Prippsmodel mostly consist of getters and setters of key values and
     * necessary objects.
     *
     */

    Game game;
    MapManager map;
    Fog fog;
    String SubmittedName;
    HighscoreTimer highscoreTimer = new HighscoreTimer();
    Client client = new Client();
    Server s = new Server();



    public PrippsModel() {

        map = new MapManager();
        game = new Game(map);
        map.loadCurrentMap();
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


    public void startGame() {
        map.loadCurrentMap();
        game.spawnEnemies(map.getMapCounter());
        game.setWinCondition(map.getMapCounter());
        game.spawnPlayer();
        fog = new Fog(game.getPlayer(), map);
    }

    public Player getPlayer() {
        return game.getPlayer();
    }

    public void enterHighScore(String name) {
        System.out.println(name);
        System.out.println(highscoreTimer.getHighscoreTime());
        client.updateServerScore(name, highscoreTimer.getHighscoreTime());
        highscoreTimer.resetHighscoreTimer();
    }
    public void stopGameTimer(){
        highscoreTimer.stopTimer();
    }
    public void startGameTimer(){
        highscoreTimer.startTimer();
    }

    public Enemy[] getEnemyArr() {
        return game.enemyManager.getEnemyArray();
    }

    public void setInputDirection(Double inputDirection) {
        game.setInputDirection(inputDirection);
    }

    public void update(double deltaTime) {
        game.update(deltaTime);
    }

    public BufferedImage getPlayerImage() {
        return game.getPlayer().getUnitImage();
    }

    public Fog getFog() {
        return fog;
    }

    public boolean winCondition() {
        return game.getWin();
    }

    public Entity getPrippsPack() {
        return game.getPrippsPack();
    }

    public MapManager getMap() {
        return this.map;
    }

    public String getSubmittedName() {
        return SubmittedName;
    }

    public void setSubmittedName(String submittedName) {
        SubmittedName = submittedName;
    }

    public void resetGame() {
        map.resetMap();
        game.deletePlayer();
        game.resetWin();
    }

    public Position[] getPosArray() {
        return game.getPosArray();
    }

    public void setPosArray(Position[] posArray){
        game.setPosArray(posArray);
    }

}
