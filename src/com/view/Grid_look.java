package com.view;

import com.model.PunishmentsLogic;

import javax.swing.*;
import java.awt.*;

public class Grid_look extends JPanel {

    private PunishmentsLogic punishmentsLogic;
    private Punishment_look[] punishmentLooks;
    private final PunishmentsLogic.PunishmentTypes[] punishmentTypes;

    Grid_look(PunishmentsLogic punishmentsLogic){
        setLayout(new GridLayout(2,1));
        this.punishmentsLogic=punishmentsLogic;
        punishmentTypes= PunishmentsLogic.PunishmentTypes.values();
        punishmentLooks= new Punishment_look[3];
        JPanel firstRow=new JPanel();
        firstRow.setLayout(new FlowLayout());
        //firstRow.setBounds(0,0,200,200);
        for(int index=0; index<3; index++) {
            punishmentLooks[index]=new Punishment_look(punishmentsLogic.getImage(punishmentTypes[index]));
            //punishmentLooks[index].setBounds(0,0,60,200);
            firstRow.add(punishmentLooks[index]);
        }
        add(firstRow);

    }

    public void updateGrid() {
        for(int index=0; index<3; index++) punishmentLooks[index].updateBar(punishmentsLogic.getTimeLeft(punishmentTypes[index]));
    }
}
