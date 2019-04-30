package com.controller;

import com.model.MainBoard;
import com.model.Shape;

import java.awt.event.KeyEvent;
import java.util.TimerTask;

public class KeysTask extends TimerTask {
    private Main_controller mainController;
    private MainBoard mainBoard;
    KeysTask(Main_controller controller){
        mainController=controller;
        mainBoard=mainController.getBvi().getMainBoard();
    }
    @Override
    public void run() {
        if (!mainController.isStarted()) {
            if (mainController.isInKeys(KeyEvent.VK_R)) {
                mainBoard.start();
            }
            return;
        }
        if (mainController.isPaused() || mainBoard.getCurPiece().getShape() == Shape.Tetrominoe.NoShape) {
            return;
        }

        if (mainController.isInKeys(KeyEvent.VK_ESCAPE)) {
            mainBoard.pause();
            return;
        }

        if (mainController.isInKeys(KeyEvent.VK_A) && !mainController.isInKeys(KeyEvent.VK_D)) {
            mainBoard.tryMove(mainBoard.getCurPiece(), mainBoard.getCurX() - 1, mainBoard.getCurY());
        }
        if (mainController.isInKeys(KeyEvent.VK_D) && !mainController.isInKeys(KeyEvent.VK_A)) {
            mainBoard.tryMove(mainBoard.getCurPiece(), mainBoard.getCurX() + 1, mainBoard.getCurY());
        }
        if (mainController.isInKeys(KeyEvent.VK_Q) && !mainController.isInKeys(KeyEvent.VK_E)) {
            mainBoard.tryMove(mainBoard.getCurPiece().rotateLeft(), mainBoard.getCurX(), mainBoard.getCurY());
        }
        if (mainController.isInKeys(KeyEvent.VK_E) && !mainController.isInKeys(KeyEvent.VK_Q)) {
            mainBoard.tryMove(mainBoard.getCurPiece().rotateRight(), mainBoard.getCurX(), mainBoard.getCurY());
        }
        if (mainController.isInKeys(KeyEvent.VK_S)) {
            mainBoard.oneLineDown();
        }
        if (mainController.isInKeys(KeyEvent.VK_SPACE)) {
            mainBoard.dropDown();
        }
    }
}
