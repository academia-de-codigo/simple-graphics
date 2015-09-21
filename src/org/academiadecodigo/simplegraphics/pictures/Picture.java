package org.academiadecodigo.simplegraphics.pictures;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

/**
 * A picture from an image file.
 */
public class Picture
{
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
    public Picture()
    {
    }

    /**
     * Constructs a picture from a given file or URL.
     * @param source the filename or URL
     */
    public Picture(String source)
    {
        load(source);
    }

    /**
     * Constructs a picture with a given width and height.
     * @param width the desired width
     * @param height the desired height
     */
    public Picture(double width, double height)
    {
        image = new BufferedImage((int) Math.round(width), 
            (int) Math.round(height), BufferedImage.TYPE_INT_RGB);
        label.setIcon(new ImageIcon(image));
        label.setText("");      
    }

    /**
     * Loads a new image from a given file or URL.
     * 
     * @param source the filename or URL
     */
    public void load(String source)
    {
        try
        {
            this.source = source;
            if (source.startsWith("http://"))
                image = ImageIO.read(new URL(source).openStream());
            else
                image = ImageIO.read(new File(source));

            label.setIcon(new ImageIcon(image));
            label.setText("");
        }
        catch (Exception ex)
        {
            image = null;
            label.setIcon(null);
            ex.printStackTrace();
        }
        Canvas.getInstance().repaint();
    }

