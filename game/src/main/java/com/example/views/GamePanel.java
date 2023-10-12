package com.example.views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import com.example.models.Bullet;
import com.example.models.GameManager;

public class GamePanel extends JPanel {

    private GameManager gm;

    public GamePanel(GameManager gm) {
        initProperties(gm);
        initComponents();
    }

    private void initProperties(GameManager gm) {
        setBackground(Color.black);
        this.gm = gm;
    }

    private void initComponents() {
        addMouseMotionListener(gm.getHero().getHeroMouseEngine());
        addMouseListener(gm.getHero().getHeroShotEngine());
        addKeyListener(gm.getHero().getHeroMovementEngine());
        setFocusable(true);
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        paintHero(g2);
        paintbullets(g2);
    }

    private void paintHero(Graphics2D g2) {
        if (gm.getHero() != null) {
            BufferedImage gunBufferedHeroSprite = gm.getHero().getGunImageHolder().getBufferedSprite();
            BufferedImage bodyBufferedHeroSprite = gm.getHero().getBodyImageHolder().getBufferedSprite();
            if (gm.getHero().getCurrentGunAT() != null) {
                g2.drawImage(gm.getHero().getCurrentBodyAt().filter(bodyBufferedHeroSprite, null),
                        (int) gm.getHero().getX(),
                        (int) gm.getHero().getY(), null);
                g2.drawImage(gm.getHero().getCurrentGunAT().filter(gunBufferedHeroSprite, null),
                        (int) gm.getHero().getX(),
                        (int) gm.getHero().getY(), null);
            }
        }
    }

    private void paintbullets(Graphics2D g2) {
        if (gm.getBullets() != null) {
            for (Bullet bullet : gm.getBullets()) {
                g2.drawImage(bullet.getCurrentAt().filter(bullet.getBodyImageHolder().getBufferedSprite(), null),
                        (int) bullet.getX(),
                        (int) bullet.getY(), null);
            }
        }
    }

    public void refresh() {
        repaint();
    }
}
