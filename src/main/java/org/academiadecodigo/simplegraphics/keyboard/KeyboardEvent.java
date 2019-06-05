package org.academiadecodigo.simplegraphics.keyboard;

import java.awt.event.KeyEvent;

/**
 * Keyboard events containing key codes and event types
 */
public class KeyboardEvent {

    /** Space and Directions */
    public static final int KEY_SPACE = KeyEvent.VK_SPACE;
    public static final int KEY_LEFT = KeyEvent.VK_LEFT;
    public static final int KEY_RIGHT = KeyEvent.VK_RIGHT;
    public static final int KEY_UP = KeyEvent.VK_UP;
    public static final int KEY_DOWN = KeyEvent.VK_DOWN;

    /** Numbers */
    public static final int KEY_0 = KeyEvent.VK_0;
    public static final int KEY_1 = KeyEvent.VK_1;
    public static final int KEY_2 = KeyEvent.VK_2;
    public static final int KEY_3 = KeyEvent.VK_3;
    public static final int KEY_4 = KeyEvent.VK_4;
    public static final int KEY_5 = KeyEvent.VK_5;
    public static final int KEY_6 = KeyEvent.VK_6;
    public static final int KEY_7 = KeyEvent.VK_7;
    public static final int KEY_8 = KeyEvent.VK_8;
    public static final int KEY_9 = KeyEvent.VK_9;

    /** Letters */
    public static final int KEY_A = KeyEvent.VK_A;
    public static final int KEY_B = KeyEvent.VK_B;
    public static final int KEY_C = KeyEvent.VK_C;
    public static final int KEY_D = KeyEvent.VK_D;
    public static final int KEY_E = KeyEvent.VK_E;
    public static final int KEY_F = KeyEvent.VK_F;
    public static final int KEY_G = KeyEvent.VK_G;
    public static final int KEY_H = KeyEvent.VK_H;
    public static final int KEY_I = KeyEvent.VK_I;
    public static final int KEY_J = KeyEvent.VK_J;
    public static final int KEY_K = KeyEvent.VK_K;
    public static final int KEY_L = KeyEvent.VK_L;
    public static final int KEY_M = KeyEvent.VK_M;
    public static final int KEY_N = KeyEvent.VK_N;
    public static final int KEY_O = KeyEvent.VK_O;
    public static final int KEY_P = KeyEvent.VK_P;
    public static final int KEY_Q = KeyEvent.VK_Q;
    public static final int KEY_R = KeyEvent.VK_R;
    public static final int KEY_S = KeyEvent.VK_S;
    public static final int KEY_T = KeyEvent.VK_T;
    public static final int KEY_U = KeyEvent.VK_U;
    public static final int KEY_V = KeyEvent.VK_V;
    public static final int KEY_W = KeyEvent.VK_W;
    public static final int KEY_X = KeyEvent.VK_X;
    public static final int KEY_Y = KeyEvent.VK_Y;
    public static final int KEY_Z = KeyEvent.VK_Z;

    /** Function keys **/
    public static final int KEY_F1 = KeyEvent.VK_F1;
    public static final int KEY_F2 = KeyEvent.VK_F2;
    public static final int KEY_F3 = KeyEvent.VK_F3;
    public static final int KEY_F4 = KeyEvent.VK_F4;
    public static final int KEY_F5 = KeyEvent.VK_F5;
    public static final int KEY_F6 = KeyEvent.VK_F6;
    public static final int KEY_F7 = KeyEvent.VK_F7;
    public static final int KEY_F8 = KeyEvent.VK_F8;
    public static final int KEY_F9 = KeyEvent.VK_F9;
    public static final int KEY_F10 = KeyEvent.VK_F10;
    public static final int KEY_F11 = KeyEvent.VK_F11;
    public static final int KEY_F12 = KeyEvent.VK_F12;

    /** Other **/
    public static final int KEY_ENTER = KeyEvent.VK_ENTER;
    public static final int KEY_ESC = KeyEvent.VK_ESCAPE;
    public static final int KEY_BACK_SLASH = KeyEvent.VK_BACK_SLASH;
    public static final int KEY_SHIFT = KeyEvent.VK_SHIFT;
    public static final int KEY_TAB = KeyEvent.VK_TAB;


    private KeyboardEventType keyboardEventType;
    private int key;

    /**
     * Gets the type of event associated with this event
     * @see KeyboardEventType
     * @return the event type
     */
    public KeyboardEventType getKeyboardEventType() {
        return keyboardEventType;
    }

    /**
     * Sets the type of event associated with this event
     * @see KeyboardEventType
     * @param keyboardEventType the event type
     */
    public void setKeyboardEventType(KeyboardEventType keyboardEventType) {
        this.keyboardEventType = keyboardEventType;
    }

    /**
     * Obtains the code of the key associated with this event
     * @return the key code
     */
    public int getKey() {
        return key;
    }

    /**
     * Sets the code of the key associated with this event
     * @param key the key code
     */
    public void setKey(int key) {
        this.key = key;
    }
}
