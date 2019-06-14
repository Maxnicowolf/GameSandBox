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
    protected double[] bounds;
    protected boolean collision;
    protected boolean hasPointer = false;

    public void handOverGameField(GameField g){
        this.gameField = g;
    }

    public Entity(double x, double y, boolean hasCollision){
        this.collision = hasCollision;
        this.x = x;
        this.y = y;
        this.dir = new Vector<>();
        this.dir.add(0.0);
        this.dir.add(0.0);
    }

    public Entity(double x, double y, Vector<Double> dir, boolean hasCollision){
        this.collision = hasCollision;
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public void turn(double val){
        double angle = Math.atan(this.dir.get(1) / this.dir.get(0)) + val;
        this.dir.set(0, Math.cos(angle * Math.PI / 180.0));
        this.dir.set(1, Math.sin(angle * Math.PI / 180.0));
    }


    public void setDir(double x, double y){
        this.dir.set(0, x);
        this.dir.set(1, y);
    }

    public void setDir(double angle){
        this.dir.set(0, Math.cos(angle * Math.PI / 180));
        this.dir.set(1, -Math.sin(angle * Math.PI / 180));
    }

    public void move(double xval, double yval){
        if (collision) {
            boolean wallCollision = false;
            boolean pitCollision = false;
            for (Block[] bl: gameField.getField()
                 ) {
                for (Block b : bl
                        ) {
                    if (b!=null&&detectCollision(xval,yval,b.getX(),b.getY())){
                        if (b.getID()==1) wallCollision = true;
                        if (b.getID()==2) pitCollision = true;
                    }
                }
            }
            //TODO Kollision Optimieren
            if(!wallCollision) {
            this.x += xval;
            this.y += yval;
            if (pitCollision) System.out.println("Aaaahh!");
            }
        } else {
            this.x += xval;
            this.y += yval;
        }
    }

    public boolean detectCollision(double xdir, double ydir, int blockX, int blockY) {
        boolean result = false;
        try {
            if (gameField.getField()[blockX][blockY].interacts()) {
                if (((this.getX()+xdir>=blockX&&this.getX()+xdir<blockX+1)&&(this.getY()+ydir>=blockY&&this.getY()+ydir<blockY+1))
                        ||((this.getX()+xdir+1>=blockX&&this.getX()+1+xdir<blockX+1)&&(this.getY()+ydir>=blockY&&this.getY()+ydir<blockY+1))
                        ||((this.getX()+xdir>=blockX&&this.getX()+xdir<blockX+1)&&(this.getY()+1+ydir>=blockY&&this.getY()+1+ydir<blockY+1))
                        ||((this.getX()+xdir+1>=blockX&&this.getX()+1+xdir<blockX+1)&&(this.getY()+1+ydir>=blockY&&this.getY()+1+ydir<blockY+1)))
                    result= true;
            } else {
                result=false;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            result = true;
        }
        return result;
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

    public Vector<Double> getDir() {
        return dir;
    }
}
