package com.example.models;

import java.awt.image.AffineTransformOp;

import com.example.engine.HeroMouseEngine;
import com.example.engine.Vector2D;

public class Hero {

    private Vector2D screenDimensionsVector;
    private Vector2D position;
    private Vector2D speed;
    private ImageHolder gunImageHolder;
    private static final String GUN_SPRITE = "/img/towerGun.png";
    private AffineTransformOp at;
    private HeroMouseEngine heroMouseEngine;

    public Hero(Vector2D position, Vector2D speed, Vector2D screenDimensionsVector) {
        this.position = position;
        this.speed = speed;
        this.screenDimensionsVector = screenDimensionsVector;
        this.heroMouseEngine = new HeroMouseEngine(this);
        initSprites();
    }

    private void initSprites() {
        gunImageHolder = new ImageHolder(GUN_SPRITE,
                (int) ((screenDimensionsVector.getX() + screenDimensionsVector.getY()) / 30));
    }

    public double getSize() {
        return (screenDimensionsVector.getX() + screenDimensionsVector.getY()) / 30;
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

    public Vector2D getCenter() {
        return new Vector2D(getX() + getSize() / 2, getY() + getY() / 2);
    }

    public AffineTransformOp getCurrentAt() {
        return at;
    }

    public void setCurrentAt(AffineTransformOp at) {
        this.at = at;
    }
    
    public HeroMouseEngine getHeroMouseEngine() {
        return heroMouseEngine;
    }
}
