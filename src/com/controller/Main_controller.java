package com.controller;

import com.model.Board;
import com.model.Shape;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main_controller extends KeyAdapter { //TODO: zaimplementuj obsluge kilku wcisnietych klawiszy:
    // https://stackoverflow.com/questions/2623995/swings-keylistener-and-multiple-keys-pressed-at-the-same-time

    private Board board;
    public Main_controller (Board board)
    {
        this.board=board;
    }
    @Override
    public void keyPressed(KeyEvent e) {

        System.out.println("key pressed");

        if (!board.isStarted() || board.getCurPiece().getShape() == Shape.Tetrominoe.NoShape) {
            return;
        }

        int keycode = e.getKeyCode();

        if (keycode == KeyEvent.VK_P) {
            board.pause();
            return;
        }

        if (board.isPaused()) {
            return;
        }

        switch (keycode) {

            case KeyEvent.VK_LEFT:
                board.tryMove(board.getCurPiece(), board.getCurX() - 1, board.getCurY());
                break;

            case KeyEvent.VK_RIGHT:
                board.tryMove(board.getCurPiece(), board.getCurX() + 1, board.getCurY());
                break;

            case KeyEvent.VK_DOWN:
                board.tryMove(board.getCurPiece().rotateRight(), board.getCurX(), board.getCurY());
                break;

            case KeyEvent.VK_UP:
                board.tryMove(board.getCurPiece().rotateLeft(), board.getCurX(), board.getCurY());
                break;

            case KeyEvent.VK_SPACE:
                board.dropDown();
                break;

            case KeyEvent.VK_D:
                board.oneLineDown();
                break;
        }
    }
}
