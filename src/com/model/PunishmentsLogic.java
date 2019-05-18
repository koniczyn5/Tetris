package com.model;

import javax.swing.*;
import java.util.Random;

public class PunishmentsLogic {

    public enum PunishmentTypes {noLeftRotation, noRightRotation, fasterTimer}

    private Punishment[] punishments;
    private final int PUNISHMENT_DURATION;

    public PunishmentsLogic(int punishmentDuration) {
        PUNISHMENT_DURATION=punishmentDuration;
        punishments= new Punishment[]{new Punishment(0, 40, new ImageIcon("src/images/noLeftRotation.png")),
                                      new Punishment(40,80, new ImageIcon("src/images/noRightRotation.png")),
                                      new Punishment(80,100, new ImageIcon("src/images/feather.png"))};
    }

    public void punish() {
        Random r=new Random();
        int chance= r.nextInt(101);
        for(int index=0; index<3; index++)
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
        for(int index=0; index<3; index++)
            if (punishments[index].isActive()) {
                punishments[index].setCurrentTimeLeft(punishments[index].getCurrentTimeLeft() - timePassed);
                if (punishments[index].getCurrentTimeLeft() < 0)
                    punishments[index].setActive(false);
            }
    }

    public boolean checkStatus(PunishmentTypes punishmentType) { return punishments[punishmentType.ordinal()].isActive(); }

    public int getTimeLeft(PunishmentTypes punishmentType) { return punishments[punishmentType.ordinal()].getCurrentTimeLeft();}

    public ImageIcon getImage(PunishmentTypes punishmentType) { return punishments[punishmentType.ordinal()].getImage();}
}
