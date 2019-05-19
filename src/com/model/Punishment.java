package com.model;

import javax.swing.*;

public class Punishment {

    private boolean isActive;
    private int currentTimeLeft;
    private final int LOWER_CHANCE_BOUND;
    private final int UPPER_CHANCE_BOUND;
    private ImageIcon image;

    public Punishment(int lowerChanceBound, int upperChanceBound, ImageIcon image)
    {
        isActive=false;
        currentTimeLeft=0;
        LOWER_CHANCE_BOUND=lowerChanceBound;
        UPPER_CHANCE_BOUND=upperChanceBound;
        this.image=image;
    }

    public boolean isActive() { return isActive; }

    public void setActive(boolean active) { isActive = active; }

    public int getCurrentTimeLeft() { return currentTimeLeft; }

    public void setCurrentTimeLeft(int currentTimeLeft) { this.currentTimeLeft = currentTimeLeft; }

    public int getLOWER_CHANCE_BOUND() { return LOWER_CHANCE_BOUND; }

    public int getUPPER_CHANCE_BOUND() { return UPPER_CHANCE_BOUND; }

    public ImageIcon getImage() { return image; }
}
