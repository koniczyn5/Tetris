package com.model;

import javax.swing.*;
import java.util.Random;

public class PunishmentsLogic {

    public enum PunishmentTypes {noLeftRotation, noRightRotation, fasterTimer}

    private Punishment[] punishments;
    private final int PUNISHMENT_DURATION;
    private final int NUMBER_OF_PUNISHMENTS;

    public PunishmentsLogic(int punishmentDuration) {
        PUNISHMENT_DURATION=punishmentDuration;
        NUMBER_OF_PUNISHMENTS=3;
        punishments= new Punishment[]{new Punishment(0, 40, new ImageIcon(getClass().getResource("/images/noLeftRotation.png"))),
                                      new Punishment(40,80, new ImageIcon(getClass().getResource("/images/noRightRotation.png"))),
                                      new Punishment(80,100, new ImageIcon(getClass().getResource("/images/feather.png")))};
    }

    public void start()
    {
        for (int index=0; index<NUMBER_OF_PUNISHMENTS; index++) {
            punishments[index].setActive(false);
            punishments[index].setCurrentTimeLeft(0);
        }
    }

    public void punish() {
        Random r=new Random();
        int chance= r.nextInt(101);
        for(int index=0; index<NUMBER_OF_PUNISHMENTS; index++)
            if (chance >= punishments[index].getLOWER_CHANCE_BOUND() && chance < punishments[index].getUPPER_CHANCE_BOUND()) {
                if (punishments[index].isActive())
                    punishments[index].setCurrentTimeLeft(punishments[index].getCurrentTimeLeft() + PUNISHMENT_DURATION);
                else {
                    punishments[index].setActive(true);
                    punishments[index].setCurrentTimeLeft(PUNISHMENT_DURATION);
                }
                System.out.println("Punished "+index);
                break;
            }
    }

    public void updateTimes(int timePassed) {
        for(int index=0; index<NUMBER_OF_PUNISHMENTS; index++)
            if (punishments[index].isActive()) {
                punishments[index].setCurrentTimeLeft(punishments[index].getCurrentTimeLeft() - timePassed);
                if (punishments[index].getCurrentTimeLeft() < 0) {
                    punishments[index].setActive(false);
                    System.out.println("Punish "+index+" ends");
                }
            }
    }

    public boolean checkStatus(PunishmentTypes punishmentType) { return punishments[punishmentType.ordinal()].isActive(); }


    public int getTimeLeft(PunishmentTypes punishmentType) { return punishments[punishmentType.ordinal()].getCurrentTimeLeft(); }

    public ImageIcon getImage(PunishmentTypes punishmentType) { return punishments[punishmentType.ordinal()].getImage(); }

    public int getNUMBER_OF_PUNISHMENTS() { return NUMBER_OF_PUNISHMENTS; }
}
