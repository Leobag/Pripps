package com.company.controller;

import com.company.model.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * A class with the purpose of storing necessary information required to save the game.
 * @author Andy Alavinasab
 * @version 05-03-22
 */
public class SaveData implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 1L;

    private final Position position;
    private final int mapCounter;
    private final Position[] enemyPosition;

    /**
     * Constructor to create saveData.
     * @param position the position of the player.
     * @param mapCounter which map the game is currently on.
     * @param enemyPosition array for all enemy positions.
     */
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
