package com.company.model;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Enemy extends Entity {

    public final int hitBoxSize = 15;
    public int unitCounter, unitNum;
    public String oldDir;

    public Enemy() {
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
                if (tempDir.equals("northEast") || tempDir.equals("southEast")) {
                    tempDir = "east";
                } else if (tempDir.equals("northWest") || tempDir.equals("southWest")) {
                    tempDir = "west";
                }
                this.unitImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Entities/" + directory + "/" + tempDir + unitNum + ".png")));

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

