package com.company.controller;

import com.company.model.*;

import java.io.Serial;
import java.io.Serializable;

public class SaveData implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 1L;

    private final Position position;
    private final int mapCounter;

    public SaveData(Position position, int mapCounter){
        this.position = position;
        this.mapCounter = mapCounter;
    }

    public Position getPosition() {
        return position;
    }

    public int getMapCounter() {
        return mapCounter;
    }
}
