package com.company.model;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;


public class Entity {
    public Point2D.Double position;
    public double size;
    public double speed;
    public boolean collisionOn = false;
    private int unitCounter, unitNum;
    public BufferedImage unitImage;
    public CollisionCheck collisionCheck;
    private Rectangle hitBox = new Rectangle();

    String direction = "down";

    public double getSize() {
        return size;
    }

    public Point2D.Double getPosition() {
        return position;
    }

    public void setPosition(double x, double y) {
        this.position = new Point2D.Double(x, y);
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