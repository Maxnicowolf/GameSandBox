package com.four_twoProductions.portal2D;

//bearbeitet von 4.2

import com.four_twoProductions.portal2D.entities.*;
import com.four_twoProductions.portal2D.obstacles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameField extends JPanel {

    public int HORIZONTAL = 750;
    public int VERTICAL = 500;
    public static double VELOCITY = 0.2;
    private Block[][] field;
    private int width;
    private int height;
    private int blockSize;
    public Player p;

    public Block[][] getField() {
        return field;
    }

    public GameField(int width, int height) {
        this.width = width;
        this.height = height;
        field = new Block[width][height];
        for (int i = 0; i < field.length; i++){
            field[i][0] = new BoundsBlock(i, 0);
            field[i][field[0].length - 1] = new BoundsBlock(i, field[0].length - 1);
        }
        for (int i = 0; i < field[0].length; i++){
            field[0][i] = new BoundsBlock(0, i);
            field[field.length - 1][i] = new BoundsBlock(field.length - 1, i);
        }
        calculateBlockSize();
    }
    public void calculateBlockSize() {
        blockSize = Math.min((HORIZONTAL - 50) / width, (VERTICAL - 45) / height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        g.drawRect( 10,10, blockSize * width, blockSize * height);

        for (int x = 0; x < this.field.length; x++) {
            for (int y = 0; y < this.field[0].length; y++) {
                if (field[x][y] != null) {
                    switch (field[x][y].getID()) {
                        case 1:
                            g.setColor(Color.black);
                            g.fillRect(x * blockSize + 10, y * blockSize + 10, blockSize, blockSize);
                            break;
                        case 2:
                            g.setColor(Color.black);
                            g.fillOval(x * blockSize + 10, y * blockSize + 10, blockSize, blockSize);
                            break;
                        case 3:
                            g.setColor(Color.black);
                            g.drawRect(x * blockSize + 10, y * blockSize + 10, blockSize, blockSize);
                            break;
                    }
                }
            }
        }

        if (this.p != null) {
            g.drawRect((int) (p.getX() * blockSize + 10), (int) (p.getY() * blockSize + 10), blockSize, blockSize);
            g.setColor(Color.red);
            g.fillRect((int) (p.getX() * blockSize + 10), (int) (p.getY() * blockSize + 10), blockSize, blockSize); // Spieler platzieren
            if (p.gethasPointer()) {
                g.setColor(Color.black);
                g.drawLine((int) (p.getX() * blockSize + blockSize / 2.0) + 10, (int) (p.getY() * blockSize + blockSize / 2.0) + 10, (int) (p.getX() * blockSize + blockSize / 2.0 + p.getDir().get(0) * blockSize) + 10, (int) (p.getY() * blockSize + blockSize / 2.0 + p.getDir().get(1) * blockSize) + 10);
            }
        }
    }

    public void addObstacle(int x, int y, int ID) {
        switch (ID) {
            case 1:
                field[x][y] = new Wall(x, y);
                break;
            case 2:
                field[x][y] = new Pit(x,y);
                break;
        }
    }

    public void addPlayer(Player p){
        this.p = p;
        this.p.handOverGameField(this);
    }

    public KeyListener keyListener = new KeyListener() {
        @Override
        public void keyPressed(KeyEvent e) {
            String key = Character.toString(e.getKeyChar());
            if (key.equals("w")) p.move(0,-VELOCITY);
            if (key.equals("a")) p.move(-VELOCITY,0);
            if (key.equals("s")) p.move(0,VELOCITY);
            if (key.equals("d")) p.move(VELOCITY,0);
            e.getComponent().repaint();
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

        @Override
        public void keyTyped(KeyEvent e) {

        }
    };

}



