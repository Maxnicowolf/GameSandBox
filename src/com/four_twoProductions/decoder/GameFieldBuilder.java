package com.four_twoProductions.decoder;

import com.four_twoProductions.portal2D.*;
import com.four_twoProductions.portal2D.entities.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class GameFieldBuilder {
    private static Scanner sc;
    public static GameField fromPath(String path) {
            File file = new File(path);
            return fromFile(file);

    }
    public static GameField fromFile(File f) {
        GameField gf;
        try {
            String aLine;
            String[] anArray;
            sc = new Scanner(f);
            aLine = sc.nextLine();
            anArray = aLine.split(",");
            if (!anArray[0].equals("d")) throw new FileNotFoundException();
            gf = new GameField(parseInt(anArray[1]), parseInt(anArray[2]));
            if (anArray.length==5){
                gf.HORIZONTAL = parseInt(anArray[3]);
                gf.VERTICAL = parseInt(anArray[4]);
                gf.calculateBlockSize();
            }
            while(sc.hasNextLine()) {

                aLine = sc.nextLine();
                anArray = aLine.split(",");
                switch (anArray[0]) {
                    case "p":
                        gf.addPlayer(new Player(parseDouble(anArray[1]),parseDouble(anArray[2])));
                        break;
                    case "b":
                        gf.addObstacle(parseInt(anArray[1]),parseInt(anArray[2]),parseInt(anArray[3]));
                        break;
                }

            }
            return gf;
        } catch (Exception e) {
            return defaultField();
        }
    }
    public static GameField defaultField() {
        GameField gf = new GameField(30,20);
        gf.addObstacle(10, 8, 1);
        gf.addObstacle(20,3,2);
        gf.addObstacle(10, 10, 1);
        gf.addObstacle(11, 10, 1);
        gf.addObstacle(12, 10, 1);
        gf.addObstacle(13, 10, 1);
        gf.addObstacle(12,12,2);
        gf.addPlayer(new Player(5, 5));
        return gf;
    }
}
