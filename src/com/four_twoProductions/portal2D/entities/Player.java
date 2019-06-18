package com.four_twoProductions.portal2D.entities;

import com.studiohartman.jamepad.ControllerManager;
import com.studiohartman.jamepad.ControllerState;

import java.util.Vector;

//Klasse für Spieler mit Controller

public class Player extends Entity{
    private boolean controllerTest = true;
    private boolean hasPointer = true;

    public Player(double x, double y){
        super(x, y, true);
        controller();
    }

    public Player(double x, double y, Vector<Double> dir) {
        super(x, y, dir, true);
        controller();
    }

    private void controller(){
        ControllerManager manager = new ControllerManager();
        manager.initSDLGamepad();
        final ControllerState[] state = {manager.getState(0)};
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
                    state[0] = manager.getState(0);
                    move(state[0].leftStickX,state[0].leftStickY * -1);
                    setDir(state[0].rightStickAngle);
                }
            }
        };
        controller.start();
    }

    public boolean gethasPointer(){
        return hasPointer;
    }

}
