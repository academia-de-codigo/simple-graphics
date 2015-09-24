package org.academiadecodigo.simplegraphics.test;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class Tester implements KeyboardHandler {

    static int cols = 100;
    static int rows = 100;
    static int cellSize = 5;

    public static void main(String[] args) {

        Tester t = new Tester();
        t.test();



    }

    public void test() {

        Keyboard k = new Keyboard(this);
        KeyboardEvent event = new KeyboardEvent();
        event.setKey(KeyboardEvent.KEY_SPACE);
        event.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(event);

        Rectangle field = new Rectangle(10, 10, cols * cellSize, rows * cellSize);
        field.setColor(Color.BLACK);
        field.draw();

        drawCar(0, 0);



    }

    public void drawCar(int col, int row) {

        int xOrig = col * cellSize + 10;
        int yOrig = row * cellSize + 10;

        Rectangle car = new Rectangle(xOrig, yOrig, xOrig + cellSize, yOrig + cellSize);
        car.setColor(Color.BLUE);
        car.fill();
    }

    public void deleteCar(int col, int row) {
        int xOrig = col * cellSize + 10;
        int yOrig = row * cellSize + 10;

        Rectangle car = new Rectangle(xOrig, yOrig, xOrig + cellSize, yOrig + cellSize);
        car.setColor(Color.WHITE);
        car.fill();
    }

    @Override
    public void keyPressed(KeyboardEvent e) {
        System.out.println("SPACE KEY PRESSED");

    }

    @Override
    public void keyReleased(KeyboardEvent e) {

    }
}
