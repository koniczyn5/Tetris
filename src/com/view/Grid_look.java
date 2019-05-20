package com.view;

import com.model.PunishmentsLogic;
import com.model.SuperpowersLogic;

import javax.swing.*;
import java.awt.*;

public class Grid_look extends JPanel {

    private PunishmentsLogic punishmentsLogic;
    private Punishment_look[] punishmentLooks;
    private final PunishmentsLogic.PunishmentTypes[] punishmentTypes;

    private SuperpowersLogic superpowersLogic;
    private Superpowers_look[] superpowersLooks;
    private  final SuperpowersLogic.SuperpowerTypes[] superpowerTypes;

    Grid_look(PunishmentsLogic punishmentsLogic, SuperpowersLogic superpowersLogic){
        setLayout(new GridLayout(2,1));
        this.punishmentsLogic=punishmentsLogic;
        punishmentTypes= PunishmentsLogic.PunishmentTypes.values();
        punishmentLooks= new Punishment_look[punishmentsLogic.getNUMBER_OF_PUNISHMENTS()];
        JLabel negativeEffectsText=new JLabel("",SwingConstants.CENTER);
        negativeEffectsText.setText("Negative Effects:");
        JPanel firstRow=new JPanel();
        firstRow.setLayout(new BorderLayout());
        firstRow.add(negativeEffectsText,BorderLayout.NORTH);
        JPanel punishmentsRow = new JPanel();
        for(int index=0; index<punishmentsLogic.getNUMBER_OF_PUNISHMENTS(); index++) {
            punishmentLooks[index]=new Punishment_look(punishmentsLogic.getImage(punishmentTypes[index]));
            punishmentsRow.add(punishmentLooks[index]);
        }
        firstRow.add(punishmentsRow);
        add(firstRow);
        JLabel superPowersText=new JLabel("", SwingConstants.CENTER);
        superPowersText.setText("SuperPowers:");
        JPanel superpowersRow = new JPanel();
        this.superpowersLogic=superpowersLogic;
        superpowerTypes = SuperpowersLogic.SuperpowerTypes.values();
        superpowersLooks = new Superpowers_look[superpowersLogic.getNUMBER_OF_SUPERPOWERS()];
        JPanel secondRow = new JPanel();
        secondRow.setLayout(new BorderLayout());
        secondRow.add(superPowersText, BorderLayout.NORTH);
        for(int index=0; index<superpowersLogic.getNUMBER_OF_SUPERPOWERS(); index++) {
            superpowersLooks[index]=new Superpowers_look(superpowersLogic.getImage(superpowerTypes[index]),superpowersLogic.getTitle(superpowerTypes[index]));
            superpowersRow.add(superpowersLooks[index]);
        }
        secondRow.add(superpowersRow);
        add(secondRow);
    }

    public void updateGrid() {
        for(int index=0; index<punishmentsLogic.getNUMBER_OF_PUNISHMENTS(); index++)
            punishmentLooks[index].updateBar(punishmentsLogic.getTimeLeft(punishmentTypes[index]));
        for(int index=0; index<superpowersLogic.getNUMBER_OF_SUPERPOWERS(); index++)
            superpowersLooks[index].updateBar(superpowersLogic.getTimeLeft(superpowerTypes[index]),(int) superpowersLogic.getPrice(superpowerTypes[index]));
    }
}
