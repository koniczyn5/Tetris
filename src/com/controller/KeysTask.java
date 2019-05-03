package com.controller;

import com.model.DropBoard;
import com.model.MainBoard;
import com.model.Shape;

import java.awt.event.KeyEvent;
import java.util.TimerTask;

public class KeysTask extends TimerTask {
    private Main_controller mainController;
    private MainBoard mainBoard;
    private DropBoard dropBoard;
    KeysTask(Main_controller controller){
        mainController=controller;
        mainBoard=mainController.getBvi().getMainBoard();
        dropBoard=mainController.getDbvi().getDropBoard();
    }
    @Override
    public void run() {
        if (!mainController.isStarted()) {
            if (mainController.isInKeys(KeyEvent.VK_R)) {
                System.out.println("Restarting");
                mainController.start();
            }
            return;
        }
        if (mainController.isInKeys(KeyEvent.VK_ESCAPE)) {
            System.out.println("Pausing");
            mainBoard.pause();
            return;
        }
        if (mainController.isPaused()) {
            return;
        }
        if(mainController.isFalling()) {
            if ((mainController.isInKeys(KeyEvent.VK_A) || mainController.isInKeys(KeyEvent.VK_LEFT))
                && !mainController.isInKeys(KeyEvent.VK_D) && !mainController.isInKeys(KeyEvent.VK_RIGHT)) {
                System.out.println("Move Left");
                mainBoard.tryMove(mainBoard.getCurPiece(), mainBoard.getCurX() - 1, mainBoard.getCurY());
            }
            if ((mainController.isInKeys(KeyEvent.VK_D) || mainController.isInKeys(KeyEvent.VK_RIGHT))
                && !mainController.isInKeys(KeyEvent.VK_A) && !mainController.isInKeys(KeyEvent.VK_LEFT)) {
                System.out.println("Move Right");
                mainBoard.tryMove(mainBoard.getCurPiece(), mainBoard.getCurX() + 1, mainBoard.getCurY());
            }
            if ((mainController.isInKeys(KeyEvent.VK_Q) || mainController.isInKeys(KeyEvent.VK_COMMA))
                && !mainController.isInKeys(KeyEvent.VK_E) && !mainController.isInKeys((KeyEvent.VK_PERIOD))) {
                System.out.println("Rotate Left");
                mainBoard.tryMove(mainBoard.getCurPiece().rotateLeft(), mainBoard.getCurX(), mainBoard.getCurY());
            }
            if ((mainController.isInKeys(KeyEvent.VK_E) || mainController.isInKeys(KeyEvent.VK_PERIOD))
                && !mainController.isInKeys(KeyEvent.VK_Q) && !mainController.isInKeys(KeyEvent.VK_COMMA)) {
                System.out.println("Rotate Right");
                mainBoard.tryMove(mainBoard.getCurPiece().rotateRight(), mainBoard.getCurX(), mainBoard.getCurY());
            }
            if (mainController.isInKeys(KeyEvent.VK_S) || mainController.isInKeys(KeyEvent.VK_DOWN)) {
                System.out.println("One Line Down");
                mainBoard.oneLineDown();
            }
            if (mainController.isInKeys(KeyEvent.VK_SPACE)) {
                System.out.println("Drop Down");
                mainBoard.dropDown();
            }
            return;
        }
        if(mainController.isDropping())
        {
            if ((mainController.isInKeys(KeyEvent.VK_A) || mainController.isInKeys(KeyEvent.VK_LEFT))
                    && !mainController.isInKeys(KeyEvent.VK_D) && !mainController.isInKeys(KeyEvent.VK_RIGHT)) {
                System.out.println("Move Left");
                dropBoard.tryMove(dropBoard.getCurPiece(), dropBoard.getCurX() - 1, dropBoard.getCurY());
            }
            if ((mainController.isInKeys(KeyEvent.VK_D) || mainController.isInKeys(KeyEvent.VK_RIGHT))
                    && !mainController.isInKeys(KeyEvent.VK_A) && !mainController.isInKeys(KeyEvent.VK_LEFT)) {
                System.out.println("Move Right");
                dropBoard.tryMove(dropBoard.getCurPiece(), dropBoard.getCurX() + 1, dropBoard.getCurY());
            }
            if ((mainController.isInKeys(KeyEvent.VK_Q) || mainController.isInKeys(KeyEvent.VK_COMMA))
                    && !mainController.isInKeys(KeyEvent.VK_E) && !mainController.isInKeys((KeyEvent.VK_PERIOD))) {
                System.out.println("Rotate Left");
                Shape rotatedPiece=dropBoard.getCurPiece().rotateLeft();
                int newY = dropBoard.getBOARD_HEIGHT() - 1 + rotatedPiece.minY();
                dropBoard.tryMove(rotatedPiece, dropBoard.getCurX(), newY);
            }
            if ((mainController.isInKeys(KeyEvent.VK_E) || mainController.isInKeys(KeyEvent.VK_PERIOD))
                    && !mainController.isInKeys(KeyEvent.VK_Q) && !mainController.isInKeys(KeyEvent.VK_COMMA)) {
                System.out.println("Rotate Right");
                Shape rotatedPiece=dropBoard.getCurPiece().rotateRight();
                int newY = dropBoard.getBOARD_HEIGHT() - 1 + rotatedPiece.minY();
                dropBoard.tryMove(rotatedPiece, dropBoard.getCurX(), newY);
            }
            if (mainController.isInKeys(KeyEvent.VK_SPACE)) {
                System.out.println("Drop Down");
                dropBoard.dropDown();
                mainController.cancelCountdownTimer();
                //TODO
            }
        }
    }
}
