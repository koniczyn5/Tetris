package com.tests;

import com.model.Shape;
import com.model.Shape.Tetrominoe;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShapeTest {

    @Test
    void setAndGetShape() {
        Shape shape = new Shape();
        shape.setShape(Tetrominoe.LineShape);
        assertEquals(Tetrominoe.LineShape,shape.getShape());
    }

    @Test
    void copyShape() {
        Shape shape1 = new Shape();
        Shape shape2 = new Shape();
        shape1.setShape(Tetrominoe.LineShape);
        shape2.copyShape(shape1);
        assertEquals(shape1.getShape(),shape2.getShape());
    }

    @Test
    void x() {
        Shape shape = new Shape();
        shape.setShape(Tetrominoe.LineShape);
        assertEquals(0,shape.x(0));
    }

    @Test
    void y() {
        Shape shape = new Shape();
        shape.setShape(Tetrominoe.LineShape);
        assertEquals(-1,shape.y(0));
    }

    @Test
    void minY() {
        Shape shape = new Shape();
        shape.setShape(Tetrominoe.LineShape);
        assertEquals(-1,shape.minY());
    }

    @Test
    void rotateLeft() {
        Shape shape = new Shape();
        shape.setShape(Tetrominoe.LineShape);
        shape=shape.rotateLeft();
        assertEquals(-1,shape.x(0));
    }

    @Test
    void rotateRight() {
        Shape shape = new Shape();
        shape.setShape(Tetrominoe.LineShape);
        shape=shape.rotateRight();
        assertEquals(1,shape.x(0));
    }
}