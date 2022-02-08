package com.company.model;

import java.awt.*;
import java.awt.geom.Point2D;

public class Player {
    private Point2D.Double position;

    public Point2D.Double getPosition() {
        return position;
    }
    public void setPosition(double x, double y) {
        this.position = new Point2D.Double(x, y);
    }
}