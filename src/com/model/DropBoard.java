package com.model;

import com.controller.Main_controller;
import com.model.Shape.Tetrominoe;

public class DropBoard {

    private int BOARD_WIDTH;
    private int BOARD_HEIGHT;

    private int curX = 0;
    private int curY = 0;
    private Shape curPiece;
    private Tetrominoe[] board;
    private Main_controller mainController;

    public DropBoard(Main_controller controller, int board_width)
    {
        initBoard(controller, board_width);
    }

    private void initBoard(Main_controller controller, int board_width) {

        mainController= controller;
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

        mainController.setDropping(false);
        mainController.getBvi().getMainBoard().newPiece(curPiece,curX);
        clearBoard();
        curPiece.setShape(Tetrominoe.NoShape);
        mainController.getDbvi().repaint();
    }

    public void start() {
        clearBoard();
        mainController.setDropping(true);
        newPiece();
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

        mainController.getDbvi().repaint();

        return true;
    }

    public void newPiece() {

        curPiece.setRandomShape();
        curX = (BOARD_WIDTH-1) / 2 + 1;
        curY = BOARD_HEIGHT - 1 + curPiece.minY();
        mainController.startCountdownTimer(mainController.COUNTDOWN_TIME,mainController.COUNTDOWN_PERIOD_INTERVAL);
    }

    public Tetrominoe[] getBoard() {
        return board;
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

    public int getBOARD_HEIGHT() { return BOARD_HEIGHT; }
}
