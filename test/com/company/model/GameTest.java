package com.company.model;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Test
    public void entityCollision(){
        Game game = new Game(new MapManager());
        game.spawnPlayer();
        game.spawnEnemies(game.map.getMapCounter());

        game.enemies[1].setPosition(5,5);
        game.getPlayer().setPosition(5,5);
        game.enemyManager.updateEnemyHitboxes();
        game.getPlayer().updateHitBox(5 * 32,5 * 32,16);

        assertTrue(game.enemyCollision());

    }

    @Test
    public void updateMovement(){
        Game game = new Game(new MapManager());
        game.spawnPlayer();
        game.spawnEnemies(game.map.getMapCounter());

        game.enemies[0].setPosition(3,3);
        game.enemies[0].setDirection("north");
        game.setInputDirection(0.0);

        game.update(100);

        assertNotEquals("north", game.enemies[0].getDirection());
        assertNotEquals(1, game.getPlayer().getPosition().getX());

    }

    @Test
    public void winGame(){
        Game game = new Game(new MapManager());
        game.spawnPlayer();
        game.setWinCondition(2);
        game.getPlayer().updateHitBox(3 * 32, 7 * 32, 16);
        game.getPlayer().setPosition(3,7);
        game.setInputDirection(0.0);
        assertTrue(game.getPlayer().getHitBox().intersects(game.getPrippsPack().getHitBox()));
    }

    @Test
    public void deletePlayer(){
        MapManager map = new MapManager();
        Game game = new Game(map);
        game.spawnPlayer();
        game.deletePlayer();

        assertNull(game.getPlayer());
    }

    @Test
    public void resetWin(){
        MapManager map = new MapManager();
        Game game = new Game(map);
        game.resetWin();

        assertFalse(game.getWin());
    }



}
