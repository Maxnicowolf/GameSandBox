package com.four_twoProductions.portal2D;

import javax.swing.*;

import java.io.File;

import static com.four_twoProductions.decoder.ChooseFile.filePath;
import static com.four_twoProductions.decoder.GameFieldBuilder.*;
public class Game {
    public static void main(String[] args) throws InterruptedException {
        GameField field = fromPath(filePath());
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Portal2D");
                frame.setSize(field.HORIZONTAL, field.VERTICAL);
                frame.setResizable(false);
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.addKeyListener(field.keyListener);

                frame.add(field);
                frame.setVisible(true);
            }
        });

        while (true){
            Thread.sleep(50);
            field.repaint();
        }

    }
}
