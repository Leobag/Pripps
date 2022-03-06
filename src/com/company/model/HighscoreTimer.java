package com.company.model;

import java.util.*;

public class HighscoreTimer {

    private Timer timer;
    private TimerTask timerTask;
    private int timeCount = 0;

    public HighscoreTimer(){

    }

    /**
     * Calls for the incremented value of the timer,
     * also resets the timer
     *
     * */
    public int getHighscoreTime(){
        return this.timeCount;

    }

    /**
     * Resets the current highscore.
     *
     */

    public void resetHighscoreTimer(){
        this.timeCount = 0;
    }

    /**
     * Stops the timer
     *
     */

    public void stopTimer(){
        timer.cancel();
    }

    /**
     * Starts the timer at a rate of updating once per second.
     *
     */

    public void startTimer(){
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                timeCount++;
            }
        };
        timer.schedule(timerTask, 0, 1000);
    }
}
