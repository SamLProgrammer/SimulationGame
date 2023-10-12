package com.example.engine;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import com.example.models.Bullet;
import com.example.models.Hero;

public class BulletEngine {

    private Bullet bullet;

    public BulletEngine(Bullet bullet, double degrees, boolean b) {
        this.bullet = bullet;
        rotateBullet(degrees, b);
    }

    public void rotateBullet(double degrees, boolean inversed) {
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


        double locationX = bullet.getBodyImageHolder().getBufferedSprite().getWidth() / 2;
        double locationY = bullet.getBodyImageHolder().getBufferedSprite().getHeight() / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        bullet.setCurrentAt(new AffineTransformOp(tx,
        AffineTransformOp.TYPE_BILINEAR));
    }
}
