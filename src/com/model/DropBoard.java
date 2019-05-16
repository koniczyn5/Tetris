package com.model;

import com.model.Shape.Tetrominoe;

public class DropBoard {

    private int BOARD_WIDTH;
    private int BOARD_HEIGHT;

    private int curX = 0;
    private int curY = 0;
    private Shape curPiece;
    private Tetrominoe[] board;

    private boolean isDropping;

    public DropBoard(int board_width)
    {
        initBoard(board_width);
    }

    private void initBoard(int board_width) {

        curPiece = new Shape();
        BOARD_WIDTH=board_width;
        BOARD_HEIGHT=4;
        this.board = new Tetrominoe[BOARD_WIDTH * BOARD_HEIGHT];
        clearBoard();
    }

    private void clearBoard() {

        for (int i = 0; i < BOARD_HEIGHT * BOARD_WIDTH; ++i) {
            board[i] = Tetrominoe.NoShape;
        }
    }

    public void dropDown() {

        isDropping=false;
        clearBoard();
        curPiece.setShape(Tetrominoe.NoShape);
    }

    public void start() {
        isDropping=true;
        clearBoard();
    }

    public boolean tryMove(Shape newPiece, int newX, int newY) {

        for (int i = 0; i < 4; ++i) {

            int x = newX + newPiece.x(i);
            int y = newY - newPiece.y(i);

            if (x < 0 || x >= BOARD_WIDTH || y < 0 || y >= BOARD_HEIGHT) {
                return false;
            }
        }

        curPiece = newPiece;
        curX = newX;
        curY = newY;
        return true;
    }

    public void newPiece() {

        curPiece.setRandomShape();
        curX = (BOARD_WIDTH-1) / 2 + 1;
        curY = BOARD_HEIGHT - 1 + curPiece.minY();
        isDropping=true;
    }

    public Tetrominoe[] getBoard() { return board; }

    public Shape getCurPiece() { return curPiece; }

    public int getCurX() { return curX; }

    public int getCurY() { return curY; }

    public int getBOARD_HEIGHT() { return BOARD_HEIGHT; }

    public boolean isDropping() { return isDropping; }
}
