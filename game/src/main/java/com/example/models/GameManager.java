package com.example.models;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import com.example.engine.Vector2D;

public class GameManager {

    private Hero hero;
    private Vector2D screenDimensions;
    private CopyOnWriteArrayList<Bullet> bullets;
    private ImageHolder bulletBodyImageHolder;
    private static final String BODY_SPRITE = "/img/greenBullet.png";

    public GameManager() {
        initProperties();
        initComponents();
        initHero();
    }

    private void initProperties() {
        this.screenDimensions = new Vector2D(Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
                Toolkit.getDefaultToolkit().getScreenSize().getHeight());
        bullets = new CopyOnWriteArrayList<>();
    }

    private void initComponents() {
        initHero();
        initSprites();
    }

    private void initSprites() {
        bulletBodyImageHolder = new ImageHolder(BODY_SPRITE,
                (int) ((screenDimensions.getX() + screenDimensions.getY()) / 50));
    }

    private void initHero() {
        hero = new Hero(new Vector2D(getScreenWidth() / 2, getScreenWidth() / 2),
                new Vector2D(10.0, 10.0),
                new Vector2D(getScreenWidth(), getScreenHeight()),
                this);
    }

    public Hero getHero() {
        return hero;
    }

    public double getScreenHeight() {
        return screenDimensions.getX();
    }

    public double getScreenWidth() {
        return screenDimensions.getY();
    }

    public void shot(Vector2D directionVector, double degrees, boolean b) {
        bullets.add(new Bullet(
                directionVector,
                degrees,
                b,
                new Vector2D(hero.getX() - bulletBodyImageHolder.getBufferedSprite().getWidth() / 6,
                        hero.getY() - bulletBodyImageHolder.getBufferedSprite().getHeight() / 6),
                screenDimensions));
    }

    public CopyOnWriteArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void move(double dt) {
        moveBullets(dt);
        moveHero();
    }

    private void moveHero() {
        hero.move();
    }

    private void moveBullets(double dt) {
        ArrayList<Bullet> bulletsToRemove = new ArrayList<>();
        for (Bullet bullet : bullets) {
            if (!bullet.move(dt, screenDimensions.getX(), screenDimensions.getY())) {
                bulletsToRemove.add(bullet);
            }
        }
        for (Bullet bullet : bulletsToRemove) {
            bullets.remove(bullet);
        }
    }

}
