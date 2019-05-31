package org.academiadecodigo.simplegraphics.graphics;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Container of shapes
 */
public class Canvas {
    private static final int MIN_SIZE = 100;
    private static final int MARGIN = 10;
    private static final int LOCATION_OFFSET = 120;
    private static Canvas canvas = new Canvas();
    private ArrayList<Shape> shapes = new ArrayList<Shape>();
    private BufferedImage background;
    private JFrame frame;
    private CanvasComponent component;

    private Canvas() {
        component = new CanvasComponent();

        frame = new JFrame();
        frame.add(component);
        frame.pack();
        frame.setLocation(LOCATION_OFFSET, LOCATION_OFFSET);
        frame.setVisible(true);
    }

    /**
     * Provides access to the canvas singleton instance
     *
     * @return the canvas
     */
    public static Canvas getInstance() {
        return canvas;
    }

    /**
     * Pauses so that the user can see the picture before it is transformed.
     */
    public static void pause() {
        JFrame frame = getInstance().frame;
        if (frame == null) return;
        JOptionPane.showMessageDialog(frame, "Click Ok to continue");
    }

    /**
     * Takes a snapshot of the screen, fades it, and sets it as the background.
     */
    public static void snapshot() {
        Dimension dim = getInstance().component.getPreferredSize();
        java.awt.Rectangle rect = new java.awt.Rectangle(0, 0, dim.width, dim.height);
        BufferedImage image = new BufferedImage(rect.width, rect.height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setColor(java.awt.Color.WHITE);
        g.fillRect(0, 0, rect.width, rect.height);
        g.setColor(java.awt.Color.BLACK);
        getInstance().component.paintComponent(g);
        float factor = 0.8f;
        float base = 255f * (1f - factor);
        RescaleOp op = new RescaleOp(factor, base, null);
        BufferedImage filteredImage
                = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        op.filter(image, filteredImage);
        getInstance().background = filteredImage;
        getInstance().component.repaint();
    }

    public void show(Shape s) {
        if (!shapes.contains(s)) {
            shapes.add(s);
        }
        repaint();
    }

    public void hide(Shape s) {
        if (shapes.contains(s)) {
            shapes.remove(s);
        }
        repaint();
    }

    /**
     * Repaints the screen
     */
    public void repaint() {
        if (frame == null) return;
        Dimension dim = component.getPreferredSize();
        if (dim.getWidth() > component.getWidth()
                || dim.getHeight() > component.getHeight()) {
            frame.pack();
        } else {
            frame.repaint();
        }
    }

    /**
     * Saves the current canvas to disk
     *
     * @param fileName the name of the file to save
     */
    public void saveToDisk(String fileName) {
        Dimension dim = component.getPreferredSize();
        java.awt.Rectangle rect = new java.awt.Rectangle(0, 0, dim.width, dim.height);
        BufferedImage image = new BufferedImage(rect.width, rect.height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) image.getGraphics();
        g.setColor(java.awt.Color.WHITE);
        g.fill(rect);
        g.setColor(java.awt.Color.BLACK);
        component.paintComponent(g);
        String extension = fileName.substring(fileName.lastIndexOf('.') + 1);
        try {
            ImageIO.write(image, extension, new File(fileName));
        } catch (IOException e) {
            System.err.println("Was unable to save the image to " + fileName);
        }
        g.dispose();
    }

    /**
     * Adds a key listener to the canvas
     *
     * @param handler reference to the KeyListener object
     */
    public void addKeyListener(KeyListener handler) {
        frame.addKeyListener(handler);
    }

    /**
     * Adds a mouse listener to the canvas
     * @param handler reference to the MouseListener object
     */
    public void addMouseListener(MouseListener handler) {
        frame.addMouseListener(handler);
    }

    class CanvasComponent extends JComponent {
        public void paintComponent(Graphics g) {
            g.setColor(java.awt.Color.WHITE);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(java.awt.Color.BLACK);
            if (background != null) {
                g.drawImage(background, 0, 0, null);
            }
            for (Shape s : new ArrayList<Shape>(shapes)) {
                Graphics2D g2 = (Graphics2D) g.create();
                s.paintShape(g2);
                g2.dispose();
            }
        }

        public Dimension getPreferredSize() {
            int maxx = MIN_SIZE;
            int maxy = MIN_SIZE;
            if (background != null) {
                maxx = Math.max(maxx, background.getWidth());
                maxy = Math.max(maxx, background.getHeight());
            }
            for (Shape s : shapes) {
                maxx = (int) Math.max(maxx, s.getX() + s.getWidth());
                maxy = (int) Math.max(maxy, s.getY() + s.getHeight());
            }
            return new Dimension(maxx + MARGIN, maxy + MARGIN);
        }
    }
}
