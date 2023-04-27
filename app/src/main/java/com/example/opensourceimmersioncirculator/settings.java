package com.example.opensourceimmersioncirculator;

import android.os.CountDownTimer;
import android.util.Log;

import androidx.navigation.NavController;

public class settings {
    public static boolean useImperial = true;
    public static boolean darkMode = false;
    public static int targetTemperature = 100;
    public static int currentTemperature = 120;
    public static int timerSetTensHours = 0;
    public static int timerSetOnesHours = 0;

    public static int timerSetTensMinutes = 0;
    public static int timerSetOnesMinutes = 0;
    public static int totalRemainingMinutes = 0;
    public static int totalSetMinutes = 0;

    public static boolean timerCountingDown = false;

    public static CountDownTimer timer;
    public static boolean firstCount = false; //we dont want to increment the timer instantly after making it, only the next time

    public static boolean inTimerFragment = false;

    public interface OnTimertickedListener{
        public void refreshCountDown();
    }
    private static OnTimertickedListener homeListener;
    private static OnTimertickedListener timerListener;

    public static void registerHomeListener(OnTimertickedListener listener)
    {
        homeListener = listener;
    }
    public static void registerTimerListener(OnTimertickedListener listener)
    {
        timerListener = listener;
    }
    public static CountDownTimer setTimer(CountDownTimer oldTimer){
        if(oldTimer != null){
            oldTimer.cancel();
        }
        settings.firstCount = true;
        totalSetMinutes = settings.timerSetTensHours * 60*10 + settings.timerSetOnesHours * 60 + settings.timerSetTensMinutes * 10 + settings.timerSetOnesMinutes;
        settings.totalRemainingMinutes = totalSetMinutes;
        return new CountDownTimer((totalSetMinutes + 1) *60*1000, 60000) {
            public void onTick(long millisUntilFinished) {
                if(settings.firstCount){
                    settings.firstCount = false;
                }else{
                    settings.totalRemainingMinutes = settings.totalRemainingMinutes - 1;

                }

                homeListener.refreshCountDown();
                timerListener.refreshCountDown();

            }
            public void onFinish() {

            }
        }.start();
    }
}



