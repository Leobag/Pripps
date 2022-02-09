package com.company.model;

public class Game {

    private Player player;
    private Double inputDirection;

    public void spawnPlayer(){
        player = new Player();
        player.setPosition(9, 1);
    }
    public Player getPlayer(){
        return this.player;
    }

    public void movePlayer(double deltaTime) {
        if (player == null) return;
        if (inputDirection == null) return;
        var x = (player.getPosition().x + Math.cos(inputDirection) * player.speed * deltaTime);
        var y = (player.getPosition().y + Math.sin(inputDirection) * player.speed * deltaTime);
        player.setPosition(x, y);
        System.out.println(Math.sin(inputDirection));
    }
    public void update(double deltaTime) {
        movePlayer(deltaTime);
    }

    public void setInputDirection(Double inputDirection) {
        this.inputDirection = inputDirection;
    }
}
