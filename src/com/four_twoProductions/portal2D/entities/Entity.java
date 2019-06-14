package com.four_twoProductions.portal2D.entities;

import java.util.Vector;

//Parentclass aller Entities

public class Entity {
    protected double x;
    protected double y;
    protected Vector<Double> dir;
    protected double[] bounds;

    public Entity(double x, double y){
        this.x = x;
        this.y = y;
        this.dir = new Vector<>();
        this.dir.add(0.0);
        this.dir.add(0.0);
    }

    public Entity(double x, double y, Vector<Double> dir){
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public void turn(double val){
        double angle = Math.atan(this.dir.get(1) / this.dir.get(0) + val);
        this.dir.set(0, Math.cos(angle));
        this.dir.set(1, Math.sin(angle));
    }

    public void setDir(double x, double y){
        this.dir.set(0, x);
        this.dir.set(1, y);
    }

    public void move(double xval, double yval){
        this.x += xval;
        this.y += yval;
    }

    public void addBounds(double x, double y){
        this.bounds = new double[]{x - 1, y - 1};
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
