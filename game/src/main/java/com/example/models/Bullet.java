package com.example.models;

import java.awt.image.AffineTransformOp;
import com.example.engine.BulletEngine;
import com.example.engine.Vector2D;

public class Bullet {

    private Vector2D screenDimensionsVector;
    private Vector2D position;
    private Vector2D speed;
    private ImageHolder bodyImageHolder;
    private AffineTransformOp at;
    private Thread liveThread;
    private boolean alive;
    private Vector2D directionVector;
    private static final String BODY_SPRITE = "/img/greenBullet.png";

    public Bullet(Vector2D directionVector, double degrees, boolean b, Vector2D position,
            Vector2D screenDimensionsVector) {
        this.position = position;
        this.screenDimensionsVector = screenDimensionsVector;
        this.directionVector = directionVector;
        this.speed = new Vector2D(5, 5);
        initSprites();
        new BulletEngine(this, degrees, b);
        initLive();
    }

    private void initLive() {
        alive = true;
        liveThread = new Thread(() -> {
            while (alive) {

                try {
                    Thread.sleep(60);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        liveThread.start();
    }

    public boolean move(double dt, double rightBoundary, double bottomBoundary) {
            position.add(directionVector.scaleVector(dt));
            return alive =(position.getX() > 0 && position.getX() < rightBoundary) && 
                (position.getY() > 0 && position.getY() < bottomBoundary);
    }

    private void initSprites() {
        bodyImageHolder = new ImageHolder(BODY_SPRITE,
                (int) getSize());
    }

    public double getSize() {
        return (screenDimensionsVector.getX() + screenDimensionsVector.getY()) / 50;
    }

    public ImageHolder getBodyImageHolder() {
        return bodyImageHolder;
    }

    public Vector2D getPosition() {
        return position;
    }

    public Vector2D getSpeed() {
        return speed;
    }

    public AffineTransformOp getCurrentAt() {
        return at;
    }

    public void setCurrentAt(AffineTransformOp at) {
        this.at = at;
    }

    public double getX() {
        return position.getX();
    }

    public double getY() {
        return position.getY();
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }
}
