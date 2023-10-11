package com.example.controller;

import java.awt.Toolkit;

import com.example.engine.Vector2D;
import com.example.models.Hero;
import com.example.views.MainFrame;

public class Controller {

    private Hero hero;
    private MainFrame mf;
    private double screenWidth;
    private double screenHeight;

    public Controller() {
        initComponents();
        initGameThread();
    }

    private void initGameThread() {
        new Thread(() -> { 
            while (true) {
                mf.refresh(hero);
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
        this.screenWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        this.screenHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        hero = new Hero(new Vector2D(screenWidth / 2, screenHeight / 2),
                new Vector2D(10.0, 10.0),
                new Vector2D(screenWidth, screenHeight));
        mf = new MainFrame(screenWidth, screenHeight, hero);
    }

}
