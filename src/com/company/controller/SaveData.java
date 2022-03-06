package com.company.controller;

import com.company.model.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * Stores necessary information from the game to create a new SaveData object.
 * The object is used to create a savefile in ResourceManager.
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
     * Constructor to create SaveData object.
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
