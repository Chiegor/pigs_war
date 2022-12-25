package org.example.entity;

import org.example.img.IMG;
import org.example.io.Input;
import org.example.utils.ResourceLoader;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Pig extends Entity {

    private BufferedImage image;
    private int speed;

    public Pig(float x, float y) {
        super(EntityType.PIG, x, y);
        image = ResourceLoader.loadImage(IMG.PIG_MODEL);
        speed = 3;
    }

    @Override
    public void update(Input input) {
        if (input.getKey(KeyEvent.VK_UP)) {
            y -= speed;
        }
        if (input.getKey(KeyEvent.VK_DOWN)) {
            y += speed;
        }
        if (input.getKey(KeyEvent.VK_LEFT)) {
            x -= speed;
        }
        if (input.getKey(KeyEvent.VK_RIGHT)) {
            x += speed;
        }
        if (input.getKey(KeyEvent.VK_SPACE)) {
            y -= speed * 3;
        }


    }

    @Override
    public void render(Graphics2D graphics) {
        graphics.drawImage(this.getImage(), (int) this.x, (int) this.y, null);
    }

    public BufferedImage getImage() {
        return image;
    }

    public static Pig[] getPigs(int number) {
        Pig[] pigsArray = new Pig[number];
        return pigsArray;
    }

    public static Pig getPig(float x, float y) {
        return new Pig(x, y);
    }
}
