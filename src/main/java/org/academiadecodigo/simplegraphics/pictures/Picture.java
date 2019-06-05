package org.academiadecodigo.simplegraphics.pictures;

import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Movable;
import org.academiadecodigo.simplegraphics.graphics.Shape;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

/**
 * A picture from an image file.
 */
public class Picture implements Shape, Movable {
    private BufferedImage image;
    private JLabel label = new JLabel();
    private String source;
    private double x;
    private double y;
    private double xGrow;
    private double yGrow;

    /**
     * Constructs a picture with no image.
     */
    public Picture() {
    }

    /**
     * Constructs a picture with a given width and height.
     *
     * @param width  the desired width
     * @param height the desired height
     */
    public Picture(double width, double height) {
        image = new BufferedImage((int) Math.round(width),
                (int) Math.round(height), BufferedImage.TYPE_INT_RGB);
        label.setIcon(new ImageIcon(image));
        label.setText("");
    }

    /**
     * Constructs an image from a given file or URL.
     *
     * @param x      the leftmost x-coordinate
     * @param y      the topmost y-coordinate
     * @param source the filename or URL
     */
    public Picture(double x, double y, String source) {
        this.x = x;
        this.y = y;
        load(source);
    }

    public Picture(int[][] grayLevels) {
        image = new BufferedImage(grayLevels[0].length, grayLevels.length, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < image.getWidth(); i++)
            for (int j = 0; j < image.getHeight(); j++) {
                int gray = grayLevels[j][i];
                if (gray < 0) gray = 0;
                if (gray > 255) gray = 255;
                int rgb = gray * (65536 + 256 + 1);
                image.setRGB(i, j, rgb);
            }
        label.setIcon(new ImageIcon(image));
        label.setText("");
    }

    /**
     * Loads a new image from a given file or URL.
     *
     * @param source the filename or URL
     */
    public void load(String source) {

        try {
            this.source = source;

            // Load from the web
            if (source.startsWith("http://")) {

                image = ImageIO.read(new URL(source).openStream());

            } else {

                // Attempt to load from the class path (as in JAR file..)
                URL url = getClass().getResource(source.startsWith("/") ? source : "/" + source);
                if (url != null) {
                    image = ImageIO.read(url.openStream());
                } else {

                    // Load from file
                    image = ImageIO.read(new File(source));
                }
            }

            label.setIcon(new ImageIcon(image));
            label.setText("");
        } catch (Exception ex) {
            image = null;
            label.setIcon(null);
            ex.printStackTrace();
        }
        Canvas.getInstance().repaint();
    }

    /**
     * Gets the leftmost x-position of the shape.
     *
     * @return the leftmost x-position
     */
    public int getX() {
        return (int) Math.round(x - xGrow);
    }

    /**
     * Gets the topmost y-position of the shape.
     *
     * @return the topmost y-position
     */
    public int getY() {
        return (int) Math.round(y - yGrow);
    }

    /**
     * Gets the rightmost x-position of the shape.
     *
     * @return the rightmost x-position
     */
    public int getMaxX() {
        return getX() + getWidth();
    }

    /**
     * Gets the bottommost y-position of the shape.
     *
     * @return the bottommost y-position
     */
    public int getMaxY() {
        return getY() + getHeight();
    }

    /**
     * Gets the width of this picture.
     */
    public int getWidth() {
        return (int) Math.round(
                (image == null ? 0 : image.getWidth()) + 2 * xGrow);
    }

    /**
     * Gets the height of this picture.
     */
    public int getHeight() {
        return (int) Math.round(
                (image == null ? 0 : image.getHeight()) + 2 * yGrow);
    }

    /**
     * The number of pixels in this picture.
     *
     * @return the number of pixels
     */
    public int pixels() {
        if (image == null) {
            return 0;
        } else {
            return image.getWidth() * image.getHeight();
        }
    }

    public int[][] getGrayLevels() {
        if (image == null) return new int[0][0];
        int[][] grayLevels = new int[getHeight()][getWidth()];

        for (int i = 0; i < grayLevels.length; i++)
            for (int j = 0; j < grayLevels[i].length; j++) {
                int rgb = image.getRGB(j, i);
                // Use NTSC/PAL algorithm to convert RGB to gray level
                grayLevels[i][j] = (int) (0.2989 * ((rgb >> 16) & 0xFF) + 0.5866 * ((rgb >> 8) & 0xFF) + 0.1144 * (rgb & 0xFF));
            }
        return grayLevels;
    }

