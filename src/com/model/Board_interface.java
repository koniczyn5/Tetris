package com.model;

public interface Board_interface {

    Shape.Tetrominoe[] getBoard();
    Shape getCurPiece();
    int getCurX();
    int getCurY();
    int getBoardWidth();
    int getBoardHeight();
}
