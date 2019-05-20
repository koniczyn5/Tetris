package com.model;

import javax.swing.*;

public class SuperpowersLogic {

    public enum SuperpowerTypes {clearBoard, slowerTimer}

    private Superpower[] superpowers;
    private final int SUPERPOWER_DURATION;
    private final int NUMBER_OF_SUPERPOWERS;

    public SuperpowersLogic(int superpowersDuration) {
        SUPERPOWER_DURATION=superpowersDuration;
        NUMBER_OF_SUPERPOWERS=2;
        superpowers= new Superpower[]{new Superpower(30000, new ImageIcon(getClass().getResource("/images/bomb.png")),"Clear board"),
                     new Superpower(10000, new ImageIcon(getClass().getResource("/images/iceCube.png")),"Slowdown timer")};
    }

    public void start()
    {
        for (int index=0; index<NUMBER_OF_SUPERPOWERS; index++) {
            superpowers[index].start();
        }
    }

    public void updateTimes(int timePassed) {
        for(int index=0; index<NUMBER_OF_SUPERPOWERS; index++)
            if (superpowers[index].isActive()) {
                superpowers[index].setCurrentTimeLeft(superpowers[index].getCurrentTimeLeft() - timePassed);
                if (superpowers[index].getCurrentTimeLeft() < 0) {
                    superpowers[index].setActive(false);
                }
            }
    }

    public boolean activateSuperpower(SuperpowerTypes superpowerType, int currentScore) {
        if(currentScore>=superpowers[superpowerType.ordinal()].getPrice())
        {
            if(superpowers[superpowerType.ordinal()].isActive())
            {
                superpowers[superpowerType.ordinal()].setCurrentTimeLeft(superpowers[superpowerType.ordinal()].getCurrentTimeLeft()+SUPERPOWER_DURATION);
            }
            else
            {
                if(superpowerType!= SuperpowerTypes.clearBoard)
                {
                    superpowers[superpowerType.ordinal()].setActive(true);
                    superpowers[superpowerType.ordinal()].setCurrentTimeLeft(SUPERPOWER_DURATION);
                }
            }
            superpowers[superpowerType.ordinal()].increasePrice();
            return true;
        }
        return false;
    }

    public boolean checkStatus(SuperpowersLogic.SuperpowerTypes superpowerType) { return superpowers[superpowerType.ordinal()].isActive(); }

    public int getTimeLeft(SuperpowersLogic.SuperpowerTypes superpowerType) { return superpowers[superpowerType.ordinal()].getCurrentTimeLeft(); }

    public ImageIcon getImage(SuperpowersLogic.SuperpowerTypes superpowerType) { return superpowers[superpowerType.ordinal()].getImage(); }

    public String getTitle(SuperpowersLogic.SuperpowerTypes superpowerType) { return superpowers[superpowerType.ordinal()].getTitle(); }

    public float getPrice(SuperpowersLogic.SuperpowerTypes superpowerType) { return superpowers[superpowerType.ordinal()].getPrice();}

    public int getNUMBER_OF_SUPERPOWERS() { return NUMBER_OF_SUPERPOWERS; }
}
