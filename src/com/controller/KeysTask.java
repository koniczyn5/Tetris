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
        mainBoard=mainController.getMainBoard();
        dropBoard=mainController.getDropBoard();
    }
    @Override
    public void run() {
        if (!mainController.isStarted()) {
            if (isInKeys(KeyEvent.VK_R)) {
                System.out.println("Restarting");
                mainController.start();
            }
            if (isInKeys(KeyEvent.VK_ESCAPE)) System.exit(0);
            return;
        }
        if ((isInKeys(KeyEvent.VK_ESCAPE) && mainController.isNotInAlreadyPressedKeys(KeyEvent.VK_ESCAPE)) ||
            (isInKeys(KeyEvent.VK_P) && mainController.isNotInAlreadyPressedKeys(KeyEvent.VK_P))) {
            System.out.println("Pausing");
            mainController.pause();
            if(isInKeys(KeyEvent.VK_ESCAPE)) mainController.addToAlreadyPressedKeys(KeyEvent.VK_ESCAPE);
            if(isInKeys(KeyEvent.VK_P)) mainController.addToAlreadyPressedKeys(KeyEvent.VK_P);
            return;
        }
        if (mainController.isPaused()) {
            return;
        }
        if(mainBoard.isFalling()) {
            if ((isInKeys(KeyEvent.VK_A) || isInKeys(KeyEvent.VK_LEFT))
                && !isInKeys(KeyEvent.VK_D) && !isInKeys(KeyEvent.VK_RIGHT)) {
                System.out.println("Move Left");
                mainBoard.tryMove(mainBoard.getCurPiece(), mainBoard.getCurX() - 1, mainBoard.getCurY());
            }
            if ((isInKeys(KeyEvent.VK_D) || isInKeys(KeyEvent.VK_RIGHT))
                && !isInKeys(KeyEvent.VK_A) && !isInKeys(KeyEvent.VK_LEFT)) {
                System.out.println("Move Right");
                mainBoard.tryMove(mainBoard.getCurPiece(), mainBoard.getCurX() + 1, mainBoard.getCurY());
            }
            if ((isInKeys(KeyEvent.VK_Q) || isInKeys(KeyEvent.VK_COMMA))
                && !isInKeys(KeyEvent.VK_E) && !isInKeys((KeyEvent.VK_PERIOD))) {
                System.out.println("Rotate Left");
                mainBoard.tryMove(mainBoard.getCurPiece().rotateLeft(), mainBoard.getCurX(), mainBoard.getCurY());
            }
            if ((isInKeys(KeyEvent.VK_E) || isInKeys(KeyEvent.VK_PERIOD))
                && !isInKeys(KeyEvent.VK_Q) && !isInKeys(KeyEvent.VK_COMMA)) {
                System.out.println("Rotate Right");
                mainBoard.tryMove(mainBoard.getCurPiece().rotateRight(), mainBoard.getCurX(), mainBoard.getCurY());
            }
            if (isInKeys(KeyEvent.VK_S) || isInKeys(KeyEvent.VK_DOWN)) {
                System.out.println("One Line Down");
                mainBoard.oneLineDown();
            }
            if (isInKeys(KeyEvent.VK_SPACE) && mainController.isNotInAlreadyPressedKeys(KeyEvent.VK_SPACE)) {
                System.out.println("Drop Down");
                mainBoard.dropDown();
                mainController.addToAlreadyPressedKeys(KeyEvent.VK_SPACE);
            }
            mainController.getMainBoardView().repaint();
            return;
        }
        if(dropBoard.isDropping())
        {
            if ((isInKeys(KeyEvent.VK_A) || isInKeys(KeyEvent.VK_LEFT))
                    && !isInKeys(KeyEvent.VK_D) && !isInKeys(KeyEvent.VK_RIGHT)) {
                System.out.println("Move Left");
                dropBoard.tryMove(dropBoard.getCurPiece(), dropBoard.getCurX() - 1, dropBoard.getCurY());
            }
            if ((isInKeys(KeyEvent.VK_D) || isInKeys(KeyEvent.VK_RIGHT))
                    && !isInKeys(KeyEvent.VK_A) && !isInKeys(KeyEvent.VK_LEFT)) {
                System.out.println("Move Right");
                dropBoard.tryMove(dropBoard.getCurPiece(), dropBoard.getCurX() + 1, dropBoard.getCurY());
            }
            if ((isInKeys(KeyEvent.VK_Q) || isInKeys(KeyEvent.VK_COMMA))
                    && !isInKeys(KeyEvent.VK_E) && !isInKeys((KeyEvent.VK_PERIOD))) {
                System.out.println("Rotate Left");
                Shape rotatedPiece=dropBoard.getCurPiece().rotateLeft();
                int newY = dropBoard.getBOARD_HEIGHT() - 1 + rotatedPiece.minY();
                dropBoard.tryMove(rotatedPiece, dropBoard.getCurX(), newY);
            }
            if ((isInKeys(KeyEvent.VK_E) || isInKeys(KeyEvent.VK_PERIOD))
                    && !isInKeys(KeyEvent.VK_Q) && !isInKeys(KeyEvent.VK_COMMA)) {
                System.out.println("Rotate Right");
                Shape rotatedPiece=dropBoard.getCurPiece().rotateRight();
                int newY = dropBoard.getBOARD_HEIGHT() - 1 + rotatedPiece.minY();
                dropBoard.tryMove(rotatedPiece, dropBoard.getCurX(), newY);
            }
            if (isInKeys(KeyEvent.VK_SPACE) && mainController.isNotInAlreadyPressedKeys(KeyEvent.VK_SPACE)) {
                System.out.println("Drop Down");
                mainController.DropToMainBoard();
                mainController.cancelCountdownTimer();
                //TODO Give points for droping earlier
                mainController.addToAlreadyPressedKeys(KeyEvent.VK_SPACE);
            }
            mainController.getDropBoardView().repaint();
        }
    }

    private boolean isInKeys(Integer key)
    {
        return mainController.isInKeys(key);
    }
}
