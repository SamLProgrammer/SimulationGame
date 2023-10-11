package com.example.views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;
import com.example.models.Hero;

public class GamePanel extends JPanel {

    private Hero hero;

    public GamePanel(Hero hero) {
        initProperties(hero);
        initComponents();
    }

    private void initProperties(Hero hero) {
        setBackground(Color.black);
        this.hero = hero;
    }

    private void initComponents() {
        addMouseMotionListener(hero.getHeroMouseEngine());
        setFocusable(true);
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        paintHero(g2);
    }

    private void paintHero(Graphics2D g2) {
        if (hero.getCurrentAt() != null) {
            g2.drawImage(hero.getGunImageHolder().getBufferedSprite(), null, (int) hero.getX(), (int) hero.getY());
            int x = (int) hero.getX();
            int y = (int) hero.getY();

            Image gunImage = hero.getGunImageHolder().getBufferedSprite();

            AffineTransform at = AffineTransform.getTranslateInstance(x, y);
            at.rotate(Math.toRadians(45), gunImage.getWidth(null) / 2, gunImage.getHeight(null) / 2);
            g2.setTransform(at);
            g2.drawImage(gunImage, 100, 100, null);
        }
    }

    public void refresh(Hero hero) {
        this.hero = hero;
        repaint();
    }
}
