package com.example.opensourceimmersioncirculator;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.opensourceimmersioncirculator.databinding.FragmentTempBinding;
import com.example.opensourceimmersioncirculator.databinding.FragmentTimerBinding;

public class timerFragment extends Fragment implements settings.OnTimertickedListener{
    private FragmentTimerBinding binding;

    //listener

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        binding = FragmentTimerBinding.inflate(inflater, container, false);
        settings.registerTimerListener(this);
        settings.inTimerFragment = true;
        if (settings.darkMode) {
            binding.getRoot().setBackgroundColor(getResources().getColor(R.color.DarkModeBackground));
        } else {
            binding.getRoot().setBackgroundColor(getResources().getColor(R.color.LightModeBackground));
        }


        binding.hourTensUpButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                settings.timerSetTensHours = settings.timerSetTensHours + 1;
                if(settings.timerSetTensHours > 9){
                    settings.timerSetTensHours = 0;
                }
                settings.timer = settings.setTimer(settings.timer);
            }
        });

        binding.hourTensDownButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                settings.timerSetTensHours = settings.timerSetTensHours - 1;
                if(settings.timerSetTensHours < 0){
                    settings.timerSetTensHours = 9;
                }
                settings.timer = settings.setTimer(settings.timer);
            }
        });

        binding.hourOnesUpButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                settings.timerSetOnesHours = settings.timerSetOnesHours + 1;
                if(settings.timerSetOnesHours > 9){
                    settings.timerSetOnesHours = 0;
                }
                settings.timer = settings.setTimer(settings.timer);
            }

        });

        binding.hourOnesDownButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                settings.timerSetOnesHours = settings.timerSetOnesHours - 1;
                if(settings.timerSetOnesHours < 0){
                    settings.timerSetOnesHours = 9;
                }
                settings.timer = settings.setTimer(settings.timer);
            }
        });

        binding.minutesTensUpButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                settings.timerSetTensMinutes = settings.timerSetTensMinutes + 1;
                if(settings.timerSetTensMinutes > 5){
                    settings.timerSetTensMinutes = 0;
                }
                settings.timer = settings.setTimer(settings.timer);
            }
        });

        binding.minutesTensDownButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                settings.timerSetTensMinutes = settings.timerSetTensMinutes - 1;
                if(settings.timerSetTensMinutes < 0){
                    settings.timerSetTensMinutes = 5;
                }
                settings.timer = settings.setTimer(settings.timer);
            }
        });

        binding.minutesOnesUpButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                settings.timerSetOnesMinutes = settings.timerSetOnesMinutes + 1;
                if(settings.timerSetOnesMinutes > 9){
                    settings.timerSetOnesMinutes = 0;
                }
                settings.timer = settings.setTimer(settings.timer);
            }
        });

        binding.minutesOnesDownButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                settings.timerSetOnesMinutes = settings.timerSetOnesMinutes - 1;
                if(settings.timerSetOnesMinutes < 0){
                    settings.timerSetOnesMinutes = 9;
                }
                settings.timer = settings.setTimer(settings.timer);
            }
        });

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        refreshCountDown();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        settings.inTimerFragment = false;
        //binding = null;
    }

    @Override
    public void refreshCountDown() {
        if(isVisible()){
            binding.timerCircle.setProgress((int)(((float)settings.totalRemainingMinutes/(float)settings.totalSetMinutes) * 100.0));
            int rHT = (int)(settings.totalRemainingMinutes / (10.0 * 60.0));
            int rHO = (int)((settings.totalRemainingMinutes/ 60.0) - rHT * 10);
            int rMT = (int)((settings.totalRemainingMinutes - rHT * 10 * 60 - rHO * 60)/10.0);
            int rMO = settings.totalRemainingMinutes - rHT * 10 * 60 - rHO * 60 - rMT * 10;

            settings.timerSetTensHours = rHT;
            settings.timerSetOnesHours = rHO;
            settings.timerSetTensMinutes = rMT;
            settings.timerSetOnesMinutes = rMO;

            binding.timerDisplay.setText( rHT + "" + rHO + ":" + rMT+ ""+ rMO);
        }
    }
}