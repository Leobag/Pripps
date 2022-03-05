package com.company.model;

import java.util.Random;

public class Game {

    CollisionCheck collisionCheck;
    EnemyManager enemyManager = new EnemyManager();
    WinManager winManager = new WinManager();
    Enemy[] enemies;
    MapManager map;
    HighscoreTimer highscoreTimer;
    private Player player;
    private boolean win = false;

    public Game(MapManager map) {
        this.map = map;
        collisionCheck = new CollisionCheck(this);
    }

    /**
     * Creates the player and initializes its position, hitbox and image.
     */

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
     * Calculates next position for player, returns if no inputDirection.
     *
     * Checks if collision is detected between a collision tile and the player
     *
     * Updates player hitbox which discovers collision with other objects i.e. enemies
     * and winning condition-object PrippsPack.
     *
     * Updates player images depending on current direction
     *
     * Checks if collison is detected and if not it sets the player position
     *
     * @param deltaTime currentTimeMillis - previousTimeMillis in game loop in controller
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

    /**
     * Updates the enemy hitbox used to detect collision with player.
     *
     * Calculates next position for each enemy currently spawned on the map.
     *
     * Update images for enemies.
     *
     * Sets new position if no collision is detected, otherwise sets a new random direction.
     *
     * Checks if collision is detected.
     *
     * @param deltaTime - currentTimeMillis - previousTimeMillis in game loop in controller
     */

    public void moveEnemies(double deltaTime) {
        enemyManager.updateEnemyHitboxes();

        for (Enemy enemy : enemies) {

            double x = (enemy.getPosition().getX() + Math.cos(enemy.getMovementDirection()) * enemy.getSpeed() * deltaTime);
            double y = (enemy.getPosition().getY() + Math.sin(enemy.getMovementDirection()) * enemy.getSpeed() * deltaTime);

            enemy.collisionOn = false;
            collisionCheck.isCollison(enemy);
            enemyManager.setPutinMovmentIMG();

            if (!enemy.isCollisionOn() && !enemyCollision()) {
                enemy.setPosition(x, y);
            } else {
                enemy.changeDirection();
            }
        }
    }

    /**
     * checks if player hitbox has intersected enemy hitbox.
     *
     * sets boolean variable in player @dead to true if collision is detected.
     *
     * @return - returns boolean true or false if player hitbox has intersected enemy hitbox.
     */
    public boolean enemyCollision() {
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
     * updates enemy positions.
     *
     * @param deltaTime difference in time every game-loop, for smoother fps and as much fps as possible
     */
    public void update(double deltaTime) {
        movePlayer(deltaTime);
        moveEnemies(deltaTime);
    }

    /**
     * set direction for player using radians (movement is inverted compared to the standard unit circle).
     *
     * set string direction, e.g. south for player used for switch case functions.
     * @param inputDirection
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

    /**
     * Resets the winning condition to not be met.
     */
    public void resetWin() {
        win = false;
    }

    public boolean getWin() {
        return win;
    }

    public Entity getPrippsPack() {
        return winManager.getPrippsPack();
    }

    public Position[] getPosArray(){
        return enemyManager.getPosArray();
    }

    public void setPosArray(Position[] posArray){
        enemyManager.setPosArray(posArray);
    }


}