    public String toString() {
        return "Picture[x=" + getX() + ",y=" + getY() + ",width=" + getWidth() + ",height=" + getHeight() + ",source=" + source + "]";
    }

    /**
     * Gets the color of a pixel.
     *
     * @param i the pixel index
     * @return the color at pixel i
     */
    public org.academiadecodigo.simplegraphics.graphics.Color getColorAt(int i) {
        if (image == null || i < 0 || i >= pixels()) {
            throw new IndexOutOfBoundsException("" + i);
        } else {
            return getColorAt(i % image.getWidth(), i / image.getWidth());
        }
    }

    /**
     * Sets the color of a pixel.
     *
     * @param i     the pixel index
     * @param color the new color for the pixel
     */
    public void setColorAt(int i, org.academiadecodigo.simplegraphics.graphics.Color color) {
        if (image == null || i < 0 || i >= pixels()) {
            throw new IndexOutOfBoundsException("" + i);
        } else {
            setColorAt(i % image.getWidth(), i / image.getWidth(), color);
        }
    }

    /**
     * Gets the color of a pixel.
     *
     * @param x the x-coordinate (column) of the pixel
     * @param y the y-coordinate (row) of the pixel
     * @return the color of the pixel
     */
    public org.academiadecodigo.simplegraphics.graphics.Color getColorAt(int x, int y) {
        if (image == null || x < 0 || x >= image.getWidth() || y < 0 || y >= image.getHeight()) {
            throw new IndexOutOfBoundsException("(" + x + "," + y + ")");
        } else {
            int rgb = image.getRGB(x, y) & 0xFFFFFF;
            return new org.academiadecodigo.simplegraphics.graphics.Color(rgb / 65536, (rgb / 256) % 256, rgb % 256);
        }
    }

    /**
     * Sets the color of a pixel.
     *
     * @param x     the x-coordinate (column) of the pixel
     * @param y     the y-coordinate (row) of the pixel
     * @param color the color of the pixel at the given row and column
     */
    public void setColorAt(int x, int y, Color color) {
        if (image == null || x < 0 || x >= image.getWidth() || y < 0 || y >= image.getHeight()) {
            throw new IndexOutOfBoundsException("(" + x + "," + y + ")");
        } else {
            image.setRGB(x, y, ((int) color.getRed()) * 65536 + ((int) color.getGreen()) * 256 + (int) color.getBlue());
            org.academiadecodigo.simplegraphics.graphics.Canvas.getInstance().repaint();
        }
    }

    /**
     * Moves this picture by a given amount.
     *
     * @param dx the amount by which to move in x-direction
     * @param dy the amount by which to move in y-direction
     */
    public void translate(double dx, double dy) {
        x += dx;
        y += dy;
        org.academiadecodigo.simplegraphics.graphics.Canvas.getInstance().repaint();
    }

    /**
     * Resizes this picture both horizontally and vertically.
     *
     * @param dw the amount by which to resize the width on each side
     * @param dh the amount by which to resize the height on each side
     */
    public void grow(double dw, double dh) {
        xGrow += dw;
        yGrow += dh;
        org.academiadecodigo.simplegraphics.graphics.Canvas.getInstance().repaint();
    }

    /**
     * Shows this picture on the canvas.
     */
    public void draw() {
        org.academiadecodigo.simplegraphics.graphics.Canvas.getInstance().show(this);
    }

    /**
     * Deletes this picture from the canvas.
     */
    public void delete() {
        org.academiadecodigo.simplegraphics.graphics.Canvas.getInstance().hide(this);
    }

    /**
     * Draws this shape.
     *
     * @param g2 the graphics context
     */
    public void paintShape(Graphics2D g2) {
        if (image != null) {
            Dimension dim = label.getPreferredSize();
            if (dim.width > 0 && dim.height > 0) {
                label.setBounds(0, 0, dim.width, dim.height);
                g2.translate(getX(), getY());
                g2.scale((image.getWidth() + 2 * xGrow) / dim.width,
                        (image.getHeight() + 2 * yGrow) / dim.height);
                label.paint(g2);
            }
        }
    }
}
