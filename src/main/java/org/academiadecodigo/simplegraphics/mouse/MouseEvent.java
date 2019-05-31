package org.academiadecodigo.simplegraphics.mouse;

/**
 * Mouse Click event containing x and y coordinates
 */
public class MouseEvent {

    private double x;
    private double y;

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

    public MouseEvent(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "MouseEvent{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
