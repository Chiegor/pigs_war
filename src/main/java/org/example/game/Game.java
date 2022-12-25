package org.example.game;

import org.example.display.Display;
import org.example.entity.Block;
import org.example.entity.Pig;
import org.example.io.Input;
import org.example.utils.Time;

import java.awt.*;

public class Game implements Runnable {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    public static final String TITLE = "PIGS BATTLE";
    public static final int CLEAR_COLOR = 0xff000000; // черный цвет
    public static final int NUM_BUFFERS = 3;

    // сколько раз мы будем считать в секунду нашу физику / 60 апд в сек
    public static final float UPDATE_RATE = 60.0f;

    // сколько времени должно проходить между каждым апдейтом
    public static final float UPDATE_INTERVAL = Time.SECOND / UPDATE_RATE;

    // сколько тред будет отдыхать чтобы другие процессы могли тоже что-то делать
    public static final long IDLE_TIME = 1;

    private boolean running;
    private Thread gameThread;
    private Graphics2D graphics;
    private Input input;
    private Block[] blocks;
    private Pig[] pigs;

    // startPositionFirstPlayer
    float xFirstPlayer = 530;
    float yFirstPlayer = 540;
    Pig firstPlayer = new Pig(xFirstPlayer, yFirstPlayer);

    // startPositionSecondPlayer
    float xSecondPlayer = 20;
    float ySecondPlayer = 540;
    Pig secondPlayer = new Pig(xSecondPlayer, ySecondPlayer);

    int delta = 0;
    int radius = 50;
    int blocksNumber = 3;
    private static int gravity = 1;

    public Game() {
        running = false;
        // создаем экран
        Display.create(WIDTH, HEIGHT, TITLE, CLEAR_COLOR, NUM_BUFFERS);
        graphics = Display.getGraphics();
        input = new Input();
        Display.addInputListener(input);
        blocks = Block.getBlocks(blocksNumber);
    }

    private void render() {
        Display.clear();
        firstPlayer.render(graphics);
        secondPlayer.render(graphics);
        blocks[0].render(graphics);
        blocks[1].render(graphics);
        blocks[2].render(graphics);
        Display.swapBuffers();
    }

    public synchronized void start() {
        if (running) {
            return;
        }
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public synchronized void stop() {
        if (!running) return;
        running = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cleanUp();
    }

    // метод для расчета действий в игре
    private void update() {
        firstPlayer.update(input);
    }

    // луп который вызывает update ровно 60 раз в сек
    @Override
    public void run() {

        int fps = 0;
        int upd = 0;
        // сколько раз мы догоняли наш апдейт
        int updl = 0;

        long count = 0;

        float delta = 0;
        long lastTime = Time.get();
        while (running) {
            long now = Time.get();
            long elapsedTime = now - lastTime;
            lastTime = now;

            count += elapsedTime;

            boolean render = false;
            delta += (elapsedTime / UPDATE_INTERVAL);
            while (delta > 1) {
                update();
                upd++;
                delta--;
                if (render) {
                    updl++;
                } else {
                    render = true;
                }
            }

            if (render) {
                render();
                fps++;
            } else {
                try {
                    Thread.sleep(IDLE_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // вывод информации о производительности
            if (count >= Time.SECOND) {
                Display.setTitle(TITLE + " || FPS: " + fps + " | UPD: " + upd + " | UPDL: " + updl);
                upd = 0;
                fps = 0;
                updl = 0;
                count = 0;
            }
        }
    }

    // уничтожает окно после того как мы закрыли игру
    private void cleanUp() {
        Display.destroy();
    }

    public static int getGravity() {
        return gravity;
    }

//    private static void fps() {
//        int fps = 0;
//        int upd = 0;
//        int updl = 0;
//    }
}
