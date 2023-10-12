package com.example.engine;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

import com.example.models.Hero;

public class HeroMovementEngine extends KeyAdapter {

    private Hero hero;
    private boolean movingUp;
    private boolean movingDown;
    private boolean movingRight;
    private boolean movingLeft;
    private long upMovementTime;
    private long downMovementTime;
    private long rightMovementTime;
    private long leftMovementTime;
    private Vector2D direction;
    private double rotationDegrees;
    private boolean rotatingRight;

    public HeroMovementEngine(Hero hero) {
        this.hero = hero;
        this.rotatingRight = false;
    }

    public void rotateBodyRight(double dt) {
        if (hero != null) {
            double degrees = Math.toRadians(dt * 0.05);
            double locationX = hero.getBodyImageHolder().getBufferedSprite().getWidth() / 2;
            double locationY = hero.getBodyImageHolder().getBufferedSprite().getHeight() / 2;
            AffineTransform tx = AffineTransform.getRotateInstance(rotationDegrees + degrees, locationX, locationY);
            hero.setCurrentBodyAt(new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR));
            rotationDegrees += degrees;
            double h = 2;

            double a = rotationDegrees;
            double x = h * Math.cos(a);
            double y = h * Math.sin(a);

            direction = new Vector2D(x, y);
            direction = direction.normalize().scale(100);
            hero.setDirectionVector(direction);
        }
    }

    public void rotateBodyLeft(double dt) {
        if (hero != null) {
            double degrees = Math.toRadians(-dt * 0.05);
            double locationX = hero.getBodyImageHolder().getBufferedSprite().getWidth() / 2;
            double locationY = hero.getBodyImageHolder().getBufferedSprite().getHeight() / 2;
            AffineTransform tx = AffineTransform.getRotateInstance(rotationDegrees + degrees, locationX, locationY);
            hero.setCurrentBodyAt(new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR));
            rotationDegrees += degrees;

            double h = 2;
            double a = rotationDegrees;
            double x = h * Math.cos(a);
            double y = h * Math.sin(a);

            direction = new Vector2D(x, y);
            direction = direction.normalize().scale(100);
            hero.setDirectionVector(direction);
        }
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
        double locationX = hero.getBodyImageHolder().getBufferedSprite().getWidth() / 2;
        double locationY = hero.getBodyImageHolder().getBufferedSprite().getHeight() / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        hero.setCurrentGunAT(new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR));
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (!movingUp) {
                    upMovementTime = System.currentTimeMillis();
                    movingUp = true;
                }
                break;
            case KeyEvent.VK_DOWN:
                if (!movingDown) {
                    movingDown = true;
                    downMovementTime = System.currentTimeMillis();
                }
                break;
            case KeyEvent.VK_LEFT:
                if (!movingLeft) {
                    movingLeft = true;
                    leftMovementTime = System.currentTimeMillis();
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (!movingRight) {
                    movingRight = true;
                    rightMovementTime = System.currentTimeMillis();
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                movingUp = false;
                break;
            case KeyEvent.VK_DOWN:
                movingDown = false;
                break;
            case KeyEvent.VK_LEFT:
                movingLeft = false;
                break;
            case KeyEvent.VK_RIGHT:
                movingRight = false;
                break;
        }
    }

    public long getLeftMovementTime() {
        return leftMovementTime;
    }

    public long getRightMovementTime() {
        return rightMovementTime;
    }

    public long getDownMovementTime() {
        return downMovementTime;
    }

    public long getUpMovementTime() {
        return upMovementTime;
    }

    public void setUpMovementTime(long upMovementTime) {
        this.upMovementTime = upMovementTime;
    }

    public void setDownMovementTime(long downMovementTime) {
        this.downMovementTime = downMovementTime;
    }

    public void setRightMovementTime(long rightMovementTime) {
        this.rightMovementTime = rightMovementTime;
    }

    public void setLeftMovementTime(long leftMovementTime) {
        this.leftMovementTime = leftMovementTime;
    }

    public boolean isMovingDown() {
        return movingDown;
    }

    public boolean isMovingLeft() {
        return movingLeft;
    }

    public boolean isMovingRight() {
        return movingRight;
    }

    public boolean isMovingUp() {
        return movingUp;
    }

    public double getRotationDegrees() {
        return rotationDegrees;
    }

    public double getRotationDegreesInDegrees() {
        return Math.toDegrees(rotationDegrees) % 360;
    }

    public void setRotatingRight(boolean rotatingRight) {
        this.rotatingRight = rotatingRight;
    }

    public boolean isRotatingRight() {
        return rotatingRight;
    }
}
