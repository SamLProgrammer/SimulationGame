package com.example.engine;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

import com.example.models.Hero;

public class HeroMouseEngine extends MouseAdapter {

    private Hero hero;
    private int xCursor;
    private int yCursor;

    public HeroMouseEngine(Hero hero) {
        this.hero = hero;
    }

    public void moveGun(double xCursor, double yCursor) {
        double xDistance = (hero.getX() + hero.getSize() / 2) - xCursor;
        double yDistance = (hero.getY() + hero.getSize() / 2) - yCursor;
        double angle = Math.atan(yDistance / xDistance);
        AffineTransform tx = AffineTransform.getRotateInstance(angle + Math.PI/2, hero.getX(), hero.getY());
        hero.setCurrentAt(new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR));
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // System.out.println(hero.getGunImageHolder().getBufferedSprite().getWidth());
        // System.out.println(hero.getGunImageHolder().getBufferedSprite().getHeight());
        moveGun(e.getX(), e.getY());
        this.xCursor = e.getX();
        this.yCursor = e.getY();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }
}
