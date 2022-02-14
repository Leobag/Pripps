package com.company.model;

import java.awt.geom.Point2D;

public class Entity {
    public Point2D.Double position;
    public double size;
    public double speed;

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
