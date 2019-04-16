package com.view;

import com.controller.Main_controller;
import com.model.Board;
import com.model.Shape;

import javax.swing.*;
import java.awt.*;

public class Board_look extends JPanel implements Board_view_interface {

    private int BOARD_WIDTH;
    private int BOARD_HEIGHT;
    private Board board;
    private Game parent;

    public Board_look(Game parent, int board_width, int board_height)
    {
        setFocusable(true);
        BOARD_WIDTH=board_width;
        BOARD_HEIGHT=board_height;
        this.parent=parent;
        board = new Board(this, board_width, board_height);
        addKeyListener(new Main_controller(board));
    }

    private int squareWidth() {
        return (int) getSize().getWidth() / BOARD_WIDTH;
    }

    private int squareHeight() {
        return (int) getSize().getHeight() / BOARD_HEIGHT;
    }

    private Shape.Tetrominoe shapeAt(int x, int y) { return board.getBoard()[(y * BOARD_WIDTH) + x];
    }

    public void doDrawing(Graphics g) {

        Dimension size = getSize();
        int boardTop = (int) size.getHeight() - BOARD_HEIGHT * squareHeight();

        for (int i = 0; i < BOARD_HEIGHT; ++i) {

            for (int j = 0; j < BOARD_WIDTH; ++j) {

                Shape.Tetrominoe shape = shapeAt(j, BOARD_HEIGHT - i - 1);

                if (shape != Shape.Tetrominoe.NoShape) {

                    drawSquare(g, j * squareWidth(),
                            boardTop + i * squareHeight(), shape);
                }
            }
        }

        if (board.getCurPiece().getShape() != Shape.Tetrominoe.NoShape) {

            for (int i = 0; i < 4; ++i) {

                int x = board.getCurX() + board.getCurPiece().x(i);
                int y = board.getCurY() - board.getCurPiece().y(i);
                drawSquare(g, x * squareWidth(),
                        boardTop + (BOARD_HEIGHT - y - 1) * squareHeight(),
                        board.getCurPiece().getShape());
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    public void drawSquare(Graphics g, int x, int y, Shape.Tetrominoe shape) {

        Color []colors = {
                new Color(0, 0, 0), new Color(204, 102, 102),
                new Color(102, 204, 102), new Color(102, 102, 204),
                new Color(204, 204, 102), new Color(204, 102, 204),
                new Color(102, 204, 204), new Color(218, 170, 0)
        };

        Color color = colors[shape.ordinal()];

        g.setColor(color);
        g.fillRect(x + 1, y + 1, squareWidth() - 2, squareHeight() - 2);

        g.setColor(color.brighter());
        g.drawLine(x, y + squareHeight() - 1, x, y);
        g.drawLine(x, y, x + squareWidth() - 1, y);

        g.setColor(color.darker());
        g.drawLine(x + 1, y + squareHeight() - 1,
                x + squareWidth() - 1, y + squareHeight() - 1);
        g.drawLine(x + squareWidth() - 1, y + squareHeight() - 1,
                x + squareWidth() - 1, y + 1);

    }

    public Game getParent() {return parent;}

    public Board getBoard() {return board;}
}
