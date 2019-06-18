package com.four_twoProductions.portal2D.entities;

import com.four_twoProductions.portal2D.GameField;
import com.four_twoProductions.portal2D.obstacles.Block;
import com.studiohartman.jamepad.ControllerManager;
import com.studiohartman.jamepad.ControllerState;

import java.util.Vector;

//Parentclass aller Entities

public class Entity {
    protected GameField gameField;
    protected double x;
    protected double y;
    protected Vector<Double> dir;
    protected boolean hasPointer = false;
    protected boolean collision = false;

    public void handOverGameField(GameField g) {
        this.gameField = g;
    }

    public Entity(double x, double y, boolean hasCollision) {
        this.collision = hasCollision;
        this.x = x;
        this.y = y;
        this.dir = new Vector<>();
        this.dir.add(0.0);
        this.dir.add(0.0);
    }

    public Entity(double x, double y, Vector<Double> dir, boolean hasCollision) {
        this.collision = hasCollision;
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public void turn(double val) {
        double angle = Math.atan(this.dir.get(1) / this.dir.get(0)) + val;
        this.dir.set(0, Math.cos(angle * Math.PI / 180.0));
        this.dir.set(1, Math.sin(angle * Math.PI / 180.0));
    }


    public void setDir(double x, double y) {
        this.dir.set(0, x);
        this.dir.set(1, y);
    }

    public void setDir(double angle) {
        this.dir.set(0, Math.cos(angle * Math.PI / 180));
        this.dir.set(1, -Math.sin(angle * Math.PI / 180));
    }

    public double getX() {
        return x;
    }

    public void move(double xval, double yval) {

        boolean xv = true;
        boolean yv = true;

        if (xval >= 0 && yval >= 0) {
            if (testCollision(xval, yval, this.gameField.getField()[(int) Math.floor(this.x)][(int) Math.ceil(this.y + 1)])) {
                this.y = this.gameField.getField()[(int) Math.floor(this.x)][(int) Math.ceil(this.y + 1)].getY() - 1;
                yv = false;
            }
            if (testCollision(xval, yval, this.gameField.getField()[(int) Math.ceil(this.x)][(int) Math.ceil(this.y + 1)])) {
                this.y = this.gameField.getField()[(int) Math.ceil(this.x)][(int) Math.ceil(this.y + 1)].getY() - 1;
                yv = false;
            }
            if (testCollision(xval, yval, this.gameField.getField()[(int) Math.ceil(this.x + 1)][(int) Math.floor(this.y)])) {
                this.x = this.gameField.getField()[(int) Math.ceil(this.x + 1)][(int) Math.floor(this.y)].getX() - 1;
                xv = false;
            }
            if (testCollision(xval, yval, this.gameField.getField()[(int) Math.ceil(this.x + 1)][(int) Math.ceil(this.y)])) {
                this.x = this.gameField.getField()[(int) Math.ceil(this.x + 1)][(int) Math.ceil(this.y)].getX() - 1;
                xv = false;
            }
        } else if (xval >= 0 && yval <= 0) {
            if (testCollision(xval, yval, this.gameField.getField()[(int) Math.floor(this.x)][(int) Math.floor(this.y - 1)])) {
                this.y = this.gameField.getField()[(int) Math.floor(this.x)][(int) Math.floor(this.y - 1)].getY() + 1;
                yv = false;
            }
            if (testCollision(xval, yval, this.gameField.getField()[(int) Math.ceil(this.x)][(int) Math.floor(this.y - 1)])) {
                this.y = this.gameField.getField()[(int) Math.ceil(this.x)][(int) Math.floor(this.y - 1)].getY() + 1;
                yv = false;
            }
            if (testCollision(xval, yval, this.gameField.getField()[(int) Math.ceil(this.x + 1)][(int) Math.floor(this.y)])) {
                this.x = this.gameField.getField()[(int) Math.ceil(this.x + 1)][(int) Math.floor(this.y)].getX() - 1;
                xv = false;
            }
            if (testCollision(xval, yval, this.gameField.getField()[(int) Math.ceil(this.x + 1)][(int) Math.ceil(this.y)])) {
                this.x = this.gameField.getField()[(int) Math.ceil(this.x + 1)][(int) Math.ceil(this.y)].getX() - 1;
                xv = false;
            }
        } else if (xval <= 0 && yval >= 0) {
            if (testCollision(xval, yval, this.gameField.getField()[(int) Math.floor(this.x)][(int) Math.ceil(this.y + 1)])) {
                this.y = this.gameField.getField()[(int) Math.floor(this.x)][(int) Math.ceil(this.y + 1)].getY() - 1;
                yv = false;
            }
            if (testCollision(xval, yval, this.gameField.getField()[(int) Math.ceil(this.x)][(int) Math.ceil(this.y + 1)])) {
                this.y = this.gameField.getField()[(int) Math.ceil(this.x)][(int) Math.ceil(this.y + 1)].getY() - 1;
                yv = false;
            }
            if (testCollision(xval, yval, this.gameField.getField()[(int) Math.floor(this.x - 1)][(int) Math.floor(this.y)])) {
                this.x = this.gameField.getField()[(int) Math.floor(this.x - 1)][(int) Math.floor(this.y)].getX() + 1;
                xv = false;
            }
            if (testCollision(xval, yval, this.gameField.getField()[(int) Math.floor(this.x - 1)][(int) Math.ceil(this.y)])) {
                this.x = this.gameField.getField()[(int) Math.floor(this.x - 1)][(int) Math.ceil(this.y)].getX() + 1;
                xv = false;
            }
        } else if (xval <= 0 && yval <= 0) {
            if (testCollision(xval, yval, this.gameField.getField()[(int) Math.floor(this.x)][(int) Math.floor(this.y - 1)])) {
                this.y = this.gameField.getField()[(int) Math.floor(this.x)][(int) Math.floor(this.y - 1)].getY() + 1;
                yv = false;
            }
            if (testCollision(xval, yval, this.gameField.getField()[(int) Math.ceil(this.x)][(int) Math.floor(this.y - 1)])) {
                this.y = this.gameField.getField()[(int) Math.ceil(this.x)][(int) Math.floor(this.y - 1)].getY() + 1;
                yv = false;
            }
            if (testCollision(xval, yval, this.gameField.getField()[(int) Math.floor(this.x - 1)][(int) Math.floor(this.y)])) {
                this.x = this.gameField.getField()[(int) Math.floor(this.x - 1)][(int) Math.floor(this.y)].getX() + 1;
                xv = false;
            }
            if (testCollision(xval, yval, this.gameField.getField()[(int) Math.floor(this.x - 1)][(int) Math.ceil(this.y)])) {
                this.x = this.gameField.getField()[(int) Math.floor(this.x - 1)][(int) Math.ceil(this.y)].getX() + 1;
                xv = false;
            }

        }

        if (xv) this.x += xval;
        if (yv) this.y += yval;
    }

    private boolean testCollision(double xval, double yval, Block b) {
        //Ecke oben links
        boolean hit = false;
        if (b != null) {
            if (b.interacts() ||  b.isSolid()) {
                if (Math.floor(this.x + xval) == b.getX() && Math.floor(this.y + yval) == b.getY())
                    hit = true;
                    //Ecke oben rechts
                else if (Math.floor(this.x + 1 + xval) == b.getX() && Math.floor(this.y + yval) == b.getY())
                    hit = true;
                    //Ecke unten links
                else if (Math.floor(this.x + xval) == b.getX() && Math.floor(this.y + 1 + yval) == b.getY())
                    hit = true;
                    //Ecke unten rechts
                else if (Math.floor(this.x + 1 + xval) == b.getX() && Math.floor(this.y + 1 + yval) == b.getY())
                    hit = true;
            }
        }
        if (hit){
            b.action();
            if (b.isSolid())
                return true;
        }
        return false;
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

    public Vector<Double> getDir() {
        return dir;
    }
}

  
