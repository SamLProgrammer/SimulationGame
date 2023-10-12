package com.example.controller;

import com.example.engine.TimeFrame;
import com.example.models.GameManager;
import com.example.views.MainFrame;

public class Controller {

    private MainFrame mf;
    private GameManager gm;
    private TimeFrame tf;

    public Controller() {
        initComponents();
        initGameThread();
    }

    private void initGameThread() {
        tf = new TimeFrame();
        new Thread(() -> { 
            while (true) {
                gm.move(tf.getDT());
                mf.refresh();
                try {
                    Thread.sleep(60); 
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break; 
                }
            }
         }).start();
    }

    private void initComponents() {
        gm = new GameManager();
        mf = new MainFrame(gm);
    }

}
