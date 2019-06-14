package com.four_twoProductions.portal2D.obstacles;

public class Block {

    private int ID;
    private int x;
    private int y;

    public Block(int x, int y, int ID){
        this.x = x;
        this.y = y;
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }
}
