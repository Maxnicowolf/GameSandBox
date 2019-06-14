package com.four_twoProductions.portal2D.entities;

import com.studiohartman.jamepad.ControllerManager;
import com.studiohartman.jamepad.ControllerState;

import java.util.Vector;

//Klasse für Spieler mit Controller

public class Player extends Entity{
    private boolean controllerTest = true;

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

                    if (bounds != null) {
                        if (x + state[0].leftStickX <= bounds[0] && x + state[0].leftStickX >= 0)
                            move(state[0].leftStickX, 0);
                        else
                            setX(x + state[0].leftStickX > bounds[0] ?  bounds[0] : 0);
                        if (y + state[0].leftStickY * -1 <= bounds[1] && y + state[0].leftStickY * -1 >= 0)
                            move(0, state[0].leftStickY * -1);
                        else
                            setY(y + state[0].leftStickY * -1 > bounds[1] ?  bounds[1] : 0);
                        setDir(manager.getState(0).rightStickX, manager.getState(0).rightStickY * -1);
                    } else {
                        move(state[0].leftStickX,state[0].leftStickY * -1);
                    }
                }
            }
        };
        controller.start();
    }

}
