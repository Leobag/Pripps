package com.company.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.*;

public class FogTest {

    @Test
    public void fogFollow(){
        MapManager map = new MapManager();
        Game game = new Game(map);
        game.spawnPlayer();
        Fog fog = new Fog(game.getPlayer(), map);

        Rectangle[] oldFog = fog.getFogSquares();
        game.getPlayer().setPosition(6,10);

        assertNotEquals(oldFog, fog.getFogSquares());

    }

}
