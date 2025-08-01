package org.academiadecodigo.simplegraphics.graphics;

import java.awt.*;

public class Triangle implements Shape, Colorable, Fillable, Movable {

    private Color color = Color.WHITE;
    private boolean filled = false;
    private double x;
    private double y;
    private double width;
    private double height;
    private int[] xPoints;
    private int[] yPoints;

    /**
     * Constructs a triangle.
     *
     * @param x      the leftmost x-coordinate
     * @param y      the topmost y-coordinate
     * @param width  the width
     * @param height the height
     */
    public Triangle(double x, double y, double width, double height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        xPoints = new int[3];
        yPoints = new int[3];
    }

    /**
     * Gets the leftmost x-position of this triangle.
     *
     * @return the leftmost x-position
     */
    @Override
    public int getX() {
        return (int) Math.round(x);
    }

    /**
     * Gets the topmost y-position of this triangle.
     *
     * @return the topmost y-position
     */
    @Override
    public int getY() {
        return (int) Math.round(y);
    }

    /**
     * Gets the width of this triangle.
     *
     * @return the width
     */
    @Override
    public int getWidth() {
        return (int) Math.round(width);
    }

    /**
     * Gets the height of this triangle.
     *
     * @return the height
     */
    @Override
    public int getHeight() {
        return (int) Math.round(height);
    }

    /**
     * Moves this triangle by a given amount.
     *
     * @param dx the amount by which to move in x-direction
     * @param dy the amount by which to move in y-direction
     */
    @Override
    public void translate(double dx, double dy) {
        x += dx;
        y += dy;
        Canvas.getInstance().repaint();
    }

    /**
     * Resizes this triangle both horizontally and vertically.
     *
     * @param dw the amount by which to resize the width on each side
     * @param dh the amount by which to resize the height on each side
     */
    @Override
    public void grow(double dw, double dh) {
        width += 2 * dw;
        height += 2 * dh;
        x -= dw;
        y -= dh;
        Canvas.getInstance().repaint();
    }

    /**
     * Sets the color of this triangle.
     *
     * @param newColor the new color
     */
    @Override
    public void setColor(Color newColor) {
        color = newColor;
        Canvas.getInstance().repaint();
    }

    /**
     * Draws this triangle.
     */
    @Override
    public void draw() {
        filled = false;
        Canvas.getInstance().show(this);
    }

    /**
     * Deletes this triangle
     */
    @Override
    public void delete() {
        Canvas.getInstance().hide(this);
    }

    /**
     * Fills this triangle.
     */
    @Override
    public void fill() {
        filled = true;
        Canvas.getInstance().show(this);
    }

    @Override
    public String toString() {
        return "Triangle[x=" + getX() + ",y=" + getY() + ",width=" + getWidth() + ",height=" + getHeight() + "]";
    }

    @Override
    public void paintShape(Graphics2D g2) {
        xPoints[0] = (int) (x + Math.round(width / 2));
        yPoints[0] = (int) y;

        xPoints[1] = (int) x;
        yPoints[1] = (int) (y + height);

        xPoints[2] = (int) (x + width);
        yPoints[2] = (int) (y + height);

        g2.setColor(new java.awt.Color((int) color.getRed(), (int) color.getGreen(), (int) color.getBlue()));

        if (filled) {
            g2.fillPolygon(xPoints, yPoints, 3);
        } else {
            g2.drawPolygon(xPoints, yPoints, 3);
        }
    }
}
