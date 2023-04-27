package com.example.opensourceimmersioncirculator;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.opensourceimmersioncirculator.databinding.FragmentHomeBinding;

public class homeFragment extends Fragment implements settings.OnTimertickedListener{

    private FragmentHomeBinding binding;

    public homeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        settings.registerHomeListener(this);

        if(settings.darkMode){
            binding.getRoot().setBackgroundColor(getResources().getColor(R.color.DarkModeBackground));
            binding.homeTempDisplayLabel.setTextColor(getResources().getColor(R.color.LightModeBackground));
            binding.homeTimerDisplayLabel.setTextColor(getResources().getColor(R.color.LightModeBackground));
            binding.homeTargetTemp.setTextColor(getResources().getColor(R.color.LightModeBackground));
        }else{
            binding.getRoot().setBackgroundColor(getResources().getColor(R.color.LightModeBackground));
            binding.homeTempDisplayLabel.setTextColor(getResources().getColor(R.color.DarkModeBackground));
            binding.homeTimerDisplayLabel.setTextColor(getResources().getColor(R.color.DarkModeBackground));
            binding.homeTargetTemp.setTextColor(getResources().getColor(R.color.DarkModeBackground));
        }

        if(settings.useImperial){
            binding.homeTargetTemp.setText(String.valueOf(settings.targetTemperature) + "°F");
            binding.homeTempDisplay.setText(String.valueOf(settings.currentTemperature) + "°F");
        }else{
            binding.homeTargetTemp.setText(String.valueOf((int)((settings.targetTemperature - 32.0) * (5.0/9.0))) + "°C");
            binding.homeTempDisplay.setText(String.valueOf((int)((settings.currentTemperature - 32.0) * (5.0/9.0))) + "°C");
        }


        binding.homeTempDisplay.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                NavHostFragment.findNavController( homeFragment.this)
                        .navigate(R.id.action_homeFragment_to_tempFragment);
            }
        });

        binding.homeTimerDisplay.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                NavHostFragment.findNavController( homeFragment.this)
                        .navigate(R.id.action_homeFragment_to_timerFragment);
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
        //binding = null;
    }

    public void updateHome(){

    }


    @Override
    public void refreshCountDown() {
        if(isVisible()){
            int rHT = (int)(settings.totalRemainingMinutes / (10.0 * 60.0));
            int rHO = (int)((settings.totalRemainingMinutes/ 60.0) - rHT * 10);
            int rMT = (int)((settings.totalRemainingMinutes - rHT * 10 * 60 - rHO * 60)/10.0);
            int rMO = settings.totalRemainingMinutes - rHT * 10 * 60 - rHO * 60 - rMT * 10;

            settings.timerSetTensHours = rHT;
            settings.timerSetOnesHours = rHO;
            settings.timerSetTensMinutes = rMT;
            settings.timerSetOnesMinutes = rMO;
            Log.i("TAG", "" + settings.totalRemainingMinutes);
            binding.homeTimerDisplay.setText( rHT + "" + rHO + ":" + rMT+ ""+ rMO);
        }
    }
}