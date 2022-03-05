package com.company.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class PlayerTest {

    @Test
    public void isDead(){
        MapManager map = new MapManager();
        Game game = new Game(map);
        game.spawnPlayer();
        game.getPlayer().setDead(true);

        assertTrue(game.getPlayer().getDead());
    }

    @Test
    public void playerStringDirection(){
        MapManager map = new MapManager();
        Game game = new Game(map);
        game.spawnPlayer();

        game.getPlayer().setInputDirection(-1.5707963267948966);
        game.getPlayer().setStringDirection(game.getPlayer().getInputDirection());
        assertEquals(game.getPlayer().getDirection(), "north");

        game.getPlayer().setInputDirection(1.5707963267948966);
        game.getPlayer().setStringDirection(game.getPlayer().getInputDirection());
        assertEquals(game.getPlayer().getDirection(), "south");

        game.getPlayer().setInputDirection(3.141592653589793);
        game.getPlayer().setStringDirection(game.getPlayer().getInputDirection());
        assertEquals(game.getPlayer().getDirection(), "west");

        game.getPlayer().setInputDirection(0.0);
        game.getPlayer().setStringDirection(game.getPlayer().getInputDirection());
        assertEquals(game.getPlayer().getDirection(), "east");

        game.getPlayer().setInputDirection(0.7853981633974483);
        game.getPlayer().setStringDirection(game.getPlayer().getInputDirection());
        assertEquals(game.getPlayer().getDirection(), "southEast");

        game.getPlayer().setInputDirection(-0.7853981633974483);
        game.getPlayer().setStringDirection(game.getPlayer().getInputDirection());
        assertEquals(game.getPlayer().getDirection(), "northEast");

        game.getPlayer().setInputDirection(2.356194490192345);
        game.getPlayer().setStringDirection(game.getPlayer().getInputDirection());
        assertEquals(game.getPlayer().getDirection(), "southWest");

        game.getPlayer().setInputDirection(-2.356194490192345);
        game.getPlayer().setStringDirection(game.getPlayer().getInputDirection());
        assertEquals(game.getPlayer().getDirection(), "northWest");
    }
}
