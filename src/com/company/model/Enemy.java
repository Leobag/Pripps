package com.company.model;

import com.company.model.Entity;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Enemy extends Entity {

    public final int hitBoxSize = 15;
    public int unitCounter, unitNum;
    public String oldDir;

    public Enemy(){
        this.unitCounter = 0;
        this.unitNum = 1;
        this.speed = 2;
        this.size = 0.5;
    }

    public void setEnemyImages(String directory) {

        String tempDir = this.direction;
        if (oldDir != this.direction) {
            oldDir = tempDir;

            try {
                if (tempDir == "northEast" || tempDir == "southEast") {
                    tempDir = "east";
                } else if (tempDir == "northWest" || tempDir == "southWest") {
                    tempDir = "west";
                }
                this.unitImage = ImageIO.read(getClass().getResourceAsStream("/Entities/" + directory + "/" + "pxArt" + ".png"));

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

