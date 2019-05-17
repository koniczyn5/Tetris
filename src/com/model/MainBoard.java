package com.model;

import com.model.Shape.Tetrominoe;

public class MainBoard {

    private int BOARD_WIDTH;
    private int BOARD_HEIGHT;

    private int curX = 0;
    private int curY = 0;
    private Shape curPiece;
    private Tetrominoe[] board;

    private boolean isFalling;
    private boolean isGameOver;
    private int rowsDestroyed;

    public MainBoard(int board_width, int board_height) {
        initBoard(board_width, board_height);
    }

    private void initBoard(int board_width, int board_height) {
        BOARD_WIDTH=board_width;
        BOARD_HEIGHT=board_height;
        curPiece = new Shape();
        board = new Tetrominoe[BOARD_WIDTH * BOARD_HEIGHT];
        clearBoard();
    }

    private Tetrominoe shapeAt(int x, int y) {
        return board[(y * BOARD_WIDTH) + x];
    }

    public void start() {
        clearBoard();
        isFalling=false;
        isGameOver=false;
        rowsDestroyed=0;
    }

    public void dropDown() {

        int newY = curY;

        while (newY > 0) {
            if (!tryMove(curPiece, curX, newY - 1)) { break; }
            --newY;
        }
        pieceDropped();
    }

    public void oneLineDown() {

        if (!tryMove(curPiece, curX, curY - 1)) { pieceDropped(); }
    }

    private void clearBoard() {

        for (int i = 0; i < BOARD_HEIGHT * BOARD_WIDTH; ++i) {
            board[i] = Tetrominoe.NoShape;
        }
    }

    private void pieceDropped() {

        for (int i = 0; i < 4; ++i) {
            int x = curX + curPiece.x(i);
            int y = curY - curPiece.y(i);
            board[(y * BOARD_WIDTH) + x] = curPiece.getShape();
        }
        removeFullLines();
        isFalling=false;
    }

    public void newPiece(Shape newShape, int newX) {

        curPiece.copyShape(newShape);
        curX = newX;
        curY = BOARD_HEIGHT - 1 + curPiece.minY();
        if (!tryMove(curPiece, curX, curY)) {
            System.out.println("Game over. R to restart");
            isGameOver=true;
            curPiece.setShape(Tetrominoe.NoShape);
        }
        isFalling=true;
    }

    public boolean tryMove(Shape newPiece, int newX, int newY) {

        for (int i = 0; i < 4; ++i) {
            int x = newX + newPiece.x(i);
            int y = newY - newPiece.y(i);
            if (x < 0 || x >= BOARD_WIDTH || y < 0 || y >= BOARD_HEIGHT) {
                return false;
            }
            if (shapeAt(x, y) != Tetrominoe.NoShape) {
                return false;
            }
        }
        curPiece = newPiece;
        curX = newX;
        curY = newY;
        return true;
    }

    private void removeFullLines() {

        int numFullLines = 0;
        for (int i = BOARD_HEIGHT - 1; i >= 0; --i) {
            boolean lineIsFull = true;
            for (int j = 0; j < BOARD_WIDTH; ++j) {
                if (shapeAt(j, i) == Tetrominoe.NoShape) {
                    lineIsFull = false;
                    break;
                }
            }
            if (lineIsFull) {
                ++numFullLines;
                for (int k = i; k < BOARD_HEIGHT - 1; ++k) {
                    for (int j = 0; j < BOARD_WIDTH; ++j) {
                        board[(k * BOARD_WIDTH) + j] = shapeAt(j, k + 1);
                    }
                }
            }
        }
        if (numFullLines > 0) {
            curPiece.setShape(Tetrominoe.NoShape);
            rowsDestroyed=numFullLines;
        }
    }

    public Shape getCurPiece() {
        return curPiece;
    }

    public int getCurX() {
        return curX;
    }

    public int getCurY() {
        return curY;
    }

    public Tetrominoe[] getBoard() {
        return board;
    }

    public boolean isFalling() { return isFalling; }

    public int getRowsDestroyed() { return rowsDestroyed; }

    public void resetRowsDestroyed() { rowsDestroyed=0; }

    public boolean isGameOver() { return isGameOver; }
}
