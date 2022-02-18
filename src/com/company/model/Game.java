package com.company.model;

import java.util.List;

public class Game {

    private Player player;
    private Enemy enemy1;
    private Double inputDirection;
    int[][] mapTileMatrix;
    CollisionCheck collisionCheck;

    public Game(int[][] mapTileMatrix){
        this.mapTileMatrix = mapTileMatrix;
    }

    public void spawnPlayer() {
        player = new Player();
        player.setPosition(9, 1);
        collisionCheck = new CollisionCheck(this);
    }

    public void spawnEnemies() {
        List<Enemy> enemies;
    }

    public Player getPlayer() {
        return this.player;
    }

    /**
     * Calculates and sets next position for player, returns if no inputDirection
     *
     * @param deltaTime currentTimeMillis - previousTimeMillis in game loop in controller
     *                  - Max Yoorkevich
     */
    public void movePlayer(double deltaTime) {
        if (player == null) return;
        if (inputDirection == null) return;
        var x = (player.getPosition().x + Math.cos(inputDirection) * player.speed * deltaTime);
        var y = (player.getPosition().y + Math.sin(inputDirection) * player.speed * deltaTime);
        //player.getPosition(); //TODO: räkna ut vinkel mellan punkter
        // TODO: if-sats för att kolla collision
        //if (player.getPosition().x == )
        player.setPosition(x, y);
    }

    /**
     * updates player position
     *
     * @param deltaTime difference in time every game-loop, for smoother fps and as much fps as possible
     *                  - Max Yoorkevich
     */
    public void update(double deltaTime) {
        movePlayer(deltaTime);
    }

    /**
     * set direction for player using radians (movement is inverted compared to the standard unit circle)
     *
     * @param inputDirection
     * - Max Yoorkevich
     */
    public void setInputDirection(Double inputDirection) {
        this.inputDirection = inputDirection;
    }

    public int[][] getMapTileMatrix() {
        return mapTileMatrix;
    }
}
