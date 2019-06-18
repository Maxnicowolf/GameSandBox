package com.four_twoProductions.portal2D.obstacles;

public class Block {
    private boolean interaction;
    private boolean solid;
    private int ID;
    private int x;
    private int y;


    public Block(int x, int y, int ID, boolean interaction, boolean solid){
        this.interaction = interaction;
        this.x = x;
        this.y = y;
        this.ID = ID;
        this.solid = solid;
    }

    public void action(){}

    public boolean interacts() {
        return interaction;
    }

    public boolean isSolid() {
        return solid;
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
