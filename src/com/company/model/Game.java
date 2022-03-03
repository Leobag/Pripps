package com.company.model;

import java.util.Random;

public class Game {

    CollisionCheck collisionCheck;
    EnemyManager enemyManager = new EnemyManager();
    WinManager winManager = new WinManager();
    Enemy[] enemies;
    MapManager map;
    private Player player;
    private boolean win = false;

    public Game(MapManager map) {
        this.map = map;
        collisionCheck = new CollisionCheck(this);

    }

    public void spawnPlayer() {

        player = new Player();
        player.setPosition(1, 11);
        player.updateHitBox((2 * 32), (11 * 32), player.hitBoxSize);
        player.setMovementImages();
    }

    public void spawnEnemies(int mapCounter) {
        enemyManager.createEnemies(mapCounter);
        enemies = enemyManager.getEnemyArray();
    }

    public void setWinCondition(int mapCounter) {
        winManager.winCondition(mapCounter);
    }

    /**
     * Calculates and sets next position for player, returns if no inputDirection
     *
     * @param deltaTime currentTimeMillis - previousTimeMillis in game loop in controller
     *                  - Max Yoorkevich
     */
    public void movePlayer(double deltaTime) {
        if (player == null) return;
        if (player.getInputDirection() == null) return;
        if (player.getHitBox().intersects(winManager.getPrippsPack().getHitBox())) win = true;

        double x = player.setPlayerX(deltaTime);
        double y = player.setPlayerY(deltaTime);

        player.setCollisionOn(false);
        collisionCheck.isCollison(player);
        player.updateHitBox((int) (x * 32), (int) (y * 32), player.hitBoxSize);
        player.setMovementImages();

        if (!player.isCollisionOn() && !enemyCollision()) {
                player.setPosition(x, y);
            }
        }


    public void moveEnemies(double deltaTime) {
        enemyManager.updateEnemyHitboxes();

        for (Enemy enemy : enemies) {

            double x = (enemy.getPosition().getX() + Math.cos(enemy.getMovementDirection()) * enemy.getSpeed() * deltaTime);
            double y = (enemy.getPosition().getY() + Math.sin(enemy.getMovementDirection()) * enemy.getSpeed() * deltaTime);

            enemy.collisionOn = false;
            collisionCheck.isCollison(enemy);
            enemyManager.setPutinMovmentIMG();

            if (!enemy.isCollisionOn()) {
                enemy.setPosition(x, y);
            } else {
                enemy.changeDirection();
            }
        }
    }

    private boolean enemyCollision() {
        for (Enemy enemy : enemies) {
            if (player.getHitBox().intersects(enemy.getHitBox())) {
                player.setDead(true);
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

    public void deletePlayer() {
        this.player = null;
    }

    public void resetWin() {
        win = false;
    }

    public void deleteEnemies() {
        for (Enemy enemy : enemies) {
            enemy = null;
        }
    }

    public boolean getWin() {
        return win;
    }

    public Entity getPrippsPack() {
        return winManager.getPrippsPack();
    }


}
