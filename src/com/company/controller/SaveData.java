package com.company.controller;

import com.company.model.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * A class with the purpose of storing necessary information required to save the game.
 * @author Andy Alavinasab
 */
public class SaveData implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 1L;

    private final Position position;
    private final int mapCounter;
    private final Position[] enemyPosition;

    public SaveData(Position position, int mapCounter, Position[] enemyPosition){
        this.position = position;
        this.mapCounter = mapCounter;
        this.enemyPosition = enemyPosition;
    }

    public Position getPosition() {
        return position;
    }

    public int getMapCounter() {
        return mapCounter;
    }

    public Position[] getEnemyPosition() {
        return enemyPosition;
    }
}
