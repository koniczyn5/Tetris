package com.model;

import com.controller.Main_controller;
import com.model.Shape.Tetrominoe;

public class MainBoard {

    private int BOARD_WIDTH;
    private int BOARD_HEIGHT;

    private int curX = 0;
    private int curY = 0;
    private Shape curPiece;
    private Tetrominoe[] board;
    private Main_controller mainController;
    private int score; //TODO rework

    public MainBoard(Main_controller parent, int board_width, int board_height) {
        initBoard(parent, board_width, board_height);
    }

    private void initBoard(Main_controller parent, int board_width, int board_height) {
        BOARD_WIDTH=board_width;
        BOARD_HEIGHT=board_height;
        mainController=parent;
        curPiece = new Shape();
        board = new Tetrominoe[BOARD_WIDTH * BOARD_HEIGHT];
        clearBoard();
    }

    private Tetrominoe shapeAt(int x, int y) {
        return board[(y * BOARD_WIDTH) + x];
    }

    public void start() {

        mainController.setStarted(true);
        mainController.setFalling(false);
        score=0;
        mainController.getInfoPanelLook().setStatusBar("Playing...");
        clearBoard();
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
        mainController.setFalling(false);
        if (!mainController.isFalling() && !mainController.isDropping()) { mainController.SpawnNewPiece();
              }
    }

    void newPiece(Shape newShape, int newX) {

        curPiece.copyShape(newShape);
        curX = newX;
        curY = BOARD_HEIGHT - 1 + curPiece.minY();
        if (!tryMove(curPiece, curX, curY)) {
            System.out.println("Game over. R to restart");
            mainController.getInfoPanelLook().setStatusBar("Game over. Press R to restart.");
            curPiece.setShape(Tetrominoe.NoShape);
            mainController.cancelBoardTimer();
            mainController.setStarted(false);
        }
        mainController.setFalling(true);
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
        mainController.getBvi().repaint();
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
                ++score; //TODO give points for fullLines
                for (int k = i; k < BOARD_HEIGHT - 1; ++k) {
                    for (int j = 0; j < BOARD_WIDTH; ++j) {
                        board[(k * BOARD_WIDTH) + j] = shapeAt(j, k + 1);
                    }
                }
            }
        }
        if (numFullLines > 0) {
            mainController.setFalling(false);
            curPiece.setShape(Tetrominoe.NoShape);
            mainController.getBvi().repaint();
        }
    }

    public void doGameCycle() {

        update();
        mainController.getBvi().repaint();
        mainController.getInfoPanelLook().setScoreBar(score);//TODO rework
    }

    private void update() {

        if (mainController.isPaused() || mainController.isDropping()) { return; }
        if (!mainController.isFalling() && !mainController.isDropping()) {
            mainController.SpawnNewPiece();
        } else { oneLineDown(); }
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
}
