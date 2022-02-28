package com.company.model;

import com.company.model.TileData.TileManager;
import java.util.List;

public class Game {

    private Player player;
    CollisionCheck collisionCheck;
    EnemyManager enemyManager = new EnemyManager();
    Enemy[] enemies;
    MapManager map;

    public Game(MapManager map){
        this.map = map;

    }

    public void spawnPlayer() {
        player = new Player();
        player.setPosition(2, 11);
        collisionCheck = new CollisionCheck(this);
    }

    public void spawnEnemies(int mapCounter) {
        enemyManager.createEnemies(mapCounter);
        enemies = enemyManager.getEnemyArray();
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
        moveEnemies(deltaTime);
        movePlayer(deltaTime);
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


}
