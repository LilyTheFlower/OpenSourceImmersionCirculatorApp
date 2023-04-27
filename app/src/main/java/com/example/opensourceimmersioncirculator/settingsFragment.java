package com.example.opensourceimmersioncirculator;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.opensourceimmersioncirculator.databinding.FragmentHomeBinding;
import com.example.opensourceimmersioncirculator.databinding.FragmentSettingsBinding;
public class settingsFragment extends Fragment {
    private FragmentSettingsBinding binding;

    public settingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        if(settings.darkMode){
            binding.themeSwitch.setChecked(true);
        }else{
            binding.themeSwitch.setChecked(false);
        }

        if(settings.useImperial){
            binding.unitSwitch.setChecked(false);
        }else{
            binding.unitSwitch.setChecked(true);
        }

        if(settings.darkMode){
            binding.bluetoothText.setTextColor(getResources().getColor(R.color.LightModeBackground));
            binding.unitsText.setTextColor(getResources().getColor(R.color.LightModeBackground));
            binding.themeText.setTextColor(getResources().getColor(R.color.LightModeBackground));
            binding.imperialText.setTextColor(getResources().getColor(R.color.LightModeBackground));
            binding.metricText.setTextColor(getResources().getColor(R.color.LightModeBackground));
            binding.lightText.setTextColor(getResources().getColor(R.color.LightModeBackground));
            binding.darkText.setTextColor(getResources().getColor(R.color.LightModeBackground));

            binding.getRoot().setBackgroundColor(getResources().getColor(R.color.DarkModeBackground));
        }else{
            binding.bluetoothText.setTextColor(getResources().getColor(R.color.DarkModeBackground));
            binding.unitsText.setTextColor(getResources().getColor(R.color.DarkModeBackground));
            binding.themeText.setTextColor(getResources().getColor(R.color.DarkModeBackground));
            binding.imperialText.setTextColor(getResources().getColor(R.color.DarkModeBackground));
            binding.metricText.setTextColor(getResources().getColor(R.color.DarkModeBackground));
            binding.lightText.setTextColor(getResources().getColor(R.color.DarkModeBackground));
            binding.darkText.setTextColor(getResources().getColor(R.color.DarkModeBackground));

            binding.getRoot().setBackgroundColor(getResources().getColor(R.color.LightModeBackground));
        }

        binding.themeSwitch.setChecked(settings.darkMode);

        binding.unitSwitch.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                if(binding.unitSwitch.isChecked()){
                    settings.useImperial = false;
                }else{
                    settings.useImperial = true;
                }
            }
        });

        binding.themeSwitch.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                if(binding.themeSwitch.isChecked()){
                    settings.darkMode = true;
                }else{
                    settings.darkMode = false;
                }
                if(settings.darkMode){
                    binding.bluetoothText.setTextColor(getResources().getColor(R.color.LightModeBackground));
                    binding.unitsText.setTextColor(getResources().getColor(R.color.LightModeBackground));
                    binding.themeText.setTextColor(getResources().getColor(R.color.LightModeBackground));
                    binding.imperialText.setTextColor(getResources().getColor(R.color.LightModeBackground));
                    binding.metricText.setTextColor(getResources().getColor(R.color.LightModeBackground));
                    binding.lightText.setTextColor(getResources().getColor(R.color.LightModeBackground));
                    binding.darkText.setTextColor(getResources().getColor(R.color.LightModeBackground));

                    binding.getRoot().setBackgroundColor(getResources().getColor(R.color.DarkModeBackground));
                }else{
                    binding.bluetoothText.setTextColor(getResources().getColor(R.color.DarkModeBackground));
                    binding.unitsText.setTextColor(getResources().getColor(R.color.DarkModeBackground));
                    binding.themeText.setTextColor(getResources().getColor(R.color.DarkModeBackground));
                    binding.imperialText.setTextColor(getResources().getColor(R.color.DarkModeBackground));
                    binding.metricText.setTextColor(getResources().getColor(R.color.DarkModeBackground));
                    binding.lightText.setTextColor(getResources().getColor(R.color.DarkModeBackground));
                    binding.darkText.setTextColor(getResources().getColor(R.color.DarkModeBackground));

                    binding.getRoot().setBackgroundColor(getResources().getColor(R.color.LightModeBackground));
                }
            }
        });
        return binding.getRoot();
    }


}