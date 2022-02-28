package com.company.model.TileData;

import java.awt.image.BufferedImage;


public class Tile {

    public BufferedImage image;
    private boolean collision = false;
    private boolean nextMap = false;

    public boolean getCollision(){
        return collision;
    }

    public void setCollision(boolean collision){
        this.collision = collision;
    }


    public void setNextMapBool(boolean nextMap){
        this.nextMap = nextMap;
    }

    public boolean getNextMapBool(){
        return this.nextMap;
    }


}
