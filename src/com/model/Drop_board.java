package com.model;

import com.controller.Main_controller;

public class Drop_board {

    private int BOARD_WIDTH;
    private int BOARD_HEIGHT;

    private int curX = 0;
    private int curY = 0;
    private Shape curPiece;
    private Shape.Tetrominoe[] board;
    private Main_controller mainController;

    public Drop_board(Main_controller controller, int board_width)
    {
        initBoard(controller, board_width);
    }

    private void initBoard(Main_controller controller, int board_width) {

        mainController= controller;
        curPiece = new Shape();
        BOARD_WIDTH=board_width;
        BOARD_HEIGHT=4;
        this.board = new Shape.Tetrominoe[BOARD_WIDTH * BOARD_HEIGHT];
        clearBoard();
    }

    private void clearBoard() {

        for (int i = 0; i < BOARD_HEIGHT * BOARD_WIDTH; ++i) {
            board[i] = Shape.Tetrominoe.NoShape;
        }
    }

    public void dropDown() {

        mainController.setDropping(false);
        mainController.getBvi().getMainBoard().newPiece(curPiece,curX);
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

        //bvi.repaint();

        return true;
    }

    private void newPiece() {

        curPiece.setRandomShape();
        curX = (BOARD_WIDTH-1) / 2 + 1;
        curY = -curPiece.minY();
        mainController.startCountdownTimer(mainController.COUNTDOWN_TIME,mainController.COUNTDOWN_PERIOD_INTERVAL);
    }

    public Shape.Tetrominoe[] getBoard() {
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
}
