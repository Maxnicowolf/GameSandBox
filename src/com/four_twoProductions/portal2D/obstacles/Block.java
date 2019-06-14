package com.four_twoProductions.portal2D.obstacles;

public class Block {
    protected boolean interaction;
    private int ID;
    private int x;
    private int y;

    public Block(int x, int y, int ID, boolean interaction){
        this.interaction = interaction;
        this.x = x;
        this.y = y;
        this.ID = ID;
    }

    public boolean interacts() {
        return interaction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getID() {
        return ID;
    }
}
