package com.tests;

import com.model.MainBoard;
import com.model.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainBoardTest {

    private MainBoard mainBoard= new MainBoard(1,10);

    @BeforeEach
    void setUp() {
        mainBoard.start();
    }

    @Test
    void start() {
        assertFalse(mainBoard.isFalling());
        assertFalse(mainBoard.isGameOver());
        assertEquals(0,mainBoard.getRowsDestroyed());
    }
    @Test
    void newPiece() {
        Shape shape=new Shape();
        shape.setShape(Shape.Tetrominoe.LineShape);
        mainBoard.newPiece(shape,0);
        assertTrue(mainBoard.isFalling());
        assertEquals(Shape.Tetrominoe.LineShape,mainBoard.getCurPiece().getShape());
        assertEquals(0,mainBoard.getCurX());
    }

    @Test
    void oneLineDown() {
        Shape shape=new Shape();
        shape.setShape(Shape.Tetrominoe.LineShape);
        mainBoard.newPiece(shape,0);
        mainBoard.oneLineDown();
        assertTrue(mainBoard.isFalling());
        assertEquals(Shape.Tetrominoe.LineShape,mainBoard.getCurPiece().getShape());
        assertEquals(0,mainBoard.getCurX());
    }

    @Test
    void dropDown() {
        Shape shape=new Shape();
        shape.setShape(Shape.Tetrominoe.LineShape);
        mainBoard.newPiece(shape,0);
        mainBoard.dropDown();
        assertFalse(mainBoard.isFalling());
        assertEquals(Shape.Tetrominoe.NoShape,mainBoard.getCurPiece().getShape());
        assertEquals(4,mainBoard.getRowsDestroyed());
    }

    @Test
    void resetRowsDestroyed() {
        Shape shape=new Shape();
        shape.setShape(Shape.Tetrominoe.LineShape);
        mainBoard.newPiece(shape,0);
        mainBoard.dropDown();
        mainBoard.resetRowsDestroyed();
        assertEquals(0,mainBoard.getRowsDestroyed());
    }
}