package com.company.model;

import javax.imageio.ImageIO;
import java.io.IOException;


public class Player extends Entity {


    private int unitCounter, unitNum;
    private Double inputDirection;
    public final int hitBoxSize = 16;
    private boolean dead = false;


    public Player(){
        this.speed = 4;
        this.size = 0.5;
        this.unitCounter = 0;
        this.unitNum = 1;
        this.setDirection("south");
    }

    public void setMovementImages(){

        if(dead){
            try {
                this.unitImage = ImageIO.read(getClass().getResourceAsStream("/Entities/PlayerImages/realExplosion.png"));
            }catch (IOException e){
                System.out.println("cant find explosionIMG");
            }
        }else {
            String tempDir = this.direction;
            if (this.unitCounter > 30) {

                if (this.unitNum == 1) {
                    this.unitNum = 2;
                } else if (this.unitNum == 2) {
                    this.unitNum = 1;
                }
                try {
                    if (tempDir == "northEast" || tempDir == "southEast") {
                        tempDir = "east";
                    } else if (tempDir == "northWest" || tempDir == "southWest") {
                        tempDir = "west";
                    }

                    this.unitImage = ImageIO.read(getClass().getResourceAsStream("/Entities/PlayerImages/" + tempDir + Integer.toString(unitNum) + ".png"));

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }

            }
            this.unitCounter++;
        }
    }

    public double setPlayerX(Double deltaTime){
        return (getPosition().x + Math.cos(getInputDirection()) * speed * deltaTime);
    }

    public double setPlayerY(Double deltaTime){
        return (getPosition().y + Math.sin(getInputDirection()) * speed * deltaTime);
    }

    public void setStringDirection(Double inputDirection){
        if(!(inputDirection == null)) {
            if (this.inputDirection == -1.5707963267948966) {
                this.setDirection("north");
            } else if (this.inputDirection == 1.5707963267948966) {
                this.setDirection("south");
            } else if (this.inputDirection == 3.141592653589793) {
                this.setDirection("west");
            } else if (this.inputDirection == 0.0) {
                this.setDirection("east");
            } else if (this.inputDirection == 0.7853981633974483) {
                this.setDirection("southEast");
            } else if (this.inputDirection == -0.7853981633974483) {
                this.setDirection("northEast");
            } else if (this.inputDirection == 2.356194490192345) {
                this.setDirection("southWest");
            } else if (this.inputDirection == -2.356194490192345) {
                this.setDirection("northWest");
            }
        }
    }


    public void setInputDirection(Double inputDirection) {
        this.inputDirection = inputDirection;
    }
    public Double getInputDirection(){
        return this.inputDirection;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public boolean getDead(){
        return this.dead;
    }
}