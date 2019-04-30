package com.model;

import java.util.Random;

public class Shape {

    public enum Tetrominoe { NoShape, ZShape, SShape, LineShape,
        TShape, SquareShape, LShape, MirroredLShape }

    private Tetrominoe pieceShape;
    private int [][] coordinates;
    private final int[][][] coordinatesTable=new int[][][] {
            { { 0, 0 },   { 0, 0 },   { 0, 0 },   { 0, 0 } },
            { { 0, -1 },  { 0, 0 },   { -1, 0 },  { -1, 1 } },
            { { 0, -1 },  { 0, 0 },   { 1, 0 },   { 1, 1 } },
            { { 0, -1 },  { 0, 0 },   { 0, 1 },   { 0, 2 } },
            { { -1, 0 },  { 0, 0 },   { 1, 0 },   { 0, 1 } },
            { { 0, 0 },   { 1, 0 },   { 0, 1 },   { 1, 1 } },
            { { -1, -1 }, { 0, -1 },  { 0, 0 },   { 0, 1 } },
            { { 1, -1 },  { 0, -1 },  { 0, 0 },   { 0, 1 } }
    };


    Shape() {

        initShape();
    }

    private void initShape() {

        coordinates = new int[4][2];
        setShape(Tetrominoe.NoShape);
    }

    void setShape(Tetrominoe shape) {
        for (int i = 0; i < 4 ; i++) {

            for (int j = 0; j < 2; ++j) {

                coordinates[i][j] = coordinatesTable[shape.ordinal()][i][j];
            }
        }

        pieceShape = shape;
    }

    private void setX(int index, int x) { coordinates[index][0] = x; }
    private void setY(int index, int y) { coordinates[index][1] = y; }
    public int x(int index) { return coordinates[index][0]; }
    public int y(int index) { return coordinates[index][1]; }
    public Tetrominoe getShape()  { return pieceShape; }

    void setRandomShape() {

        Random r = new Random();
        int x = Math.abs(r.nextInt()) % 7 + 1;
        Tetrominoe[] values = Tetrominoe.values();
        setShape(values[x]);
    }

    public int minX() {

        int m = coordinates[0][0];

        for (int i=0; i < 4; i++) {

            m = Math.min(m, coordinates[i][0]);
        }

        return m;
    }


    int minY() {

        int m = coordinates[0][1];

        for (int i=0; i < 4; i++) {

            m = Math.min(m, coordinates[i][1]);
        }

        return m;
    }

    public Shape rotateLeft() {

        if (pieceShape == Tetrominoe.SquareShape)
            return this;

        Shape result = new Shape();
        result.pieceShape = pieceShape;

        for (int i = 0; i < 4; ++i) {

            result.setX(i, y(i));
            result.setY(i, -x(i));
        }

        return result;
    }

    public Shape rotateRight() {

        if (pieceShape == Tetrominoe.SquareShape)
            return this;

        Shape result = new Shape();
        result.pieceShape = pieceShape;

        for (int i = 0; i < 4; ++i) {

            result.setX(i, -y(i));
            result.setY(i, x(i));
        }

        return result;
    }
}
