package com.company.model;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
public class MapTest {

    @Test
    public void loadTextFile(){

        MapManager map = new MapManager();

        map.loadCurrentMap();

        int tileNum = map.getMapMatrix()[1][2];

        map.nextMap();

        assertNotEquals(tileNum, map.getMapMatrix()[1][2]);


    }
}
