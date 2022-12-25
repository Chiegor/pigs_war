package org.example.entity;

import org.example.img.IMG;
import org.example.io.Input;
import org.example.utils.ResourceLoader;

import java.awt.*;

public class Block extends Entity {
    private int width;
    private int height;

    public Block(float x, float y, int width, int height) {
        super(EntityType.BLOCK, x, y);
        this.width = width;
        this.height = height;
    }

    @Override
    public void update(Input input) {

    }

    @Override
    public void render(Graphics2D graphics) {
        graphics.drawImage(ResourceLoader.loadImage(IMG.BLOCK_MODEL), (int) this.getX(), (int) this.getY(), null);
    }

    public static Block[] getBlocks(int number) {
        Block[] blocks = new Block[number];
        blocks[0] = new Block(100, 100, 50, 80);
        blocks[1] = new Block(400, 300, 50, 80);
        blocks[2] = new Block(150, 420, 50, 80);
        return blocks;
    }


}
