package com.example.models;

import java.awt.image.AffineTransformOp;

import com.example.engine.HeroAimingEngine;
import com.example.engine.HeroMovementEngine;
import com.example.engine.HeroShotEngine;
import com.example.engine.Vector2D;

public class Hero {

    private Vector2D screenDimensionsVector;
    private Vector2D position;
    private Vector2D speed;
    private Vector2D directionVector;
    private GameManager gm;
    private AffineTransformOp currentGunAT;
    private AffineTransformOp currentBodyAt;
    private HeroAimingEngine heroMouseEngine;
    private HeroShotEngine heroShotEngine;
    private HeroMovementEngine heroMovementEngine;
    private ImageHolder gunImageHolder;
    private ImageHolder bodyImageHolder;
    private static final String GUN_SPRITE = "/img/gunTank3.png";
    private static final String BODY_SPRITE = "/img/bodyTank1.png";

    public Hero(Vector2D position, Vector2D speed, Vector2D screenDimensionsVector, GameManager gm) {
        this.position = position;
        this.speed = speed;
        this.screenDimensionsVector = screenDimensionsVector;
        this.gm = gm;
        initSprites();
        this.directionVector = new Vector2D((getX() + bodyImageHolder.getBufferedSprite().getWidth() / 2) - 2
        , getY() - 100);
        heroMouseEngine = new HeroAimingEngine(this);
        heroShotEngine = new HeroShotEngine(this);
        heroMovementEngine = new HeroMovementEngine(this);
        this.heroMovementEngine.rotateBodyRight(0);
    }

    private void initSprites() {
        gunImageHolder = new ImageHolder(GUN_SPRITE, (int) getSize());
        bodyImageHolder = new ImageHolder(BODY_SPRITE, (int) getSize());
    }

    public double getSize() {
        return (screenDimensionsVector.getX() + screenDimensionsVector.getY()) / 70;
    }

    public Vector2D getPosition() {
        return position;
    }

    public Vector2D getSpeed() {
        return speed;
    }

    public double getX() {
        return position.getX();
    }

    public double getY() {
        return position.getY();
    }

    public ImageHolder getGunImageHolder() {
        return gunImageHolder;
    }

    public ImageHolder getBodyImageHolder() {
        return bodyImageHolder;
    }

    public Vector2D getCenter() {
        return new Vector2D(getX() + getSize() / 2, getY() + getY() / 2);
    }

    public AffineTransformOp getCurrentGunAT() {
        return currentGunAT;
    }

    public void setCurrentGunAT(AffineTransformOp currentGunAT) {
        this.currentGunAT = currentGunAT;
    }

    public HeroAimingEngine getHeroMouseEngine() {
        return heroMouseEngine;
    }

    public HeroShotEngine getHeroShotEngine() {
        return heroShotEngine;
    }

    public void shot(Vector2D directionVector, double degrees, boolean b) {
        System.out.println("normalized: " + directionVector.toString());
        gm.shot(directionVector, degrees, b);
    }

    public void move() {
        if (heroMovementEngine.isMovingUp()) {
            double dt = System.currentTimeMillis() - heroMovementEngine.getUpMovementTime();
            moveUp(dt);
        }
        if (heroMovementEngine.isMovingDown()) {
            double dt = System.currentTimeMillis() - heroMovementEngine.getDownMovementTime();
            moveDown(dt);
        }
        if (heroMovementEngine.isMovingRight()) {
            double dt = System.currentTimeMillis() - heroMovementEngine.getRightMovementTime();
            moveRight(dt);
        }
        if (heroMovementEngine.isMovingLeft()) {
            double dt = System.currentTimeMillis() - heroMovementEngine.getLeftMovementTime();
            moveLeft(dt);
        }
    }

    public void moveUp(double dt) {
        Vector2D moveUpVector2D = new Vector2D(0, -1);
        position.add(moveUpVector2D.normalize().scaleVector(dt / 5));
        heroMovementEngine.setUpMovementTime(System.currentTimeMillis());
    }

    public void moveDown(double dt) {
        Vector2D moveDownVector2D = new Vector2D(0, 1);
        position.add(moveDownVector2D.normalize().scaleVector(dt / 5));
        heroMovementEngine.setDownMovementTime(System.currentTimeMillis());
    }

    public void moveRight(double dt) {
        heroMovementEngine.rotateBodyRight(dt);
        heroMovementEngine.setRightMovementTime(System.currentTimeMillis());
    }

    public void moveLeft(double dt) {
        heroMovementEngine.rotateBodyLeft(dt);
        heroMovementEngine.setLeftMovementTime(System.currentTimeMillis());
    }

    public HeroMovementEngine getHeroMovementEngine() {
        return heroMovementEngine;
    }

    public Vector2D getDirectionVector() {
        return directionVector;
    }

    public void setCurrentBodyAt(AffineTransformOp currentBodyAt) {
        this.currentBodyAt = currentBodyAt;
    }

    public AffineTransformOp getCurrentBodyAt() {
        return currentBodyAt;
    }

    public void setDirectionVector(Vector2D directionVector) {
        this.directionVector = directionVector;
    }

}
