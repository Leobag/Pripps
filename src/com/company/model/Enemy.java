package com.company.model;

import com.company.model.Entity;

public class Enemy extends Entity {
    public final int hitBoxSize = 15;
    public Enemy(){
        this.speed = 2;
        this.size = 0.5;
    }
}
