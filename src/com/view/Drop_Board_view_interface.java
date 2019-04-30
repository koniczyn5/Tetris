package com.view;

import com.model.DropBoard;
import com.model.Shape;

import java.awt.*;

public interface Drop_Board_view_interface {
    void doDrawing(Graphics g);
    void drawSquare(Graphics g, int x, int y, Shape.Tetrominoe shape);
    DropBoard getDropBoard();

    void repaint();
}
