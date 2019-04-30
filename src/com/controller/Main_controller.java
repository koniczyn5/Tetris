package com.controller;

import com.model.Board;
import com.model.Shape;
import com.view.Board_look;
import com.view.Board_view_interface;
import com.view.Drop_board_look;
import com.view.Game;

import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

public class Main_controller extends MultiKeyAdapter {

    private final int KEYS_INITIAL_DELAY = 100;
    private final int KEYS_PERIOD_INTERVAL = 120;
    private final int BOARD_INITIAL_DELAY = 100;
    private final int BOARD_PERIOD_INTERVAL = 300;
    private Board_view_interface bvi;
    private Board board;
    private Drop_board_look dropBoardLook;
    private Timer keysTimer;
    private Timer boardTimer;

    public Main_controller (Game parent, int board_width, int board_height)
    {
        super();
        Board_look boardLook=new Board_look(board_width,board_height);
        dropBoardLook=new Drop_board_look(boardLook.getBoard(),board_width);
        bvi=boardLook;
        boardLook.setSize(200,440);
        boardLook.setLocation(0,80);
        boardLook.addKeyListener(this);
        dropBoardLook.setSize(200,80);
        dropBoardLook.setLocation(0,0);
        dropBoardLook.addKeyListener(this);
        parent.add(dropBoardLook);
        parent.add(boardLook);
        initMain(bvi.getBoard());
    }

    private void initMain(Board board)
    {
        this.board=board;
        keysTimer=new Timer(true);
        keysTimer.scheduleAtFixedRate(new KeysTask(),KEYS_INITIAL_DELAY,KEYS_PERIOD_INTERVAL);
    }


    private class KeysTask extends TimerTask {
        @Override
        public void run() {
            if (!board.isStarted()) {
                if (isInKeys(KeyEvent.VK_R)) {
                    board.start();
                }
                return;
            }
            if (board.isPaused() || board.getCurPiece().getShape() == Shape.Tetrominoe.NoShape) {
                return;
            }

            if (isInKeys(KeyEvent.VK_ESCAPE)) {
                board.pause();
                return;
            }

            if (isInKeys(KeyEvent.VK_A) && !isInKeys(KeyEvent.VK_D)) {
                board.tryMove(board.getCurPiece(), board.getCurX() - 1, board.getCurY());
            }
            if (isInKeys(KeyEvent.VK_D) && !isInKeys(KeyEvent.VK_A)) {
                board.tryMove(board.getCurPiece(), board.getCurX() + 1, board.getCurY());
            }
            if (isInKeys(KeyEvent.VK_Q) && !isInKeys(KeyEvent.VK_E)) {
                board.tryMove(board.getCurPiece().rotateLeft(), board.getCurX(), board.getCurY());
            }
            if (isInKeys(KeyEvent.VK_E) && !isInKeys(KeyEvent.VK_Q)) {
                board.tryMove(board.getCurPiece().rotateRight(), board.getCurX(), board.getCurY());
            }
            if (isInKeys(KeyEvent.VK_S)) {
                board.oneLineDown();
            }
            if (isInKeys(KeyEvent.VK_SPACE)) {
                board.dropDown();
            }
        }
    }

    public Board_view_interface getBvi() {
        return bvi;
    }

}
