package org.academiadecodigo.simplegraphics.keyboard;

import org.academiadecodigo.simplegraphics.graphics.Canvas;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import static org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType.KEY_PRESSED;
import static org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType.KEY_RELEASED;

/**
 * Instantiate a Keyboard for obtaining key handling capability
 */
public class Keyboard implements KeyListener {

    KeyboardHandler handler;
    List<KeyboardEvent> keyboardEventArrayList;

    /**
     * @param handler the keyboard handler
     */
    public Keyboard(KeyboardHandler handler) {

        Canvas.getInstance().addKeyListener(this);

        this.handler = handler;
        keyboardEventArrayList = new ArrayList<KeyboardEvent>();

    }

    /**
     * Add a new Keyboard event listener
     * @param keyboardEvent the event to add
     */
    public void addEventListener(KeyboardEvent keyboardEvent) {
        keyboardEventArrayList.add(keyboardEvent);
    }

    /**
     * Add a new Keyboard event listener
     * @see KeyboardEvent
     * @param key the associated key code
     * @see KeyboardEventType
     * @param type the event to add
     */
    public void addEventListener(int key, KeyboardEventType type) {
        KeyboardEvent e = new KeyboardEvent();
        e.setKey(key);
        e.setKeyboardEventType(type);
        addEventListener(e);
    }

    /**
     * Add Keyboard event listeners to a group of keys
     * @see KeyboardEvent
     * @param keys the key codes
     * @see KeyboardEventType
     * @param type the event to add
     */
    public void addEventListeners(int[] keys, KeyboardEventType type) {
        for (int key : keys) {
            addEventListener(key, type);
        }
    }

    /**
     * Remove an existing Keyboard event listener
     * @param keyboardEvent the event to remove
     */
    public void removeEventListener(KeyboardEvent keyboardEvent) {
        keyboardEventArrayList.remove(keyboardEvent);
    }

    /**
     * @see KeyListener#keyTyped(KeyEvent)
     * @param e the event
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * @see KeyboardHandler#keyPressed(KeyboardEvent)
     */
    @Override
    public void keyPressed(KeyEvent e) {

        if (handler == null) {
            return;
        }

        Iterator<KeyboardEvent> iterator = keyboardEventArrayList.iterator();
        while (iterator.hasNext()) {
            KeyboardEvent ke = iterator.next();
            if (ke.getKeyboardEventType() == KeyboardEventType.KEY_PRESSED &&
                    ke.getKey() == e.getKeyCode()) {
                    handler.keyPressed(ke);
            }
        }
    }

    /**
     * @see KeyboardHandler#keyReleased(KeyboardEvent)
     */
    @Override
    public void keyReleased(KeyEvent e) {

        if (handler == null) {
            return;
        }

        Iterator<KeyboardEvent> iterator = keyboardEventArrayList.iterator();
        while (iterator.hasNext()) {
            KeyboardEvent ke = iterator.next();
            if (ke.getKeyboardEventType() == KeyboardEventType.KEY_RELEASED &&
                    ke.getKey() == e.getKeyCode()) {
                handler.keyReleased(ke);
            }
        }
    }
}
