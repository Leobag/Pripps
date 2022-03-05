package com.company.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CollisionCheckTest {
    @Test
    public void entityCollidesWithWall() {
        Game game = new Game(new MapManager());
        game.spawnPlayer();
        Player player = game.getPlayer();
        player.setPosition(1, 1);
        player.setDirection("south");
        new CollisionCheck(game).isCollison(player);
        assertTrue(player.isCollisionOn());

        player.setDirection("north");
        new CollisionCheck(game).isCollison(player);
        assertTrue(player.isCollisionOn());

        player.setDirection("west");
        new CollisionCheck(game).isCollison(player);
        assertTrue(player.isCollisionOn());

        player.setDirection("east");
        new CollisionCheck(game).isCollison(player);
        assertTrue(player.isCollisionOn());

        player.setDirection("southEast");
        new CollisionCheck(game).isCollison(player);
        assertTrue(player.isCollisionOn());

        player.setDirection("southWest");
        new CollisionCheck(game).isCollison(player);
        assertTrue(player.isCollisionOn());

        player.setDirection("northEast");
        new CollisionCheck(game).isCollison(player);
        assertTrue(player.isCollisionOn());

        player.setDirection("northWest");
        new CollisionCheck(game).isCollison(player);
        assertTrue(player.isCollisionOn());

    }
}
