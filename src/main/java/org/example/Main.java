package org.example;

import org.example.game.Game;

public class Main {
    public static void main(String[] args) {
        Game pigs = new Game();
        pigs.start();

    }
}

/*
цвета в хекседецимальном формате ->
0xff00ff00
это интеджер
0x - это 16ти система
ff - это максимальное значение последнего байта
00 - это значение красного - его не будет
ff - это значение зеленого - максимально
00 - это значнеие синего - его не будет
 */