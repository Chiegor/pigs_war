package org.example.io;

import org.example.action.Action;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;

public class Input extends JComponent {

    // индекс - кнопка, значение - нажата или нет
    private boolean[] map;

    public Input() {
        // все децимальтный значения клавиш по аски-коду
        map = new boolean[256];

        for (int i = 0; i < map.length; i++) {

            final  int KEY_CODE = i;

            // нажатие кнопок ловим только когда поле игры активно
            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                    // нажатие кнопки по аски-коду // i * 2 - мы даем название кнопки которую нажали
                    .put(KeyStroke.getKeyStroke(i, 0, false), i * 2);
            // анонимус иннер класс
            getActionMap().put(i * 2, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // если кнопка нажата - её ячейка становиться true
                    map[KEY_CODE] = true;
                }
            });

            // дейсвтие когда кнопка отпускается
            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                    .put(KeyStroke.getKeyStroke(i, 0, true), i * 2 + 1);
            getActionMap().put(i * 2 + 1, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    map[KEY_CODE] = false;
                }
            });
        }
    }

    public boolean[] getMap() {
        // возвращаем копию, чтобы никто не смоги зменить наш массив
        return Arrays.copyOf(map, map.length);
    }

    // проверяем нажата кнопка или нет
    public boolean getKey(int keyCode) {
        return map[keyCode];
    }



}
