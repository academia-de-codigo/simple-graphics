package org.academiadecodigo.simplegraphics.mouse;

/**
 * Interface to be implemented by all classes that want to receive mouse events
 * @see MouseEvent
 */
public interface MouseHandler {

    /**
     * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
     * @param e the event
     */
    public void mouseClicked(MouseEvent e);

    /**
     * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
     * @param e the event
     */
    public void mouseMoved(MouseEvent e);

}
