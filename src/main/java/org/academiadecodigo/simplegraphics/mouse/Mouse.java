package org.academiadecodigo.simplegraphics.mouse;

import org.academiadecodigo.simplegraphics.graphics.Canvas;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


/**
 * Instantiate a Mouse for obtaining mouse handling capability
 */
public class Mouse implements MouseListener, MouseMotionListener {

    MouseHandler handler;

    /**
     * @param handler the mouse handler
     */
    public Mouse(MouseHandler handler) {
        this.handler = handler;
        Canvas.getInstance().addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        handler.mouseClicked(new org.academiadecodigo.simplegraphics.mouse.MouseEvent(e.getX(), e.getY(), e.getButton()));
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        handler.mouseMoved(new org.academiadecodigo.simplegraphics.mouse.MouseEvent(e.getX(), e.getY(), e.getButton()));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        handler.mousePressed(new org.academiadecodigo.simplegraphics.mouse.MouseEvent(e.getX(), e.getY(), e.getButton()));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        handler.mouseReleased(new org.academiadecodigo.simplegraphics.mouse.MouseEvent(e.getX(), e.getY(), e.getButton()));
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        handler.mouseEntered(new org.academiadecodigo.simplegraphics.mouse.MouseEvent(e.getX(), e.getY(), e.getButton()));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        handler.mouseExited(new org.academiadecodigo.simplegraphics.mouse.MouseEvent(e.getX(), e.getY(), e.getButton()));
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        handler.mouseDragged(new org.academiadecodigo.simplegraphics.mouse.MouseEvent(e.getX(), e.getY(), e.getButton()));
    }
}
