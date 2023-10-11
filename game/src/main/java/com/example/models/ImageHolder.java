package com.example.models;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageHolder {

    private String spriteRoute;
    private Image sprite;
    private BufferedImage bufferedSprite;

    public ImageHolder(String spriteRoute, int size) {
        this.spriteRoute = spriteRoute;
        initImage(spriteRoute, size);
        initSprite(size);
    }

    private void initImage(String spriteRoute, int size) {
        try {
            bufferedSprite = resizeImage(ImageIO.read(getClass().getResourceAsStream(spriteRoute)), size);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private Image initSprite(int size) {
        Image img = new ImageIcon(getClass().getResource(spriteRoute)).getImage();
		Image newimg = img.getScaledInstance(size, size, Image.SCALE_DEFAULT);
		return (new ImageIcon(newimg).getImage());
    }

    private BufferedImage resizeImage(BufferedImage img, int size) {
		BufferedImage auxImage =  new BufferedImage(size,
                size, img.getType());
		Graphics2D g2d = auxImage.createGraphics();
        g2d.drawImage(img, 0, 0, size, size, null);
        g2d.dispose();
        return auxImage;
	}

    public Image getSprite() {
        return sprite;
    }

    public BufferedImage getBufferedSprite() {
        return bufferedSprite;
    }
}
