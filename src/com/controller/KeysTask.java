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
                System.out.println("Restarting");
                mainBoard.start();
            }
            return;
        }
        if (mainController.isInKeys(KeyEvent.VK_ESCAPE)) {
            System.out.println("Pausing");
            mainBoard.pause();
            return;
        }
        if (mainController.isPaused() || mainBoard.getCurPiece().getShape() == Shape.Tetrominoe.NoShape) {
            return;
        }
        if (mainController.isInKeys(KeyEvent.VK_A) && !mainController.isInKeys(KeyEvent.VK_D)) {
            System.out.println("Move Left");
            mainBoard.tryMove(mainBoard.getCurPiece(), mainBoard.getCurX() - 1, mainBoard.getCurY());
        }
        if (mainController.isInKeys(KeyEvent.VK_D) && !mainController.isInKeys(KeyEvent.VK_A)) {
            System.out.println("Move Right");
            mainBoard.tryMove(mainBoard.getCurPiece(), mainBoard.getCurX() + 1, mainBoard.getCurY());
        }
        if (mainController.isInKeys(KeyEvent.VK_Q) && !mainController.isInKeys(KeyEvent.VK_E)) {
            System.out.println("Rotate Left");
            mainBoard.tryMove(mainBoard.getCurPiece().rotateLeft(), mainBoard.getCurX(), mainBoard.getCurY());
        }
        if (mainController.isInKeys(KeyEvent.VK_E) && !mainController.isInKeys(KeyEvent.VK_Q)) {
            System.out.println("Rotate Right");
            mainBoard.tryMove(mainBoard.getCurPiece().rotateRight(), mainBoard.getCurX(), mainBoard.getCurY());
        }
        if (mainController.isInKeys(KeyEvent.VK_S)) {
            System.out.println("One Line Down");
            mainBoard.oneLineDown();
        }
        if (mainController.isInKeys(KeyEvent.VK_SPACE)) {
            System.out.println("Drop Down");
            mainBoard.dropDown();
        }
    }
}
