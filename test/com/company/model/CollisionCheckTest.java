package com.company.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CollisionCheckTest {
    @Test
    public void entityCollidesWithWall() {
        Game game = new Game(new MapManager());
        game.spawnPlayer();
        Player player = game.getPlayer();
        player.setPosition(0, 0);
        new CollisionCheck(game).isCollison(player);
        assertTrue(player.isCollisionOn());
    }
}
