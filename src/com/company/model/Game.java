package com.company.model;

import com.company.model.TileData.TileManager;
import java.util.List;

public class Game {

    private Player player;
    private Enemy enemy1;
    private Double inputDirection;
    int[][] mapTileMatrix;
    CollisionCheck collisionCheck;
    TileManager tile;

    public Game(int[][] mapTileMatrix, TileManager tile){
        this.mapTileMatrix = mapTileMatrix;
        this.tile = tile;
    }

    public void spawnPlayer() {
        player = new Player();
        player.setPosition(0, 11);
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

        player.setCollisionOn(false);
        collisionCheck.isCollison(player);
        player.setMovementImages();

        if(!player.isCollisionOn()) {
            player.setPosition(x, y);
        }
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
        setStringDirection(this.inputDirection);
    }

    public void setStringDirection(Double inputDirection){
        if(!(inputDirection == null)){
            if (this.inputDirection == -1.5707963267948966) {
                player.setDirection("north");
            } else if (this.inputDirection == 1.5707963267948966) {
                player.setDirection("south");
            } else if (this.inputDirection == 3.141592653589793) {
                player.setDirection("west");
            } else if (this.inputDirection == 0.0) {
                player.setDirection("east");
            } else if (this.inputDirection == 0.7853981633974483) {
                player.setDirection("southEast");
            } else if (this.inputDirection == -0.7853981633974483) {
                player.setDirection("northEast");
            } else if (this.inputDirection == 2.356194490192345) {
                player.setDirection("southWest");
            } else if (this.inputDirection == -2.356194490192345) {
                player.setDirection("northWest");
            }
        }
    }

    public int[][] getMapTileMatrix() {
        return mapTileMatrix;
    }

    public TileManager getTile() {
        return tile;
    }
}
