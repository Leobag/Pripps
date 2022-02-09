package com.company.model;

import java.awt.geom.Point2D;

public class Player {
    private Point2D.Double position;
    private final double size = 0.5;
    public double speed = 4;

    public double getSize() {
        return size;
    }
    public Point2D.Double getPosition() {
        return position;
    }
    public void setPosition(double x, double y) {
        this.position = new Point2D.Double(x, y);
    }
}