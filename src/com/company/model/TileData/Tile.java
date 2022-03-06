package com.company.model.TileData;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author - Emil Berzelius
 */
public class Tile {
    /**
     * The tile class hold the tile image as well as conditions unique for the tile number.
     */
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
