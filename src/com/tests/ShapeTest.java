package com.tests;

import com.model.Shape;
import com.model.Shape.Tetrominoe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShapeTest {

    private Shape shape = new Shape();

    @BeforeEach
    void setUp() {
        shape.setShape(Tetrominoe.LineShape);
    }

    @Test
    void setAndGetShape() {
        assertEquals(Tetrominoe.LineShape,shape.getShape());
    }

    @Test
    void copyShape() {
        Shape shape2 = new Shape();
        shape2.copyShape(shape);
        assertEquals(shape.getShape(),shape2.getShape());
    }

    @Test
    void x() {
        assertEquals(0,shape.x(0));
    }

    @Test
    void y() {
        assertEquals(-1,shape.y(0));
    }

    @Test
    void minY() {
        assertEquals(-1,shape.minY());
    }

    @Test
    void rotateLeft() {
        shape=shape.rotateLeft();
        assertEquals(-1,shape.x(0));
    }

    @Test
    void rotateRight() {
        shape=shape.rotateRight();
        assertEquals(1,shape.x(0));
    }
}