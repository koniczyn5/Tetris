package com.model;

import javax.swing.*;

class Punishment {

    private boolean isActive;
    private int currentTimeLeft;
    private final int LOWER_CHANCE_BOUND;
    private final int UPPER_CHANCE_BOUND;
    private ImageIcon image;

    Punishment(int lowerChanceBound, int upperChanceBound, ImageIcon image)
    {
        isActive=false;
        currentTimeLeft=0;
        LOWER_CHANCE_BOUND=lowerChanceBound;
        UPPER_CHANCE_BOUND=upperChanceBound;
        this.image=image;
    }

    boolean isActive() { return isActive; }

    void setActive(boolean active) { isActive = active; }

    int getCurrentTimeLeft() { return currentTimeLeft; }

    void setCurrentTimeLeft(int currentTimeLeft) { this.currentTimeLeft = currentTimeLeft; }

    int getLOWER_CHANCE_BOUND() { return LOWER_CHANCE_BOUND; }

    int getUPPER_CHANCE_BOUND() { return UPPER_CHANCE_BOUND; }

    ImageIcon getImage() { return image; }
}
