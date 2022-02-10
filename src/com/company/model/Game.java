package com.company.model;

public class Game {

    private Player player;
    private Double inputDirection;

    public void spawnPlayer() {
        player = new Player();
        player.setPosition(9, 1);
    }

    public Player getPlayer() {
        return this.player;
    }

    /**
     * Calculates and sets next position for player, returns if no inputDirection
     * @param deltaTime currentTimeMillis - previousTimeMillis in game loop in controller
     * - Max Yoorkevich
     */
    public void movePlayer(double deltaTime) {
        if (player == null) return;
        if (inputDirection == null) return;
        var x = (player.getPosition().x + Math.cos(inputDirection) * player.speed * deltaTime);
        var y = (player.getPosition().y + Math.sin(inputDirection) * player.speed * deltaTime);
        player.setPosition(x, y);
    }

    /**
     * updates player position
     * @param deltaTime difference in time every game-loop, for smoother fps and as much fps as possible
     * - Max Yoorkevich
     */
    public void update(double deltaTime) {
        movePlayer(deltaTime);
    }

    public void setInputDirection(Double inputDirection) {
        this.inputDirection = inputDirection;
    }
}
