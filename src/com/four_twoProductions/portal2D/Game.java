package com.four_twoProductions.portal2D;

import com.four_twoProductions.portal2D.entities.Player;
/*
import com.studiohartman.jamepad.ControllerManager;
import com.studiohartman.jamepad.ControllerState;
*/
import javax.swing.*;

import static com.four_twoProductions.portal2D.GameField.HORIZONTAL;
import static com.four_twoProductions.portal2D.GameField.VERTICAL;

public class Game {
    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Portal2D");
        frame.setSize(HORIZONTAL, VERTICAL);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        GameField field = new GameField(20, 13);
        frame.addKeyListener(field.keyListener);
        field.addObstacle(10, 10, 1);
        field.addObstacle(11, 10, 1);
        field.addObstacle(12, 10, 1);
        field.addObstacle(13, 10, 1);
        field.addObstacle(12,12,2);
        field.addPlayer(new Player(0, 0));
        frame.add(field);
        frame.setVisible(true);

        while (true){
            Thread.sleep(50);
            field.repaint();
        }

    }
}
