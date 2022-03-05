package com.company.controller;

import com.company.model.Game;
import com.company.model.MapManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

public class SaveLoadTest {


    @Test
    public void saveLoad() throws IOException, ClassNotFoundException {
        MapManager map = new MapManager();
        Game game = new Game(map);
        game.spawnPlayer();
        SaveData saveData = new SaveData(game.getPlayer().getPosition(), map.getMapCounter(), game.getPosArray());
        ResourceManager.save(saveData, "data.txt");

        SaveData loadData = (SaveData) ResourceManager.load("data.txt");
        assertEquals(saveData.getPosition().getX(), loadData.getPosition().getX());
        assertEquals(saveData.getPosition().getY(), loadData.getPosition().getY());
        assertEquals(saveData.getMapCounter(), loadData.getMapCounter());
        assertEquals(saveData.getEnemyPosition(), loadData.getEnemyPosition());

    }
}

