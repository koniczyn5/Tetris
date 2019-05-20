package com.model;

import javax.swing.*;

public class Superpower {

    private boolean isActive;
    private int currentTimeLeft;
    private final int INITIAL_PRICE;
    private final static float PRICE_FACTOR=1.1f;
    private float price;
    private ImageIcon image;
    private String title;

    public Superpower(int cost, ImageIcon image, String title) {
        INITIAL_PRICE = cost;
        this.image = image;
        this.title = title;
        start();
    }

    public void start()    {
        price = INITIAL_PRICE;
        isActive = false;
        currentTimeLeft = 0;
    }

    public void increasePrice() { price = price*PRICE_FACTOR;}

    public boolean isActive() { return isActive; }

    public void setActive(boolean active) { isActive = active; }

    public int getCurrentTimeLeft() { return currentTimeLeft; }

    public void setCurrentTimeLeft(int currentTimeLeft) { this.currentTimeLeft = currentTimeLeft; }

    public float getPrice() { return price; }

    public ImageIcon getImage() { return image; }

    public String getTitle() { return title; }
}
