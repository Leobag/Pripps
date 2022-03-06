package com.company.model;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * Parent class to enemies, player and win condition object.
 * Stores information which all of these object uses, for example unitImage and hitBox.
 *
 * @author Emil Berzelius
 * @version 06-03-22
 */
public class Entity {
    private Position position;
    public double size;
    public double speed;
    public boolean collisionOn = false;
    public BufferedImage unitImage;
    private Rectangle hitBox = new Rectangle();

    String direction;

    public double getSize() {
        return size;
    }

    public Position getPosition() {
        return position;
    }


    public void setPosition(double x, double y) {
        this.position = new Position(x, y);
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }

    public double getSpeed() {
        return speed;
    }

    public boolean isCollisionOn() {
        return collisionOn;
    }

    public void setCollisionOn(boolean collisionOn) {
        this.collisionOn = collisionOn;
    }

    public BufferedImage getUnitImage(){
        return this.unitImage;
    }

    /**
     * Updates the hitbox of the player.
     *
     * @param x - X-coordinate of top left corner of hitbox.
     * @param y - Y-coordinate of top left corner of hitbox.
     * @param size - the width and height of hitbox.
     */

    public void updateHitBox(int x, int y, int size){
        this.hitBox.x = x;
        this.hitBox.y = y;
        this.hitBox.width = size;
        this.hitBox.height = size;
    }

    public Rectangle getHitBox(){
        return hitBox;
    }


}