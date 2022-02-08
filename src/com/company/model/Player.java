package com.company.model;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Player {
    private Point2D.Double position;
    private final int size = 1;

    public Point2D.Double getPosition() {
        return position;
    }
    public void setPosition(double x, double y) {
        this.position = new Point2D.Double(x, y);
    }

    void drawPlayer(Graphics2D g){
//        g.setColor(Color.BLUE);
//        g.fillRect(getPosition().x, getPosition().y, size, size);
        Rectangle2D player = new Rectangle2D.Double(getPosition().x, getPosition().y, size, size);
        Graphics2D g2 = (Graphics2D) g;
    }

    private void moveEast(){
        setPosition(getPosition().x + 1, getPosition().y);
    }
    private void moveWest(){
        setPosition(getPosition().x - 1, getPosition().y);
    }
    private void moveNorth(){
        setPosition(getPosition().x, getPosition().y + 1);
    }
    private void moveSouth(){
        setPosition(getPosition().x , getPosition().y - 1);
    }
    private void moveNorthEast(){
        setPosition(getPosition().x + 1, getPosition().y + 1);
    }
    private void moveNorthWest(){
        setPosition(getPosition().x - 1, getPosition().y + 1);
    }
    private void moveSouthEast(){
        setPosition(getPosition().x - 1, getPosition().y + 1);
    }
    private void moveSouthWest(){
        setPosition(getPosition().x - 1, getPosition().y - 1);
    }
}