package com.model;

import java.util.Timer;
import java.util.TimerTask;

public class Drop_board {

    private int BOARD_WIDTH;
    private int BOARD_HEIGHT;

    private boolean isDropping = false;
    private int curX = 0;
    private int curY = 0;
    private Shape curPiece;
    private Shape.Tetrominoe[] board;
    private Timer countdownTimer;
    private MainBoard mainBoard;
    private final int TIMER_TIME=2000;
    private int timerTime;
    private final int TIMER_PERIOD=10;

    public Drop_board(MainBoard mainBoard, int board_width)
    {
        initBoard(mainBoard, board_width);
    }

    private void initBoard(MainBoard mainBoard, int board_width) {

        this.mainBoard= mainBoard;
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

        isDropping=false;
        mainBoard.newPiece(curPiece,curX);
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
        curX = BOARD_WIDTH / 2 + 1;
        curY = -curPiece.minY();
        timerTime=TIMER_TIME;
        countdownTimer=new Timer();
        countdownTimer.scheduleAtFixedRate(new CountdownTimerSchedule(),0,TIMER_PERIOD);
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

    public boolean isDropping() {
        return isDropping;
    }

    private class CountdownTimerSchedule extends TimerTask {
    @Override
    public void run()
    {
        timerTime-=TIMER_PERIOD;
        if(timerTime<=0)
        {
            //zrzuc klocek i naloz kare
        }
        else
        {
            //zaktualizuj wyswietlacz czasu
        }
    }
    }
}
