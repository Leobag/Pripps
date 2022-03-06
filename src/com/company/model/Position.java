package com.company.model;

import java.io.Serializable;

/**
 * Creates a position object containing a 2D-position on the map.
 * @author - Andy Alavinasab
 */
public class Position implements Serializable {

    private double x;
    private double y;

    /**
     * Constructs the position object using the parameters.
     * @param x - x-value.
     * @param y - y-value.
     */
    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

}
