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
        handler.mouseClicked(new org.academiadecodigo.simplegraphics.mouse.MouseEvent(e.getX(), e.getY()));
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        handler.mouseMoved(new org.academiadecodigo.simplegraphics.mouse.MouseEvent(e.getX(), e.getY()));
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

}
