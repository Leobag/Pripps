package com.company.model;

import com.company.model.TileData.TileManager;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class Game {

    private Player player;
    CollisionCheck collisionCheck;
    EnemyManager enemyManager = new EnemyManager();
    WinManager winManager = new WinManager();
    Enemy[] enemies;
    MapManager map;

    private boolean win = false;

    public Game(MapManager map){
        this.map = map;

    }

    public void spawnPlayer() {
        player = new Player();
        player.setPosition(1, 11);
        player.updateHitBox((2 * 32),(11 * 32), player.hitBoxSize);
        collisionCheck = new CollisionCheck(this);
        player.setMovementImages();
    }

    public void spawnEnemies(int mapCounter){
        enemyManager.createEnemies(mapCounter);
        enemies = enemyManager.getEnemyArray();


    }

    public void setWinCondition(int mapCounter){
        winManager.winCondition(mapCounter);
    }

    /**
     * Calculates and sets next position for player, returns if no inputDirection
     *
     * @param deltaTime currentTimeMillis - previousTimeMillis in game loop in controller
     *                  - Max Yoorkevich
     */
    public void movePlayer(double deltaTime) {
        if(player == null) return;
        if(player.getInputDirection() == null) return;
        if(player.getHitBox().intersects(winManager.getPrippsPack().getHitBox())) win = true;

        double x = player.setPlayerX(deltaTime);
        double y = player.setPlayerY(deltaTime);

        player.setCollisionOn(false);
        collisionCheck.isCollison(player);
        player.updateHitBox((int) (x * 32), (int) (y * 32), player.hitBoxSize);
        player.setMovementImages();

        if(!player.isCollisionOn()) {
            if(!enemyCollision()) {
                player.setPosition(x, y);
            }
        }
    }

    public void moveEnemies(double deltaTime){
        enemyManager.updateEnemyHitboxes();
        Random rand = new Random();

        for(int i = 0; i < enemies.length; i++){

        //sätt inputvalue till Math.sin utifrån movementalgoritm
        //   var y =  (enemies[i].getPosition().y +
        //           Math.sin(0.0) * enemies[i].getSpeed() * deltaTime);
        //   var x = (enemyManager.getEnemyArray()[i].getPosition().x +
        //           Math.cos(0.0) * enemies[i].getSpeed() * deltaTime);


           enemies[i].collisionOn = false;
           collisionCheck.isCollison(enemies[i]);
           enemies[i].setDirection("east"); // sätt direction här eller någon annan stans
           enemyManager.setPutinMovmentIMG(); //sätter bild ifall man ändrar riktning

           if(!enemies[i].collisionOn) {
               //enemies[i].setPosition(x, y);
           }
        }
    }

    private boolean enemyCollision(){
        for (Enemy enemy : enemies) {
            if (player.getHitBox().intersects(enemy.getHitBox())) {
                return true;
            }
        }
        return false;
    }

    /**
     * updates player position
     *
     * @param deltaTime difference in time every game-loop, for smoother fps and as much fps as possible
     *                  - Max Yoorkevich
     */
    public void update(double deltaTime) {
        movePlayer(deltaTime);
        moveEnemies(deltaTime);

    }

    /**
     * set direction for player using radians (movement is inverted compared to the standard unit circle)
     *
     * @param inputDirection
     * - Max Yoorkevich
     */
    public void setInputDirection(Double inputDirection) {
            player.setInputDirection(inputDirection);
            player.setStringDirection(inputDirection);

    }

    public Player getPlayer() {
        return this.player;
    }

    public boolean getWin(){
        return win;
    }

    public Entity getPrippsPack(){
        return winManager.getPrippsPack();
    }


}
