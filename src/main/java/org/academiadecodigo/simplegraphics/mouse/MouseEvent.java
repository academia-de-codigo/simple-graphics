package org.academiadecodigo.simplegraphics.mouse;

/**
 * Mouse Click event containing x and y coordinates
 */
public class MouseEvent {

    /** Mouse Buttons */
    public static final int LEFT_CLICK = java.awt.event.MouseEvent.BUTTON1;
    public static final int RIGHT_CLICK = java.awt.event.MouseEvent.BUTTON2;

    private double x;
    private double y;
    private int mouseButton;

    /**
     * Gets the X coordinate where the mouse clicked
     * @return the x coordinate
     */
    public double getX() {
        return x;
    }

    /**
     * Gets the Y coordinate where the mouse cliecked
     * @return the y coordinate
     */
    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public MouseEvent(double x, double y, int mouseButton) {
        this.x = x;
        this.y = y;
        this.mouseButton = mouseButton;
    }

    @Override
    public String toString() {
        return "MouseEvent{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    /**
     * Returns what button was pressed
     * @return
     */
    public int getMouseButton() {
        return mouseButton;
    }
}
