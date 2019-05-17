package com.model;

public class Score {

    private int score;
    private final float startingScoreGain=100;
    private final float startingTimeScoreGain=50;
    private final float GainFactor =1.01f;
    private float currentScoreGain;
    private float currentTimeScoreGain;

    public Score()  { start(); }

    public void start() {
        score=0;
        currentScoreGain=startingScoreGain;
        currentTimeScoreGain=startingTimeScoreGain;
    }

    public int getScore() { return score;}

    public void addPointsForRow(int rows)
    {
        score+=rows*currentScoreGain;
        System.out.println(Math.pow(GainFactor,rows));
        System.out.println((int)Math.pow(GainFactor,rows));
        currentScoreGain= (float) (currentScoreGain*Math.pow(GainFactor,rows));
        System.out.println(currentScoreGain);
    }

    public void addPointsForTime(float time)
    {
        score+=time*currentTimeScoreGain;
        currentTimeScoreGain*= GainFactor;
    }

    public void removePoints(int points)
    {
        score-=points;
        if(score<0) score=0;
    }
}
