package com.view;

import com.model.Shape;

import java.awt.*;

public interface Board_view_interface {
    void doDrawing(Graphics g);
    void drawSquare(Graphics g, int x, int y, Shape.Tetrominoe shape);
    Game getParent();

    void repaint();
}
