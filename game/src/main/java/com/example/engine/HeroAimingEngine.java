package com.example.engine;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

import com.example.models.Hero;

public class HeroAimingEngine extends MouseAdapter {

    private Hero hero;
    private int xCursor;
    private int yCursor;

    public HeroAimingEngine(Hero hero) {
        this.hero = hero;
    }

    public void moveGun(double xCursor, double yCursor) {
        if (hero != null) {
            double xDistance = (hero.getX() + hero.getSize() / 2) - xCursor;
            double yDistance = (hero.getY() + hero.getSize() / 2) - yCursor;
            double degree = Math.atan(yDistance / xDistance);
            if (xCursor < hero.getX() + hero.getSize() / 2) {
                rotateGun(degree, true);
            } else {
                rotateGun(Math.atan(yDistance / xDistance), false);
            }
        }
    }

    public void rotateGun(double degrees, boolean inversed) {
        double rotationRequired = degrees + Math.PI / 2;
        if (inversed) {
            rotationRequired += Math.PI;
        }
        if (degrees == -Math.PI / 2) {
            rotationRequired = Math.PI;
        }
        if (degrees == Math.PI / 2) {
            rotationRequired = 0;
        }
        double locationX = hero.getGunImageHolder().getBufferedSprite().getWidth() / 2;
        double locationY = hero.getGunImageHolder().getBufferedSprite().getHeight() / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        hero.setCurrentGunAT(new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR));
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        moveGun(e.getX(), e.getY());
        this.xCursor = e.getX();
        this.yCursor = e.getY();
    }

    public int getxCursor() {
        return xCursor;
    }

    public int getyCursor() {
        return yCursor;
    }
}