    /**
     * Displays a file chooser for picking a file, loads the picture from the
     * file that the user selected, and pauses so that the user can see the
     * loaded picture.
     */
    public void pick()
    {
        JFileChooser chooser = new JFileChooser(".");
        chooser.setFileFilter(new FileFilter()
            {
                public String getDescription()
                {
                    return "Image files";
                }

                public boolean accept(File f)
                {
                    String name = f.getName();
                    return Arrays.asList(ImageIO.getReaderFileSuffixes()).contains(name.substring(name.lastIndexOf(".") + 1));
                }
            });
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            load(chooser.getSelectedFile().getAbsolutePath());
            draw();           
        }
    }
    
    /**
     * Pauses so that one can look at the picture.
     */
    public void pause()
    {
        Canvas.getInstance().pause();
    }
    

    /**
     * Gets the leftmost x-position of the shape.
     * @return the leftmost x-position
     */
    public int getX()
    {
        return (int) Math.round(x - xGrow);
    }

    /**
     * Gets the topmost y-position of the shape.
     * @return the topmost y-position
     */
    public int getY()
    {
        return (int) Math.round(y - yGrow);
    }

    /**
     * Gets the rightmost x-position of the shape.
     * @return the rightmost x-position
     */
    public int getMaxX()
    {
        return getX() + getWidth();
    }

    /**
     * Gets the bottommost y-position of the shape.
     * @return the bottommost y-position
     */
    public int getMaxY()
    {
        return getY() + getHeight();
    }

    /**
     * Gets the width of this picture.
     */
    public int getWidth()
    {
        return (int) Math.round(
            (image == null ? 0 : image.getWidth()) + 2 * xGrow);
    }

    /**
     * Gets the height of this picture.
     */
    public int getHeight()
    {
        return (int) Math.round(
            (image == null ? 0 : image.getHeight()) + 2 * yGrow);
    }

    /**
     * The number of pixels in this picture.
     * @return the number of pixels
     */
    public int pixels()
    {
        if (image == null)
        {
            return 0;
        }
        else
        {
            return image.getWidth() * image.getHeight();
        }
    }

    public int[][] getGrayLevels()
    {
        if (image == null) return new int[0][0];
        int[][] grayLevels = new int[getHeight()][getWidth()];
      
        for (int i = 0; i < grayLevels.length; i++)
            for (int j = 0; j < grayLevels[i].length; j++)
            {
                int rgb = image.getRGB(j, i);
                // Use NTSC/PAL algorithm to convert RGB to gray level
                grayLevels[i][j] = (int)(0.2989 * ((rgb >> 16) & 0xFF) + 0.5866 * ((rgb >> 8) & 0xFF) + 0.1144 * (rgb & 0xFF));	       
            }
        return grayLevels;
    }

    public Picture(int[][] grayLevels)
    {
        image = new BufferedImage(grayLevels[0].length, grayLevels.length, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < image.getWidth(); i++)
            for (int j = 0; j < image.getHeight(); j++)
            {
                int gray = grayLevels[j][i];
                if (gray < 0) gray = 0;
                if (gray > 255) gray = 255;
                int rgb = gray * (65536 + 256 + 1);
                image.setRGB(i, j, rgb);
            }
        label.setIcon(new ImageIcon(image));
        label.setText("");      
    }

    public String toString()
    {
        return "Picture[x=" + getX() + ",y=" + getY() + ",width=" + getWidth() + ",height=" + getHeight() + ",source=" + source + "]";
    }

    /**
     * Gets the color of a pixel.
     * @param i the pixel index
     * @return the color at pixel i
     */
    public Color getColorAt(int i)
    {
        if (image == null || i < 0 || i >= pixels())
        {
            throw new IndexOutOfBoundsException("" + i);
        }
        else
        {
            return getColorAt(i % image.getWidth(), i / image.getWidth());
        }
    }

    /**
     * Sets the color of a pixel.
     * @param i the pixel index
     * @param color the new color for the pixel
     */
    public void setColorAt(int i, Color color)
    {
        if (image == null || i < 0 || i >= pixels())
        {
            throw new IndexOutOfBoundsException("" + i);
        }
        else
        {
            setColorAt(i % image.getWidth(), i / image.getWidth(), color);
        }
    }

    /**
     * Gets the color of a pixel.
     * @param x the x-coordinate (column) of the pixel
     * @param y the y-coordinate (row) of the pixel
     * @param color the new color for the pixel
     */
    public Color getColorAt(int x, int y)
    {
        if (image == null || x < 0 || x >= image.getWidth() || y < 0 || y >= image.getHeight())
        {
            throw new IndexOutOfBoundsException("(" + x + "," + y + ")");
        }
        else
        {
            int rgb = image.getRGB(x, y) & 0xFFFFFF;
            return new Color(rgb / 65536, (rgb / 256) % 256, rgb % 256);
        }
    }

    /**
     * Sets the color of a pixel.
     * @param x the x-coordinate (column) of the pixel
     * @param y the y-coordinate (row) of the pixel
     * @param the color of the pixel at the given row and column
     */
    public void setColorAt(int x, int y, Color color)
    {
        if (image == null || x < 0 || x >= image.getWidth() || y < 0 || y >= image.getHeight())
        {
            throw new IndexOutOfBoundsException("(" + x + "," + y + ")");
        }
        else
        {
            if (color.getRed() < 0 || color.getRed() > 255)
                throw new IllegalArgumentException("red not between 0 and 255");
            if (color.getGreen() < 0 || color.getGreen() > 255)
                throw new IllegalArgumentException("green not between 0 and 255");
            if (color.getBlue() < 0 || color.getBlue() > 255)
                throw new IllegalArgumentException("blue not between 0 and 255");
            image.setRGB(x, y, color.getRed() * 65536 + color.getGreen() * 256 + color.getBlue());
            Canvas.getInstance().repaint();
        }
    }

    /**
     * Moves this picture by a given amount.
     * @param dx the amount by which to move in x-direction
     * @param dy the amount by which to move in y-direction
     */
    public void translate(double dx, double dy)
    {
        x += dx;
        y += dy;
        Canvas.getInstance().repaint();
    }

    /**
     * Resizes this picture both horizontally and vertically.
     * @param dw the amount by which to resize the width on each side
     * @param dw the amount by which to resize the height on each side
     */
    public void grow(double dw, double dh)
    {
        xGrow += dw;
        yGrow += dh;
        Canvas.getInstance().repaint();
    }

    /**
     * Shows this picture on the canvas.
     */
    public void draw()
    {
        Canvas.getInstance().show(this);
    }

    /**
     * Draws this shape.
     * @param g2 the graphics context
     */
    public void paintShape(Graphics2D g2)
    {
        Dimension dim = label.getPreferredSize();
        //        System.out.println(dim);
        if (dim.width > 0 && dim.height > 0)
        {
            label.setBounds(0, 0, dim.width, dim.height);
            g2.translate(getX(), getY());
            g2.scale((image.getWidth() + 2 * xGrow) / dim.width, 
                (image.getHeight() + 2 * yGrow) / dim.height);
            label.paint(g2);
        }
    }

    static class Canvas
    {
        private static Canvas canvas = new Canvas();

        private ArrayList<Picture> pictures = new ArrayList<Picture>();
        private JFrame frame;
        private CanvasComponent component = new CanvasComponent();

        private static final int LOCATION_OFFSET = 120;
        private static final int MIN_SIZE = 100;

        class CanvasComponent extends JComponent
        {
            public void paintComponent(Graphics g)
            {
                g.setColor(java.awt.Color.WHITE);
                g.fillRect(0, 0, getWidth(), getHeight());

                for (Picture s : pictures)
                {
                    Graphics2D g2 = (Graphics2D) g.create();
                    s.paintShape(g2);
                    g2.dispose();
                }
            }

            public Dimension getPreferredSize()
            {
                int maxx = MIN_SIZE;
                int maxy = MIN_SIZE;
                for (Picture s : pictures)
                {
                    maxx = (int) Math.max(maxx, s.getX() + s.getWidth());
                    maxy = (int) Math.max(maxy, s.getY() + s.getHeight());
                }
                return new Dimension(maxx, maxy);
            }
        }

        private Canvas()
        {
            if (System.getProperty("com.horstmann.codecheck") == null)
            {
                frame = new JFrame();
                if (!System.getProperty("java.class.path").contains("bluej"))
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(component);
                frame.pack();
                frame.setLocation(LOCATION_OFFSET, LOCATION_OFFSET);
                frame.setVisible(true);
            }
            else
            {
                final String SAVEFILE ="canvas.png";
                final Thread currentThread = Thread.currentThread();
                Thread watcherThread = new Thread() 
                    {
                        public void run()
                        {
                            try
                            {
                                final int DELAY = 10;
                            
                                while (currentThread.getState() != Thread.State.TERMINATED)
                                {
                                    Thread.sleep(DELAY);
                                }
                                saveToDisk(SAVEFILE);
                            }
                            catch (Exception ex)
                            {
                                ex.printStackTrace();
                            }
                        }
                    };
                watcherThread.start();
            }
        }

        public static Canvas getInstance()
        {
            return canvas;
        }

        public void show(Picture s)
        {
            if (!pictures.contains(s))
            {
                pictures.add(s);
            }
            repaint();
        }

        public void repaint()
        {
            if (frame == null) return;
            Dimension dim = component.getPreferredSize();
            if (dim.getWidth() > component.getWidth()
                || dim.getHeight() > component.getHeight())
            {
                frame.pack();
            }
            else
            {
                frame.repaint();
            }
        }

        /**
         * Pauses so that the user can see the picture before it is transformed.
         */
        public void pause()
        {
            if (frame == null) return;
            JOptionPane.showMessageDialog(frame, "Click Ok to continue");
        }

        public void saveToDisk(String fileName)
        {
            Dimension dim = component.getPreferredSize();
            java.awt.Rectangle rect = new java.awt.Rectangle(0, 0, dim.width, dim.height);
            BufferedImage image = new BufferedImage(rect.width, rect.height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = (Graphics2D) image.getGraphics();
            g.setColor(java.awt.Color.WHITE);
            g.fill(rect);
            g.setColor(java.awt.Color.BLACK);
            component.paintComponent(g);
            String extension = fileName.substring(fileName.lastIndexOf('.') + 1);
            try
            {
                ImageIO.write(image, extension, new File(fileName));
            } 
            catch(IOException e)
            {
                System.err.println("Was unable to save the image to " + fileName);
            }
            g.dispose();    	
        }
    }
}
