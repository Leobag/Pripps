package com.company.model;

import javax.imageio.ImageIO;
import java.io.IOException;


public class Player extends Entity {


    private int unitCounter, unitNum;

    public Player(){
        this.speed = 4;
        this.size = 0.8;
        this.unitCounter = 0;
        this.unitNum = 1;
    }

    public void setMovementImages(){

        String tempDir = this.direction;
            if(this.unitCounter > 30){

                if(this.unitNum == 1){
                    this.unitNum = 2;
                } else if(this.unitNum == 2){
                    this.unitNum = 1;
                }
                try{
                    if(tempDir == "northEast" || tempDir == "southEast"){
                        tempDir = "east";
                    } else if(tempDir == "northWest" || tempDir == "southWest"){
                        tempDir = "west";
                    }

                    this.unitImage = ImageIO.read(getClass().getResourceAsStream("/Entities/" + tempDir + Integer.toString(unitNum) + ".png"));

                } catch(IOException e){
                    System.out.println(e.getMessage());
                }

            }
            this.unitCounter++;
    }



}