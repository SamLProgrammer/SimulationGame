package com.example.engine;

import java.awt.event.MouseAdapter;
import com.example.models.Hero;
import java.awt.event.MouseEvent;

public class HeroShotEngine extends MouseAdapter{

    private Hero hero;
    private int xCursor;
    private int yCursor;

    public HeroShotEngine(Hero hero) {
        this.hero = hero;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        xCursor = e.getX();
        yCursor = e.getY();
        double xDistance = (hero.getX() + hero.getSize() / 2) - xCursor;
        double yDistance = (hero.getY() + hero.getSize() / 2) - yCursor;
        Vector2D directionVector = new Vector2D(xCursor - (hero.getX() + hero.getSize() / 2), yCursor - (hero.getY() + hero.getSize() / 2));
        if (xCursor < hero.getX() + hero.getSize() / 2) {
            hero.shot(directionVector.normalized(), Math.atan(yDistance / xDistance), true);
        } else {
            hero.shot(directionVector.normalized(), Math.atan(yDistance / xDistance), false);
        }
    }
}
