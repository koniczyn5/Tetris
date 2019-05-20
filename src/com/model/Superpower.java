package com.model;

import javax.swing.*;

public class Superpower {

    private boolean isActive;
    private int currentTimeLeft;
    private int price;
    private ImageIcon image;
    private String title;

    public Superpower(int cost, ImageIcon image, String title)
    {
        isActive=false;
        currentTimeLeft=0;
        price=cost;
        this.image=image;
        this.title=title;
    }

    public boolean isActive() { return isActive; }

    public void setActive(boolean active) { isActive = active; }

    public int getCurrentTimeLeft() { return currentTimeLeft; }

    public void setCurrentTimeLeft(int currentTimeLeft) { this.currentTimeLeft = currentTimeLeft; }

    public int getPrice() { return price; }

    public ImageIcon getImage() { return image; }

    public String getTitle() { return title; }
}
