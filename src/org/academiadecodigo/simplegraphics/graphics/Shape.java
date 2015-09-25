package org.academiadecodigo.simplegraphics.graphics;

import java.awt.*;

/**
 * A shape that can be drawn on a canvas.
 */
public interface Shape {
    /**
     * Gets the leftmost x-position of the shape.
     *
     * @return the leftmost x-position
     */
    int getX();

    /**
     * Gets the topmost y-position of the shape.
     *
     * @return the topmost y-position
     */
    int getY();

    /**
     * Gets the width of the shape.
     *
     * @return the width
     */
    int getWidth();

    /**
     * Gets the height of the shape.
     *
     * @return the height
     */
    int getHeight();

    /**
     * Draws the shape
     */
    void draw();

    /**
     * Deletes the shape from screen
     */
    void delete();

    /**
     * Changes the size of the shape
     *
     * @param dw the amount by which to resize the width on each side
     * @param dh the amount by which to resize the height on each side
     */
    void grow(double dw, double dh);

    /**
     * Paints the shape
     *
     * @param g2 the graphics object
     */
    void paintShape(Graphics2D g2);

}
