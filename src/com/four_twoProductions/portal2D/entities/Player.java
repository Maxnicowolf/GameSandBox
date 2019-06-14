package com.four_twoProductions.portal2D.entities;

import com.studiohartman.jamepad.ControllerManager;

import java.util.Vector;

//Klasse für Spieler mit Controller

public class Player extends Entity{
    private boolean controllerTest = true;

    public Player(double x, double y){
        super(x, y);
        controller();
    }

    public Player(double x, double y, Vector<Double> dir) {
        super(x, y, dir);
        controller();
    }

    private void controller(){
        ControllerManager manager = new ControllerManager();
        manager.initSDLGamepad();
        Thread controller = new Thread(){
            @Override
            public void run() {
                super.run();
                while (controllerTest){
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    move(manager.getState(0).leftStickX, manager.getState(0).leftStickY * -1);
                    setDir(manager.getState(0).rightStickX, manager.getState(0).rightStickY);
                }
            }
        };
        controller.start();
    }
}
