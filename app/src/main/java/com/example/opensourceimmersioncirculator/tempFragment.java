package com.example.opensourceimmersioncirculator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.opensourceimmersioncirculator.databinding.FragmentTempBinding;

public class tempFragment extends Fragment {

    private FragmentTempBinding binding;
    DisplayMetrics displayMetrics = new DisplayMetrics();

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        binding = FragmentTempBinding.inflate(inflater, container, false);
        ((Activity) getContext()).getWindowManager()
                .getDefaultDisplay()
                .getMetrics(displayMetrics);
        binding.tempCircle.setProgress((int)((((float)settings.targetTemperature-95.0)/108.0) * 100.0));
        if(settings.useImperial){
            binding.tempText.setText(String.valueOf(settings.targetTemperature) + "°F");
        }else{
            binding.tempText.setText(String.valueOf((int)((settings.targetTemperature - 32.0) * (5.0/9.0))) + "°C");
        }
        if(settings.darkMode){
            binding.getRoot().setBackgroundColor(getResources().getColor(R.color.DarkModeBackground));
            binding.targetTempLabel.setTextColor(getResources().getColor(R.color.LightModeBackground));
        }else{
            binding.getRoot().setBackgroundColor(getResources().getColor(R.color.LightModeBackground));
            binding.targetTempLabel.setTextColor(getResources().getColor(R.color.DarkModeBackground));
        }

        binding.tempCircle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ((Activity) getContext()).getWindowManager()
                        .getDefaultDisplay()
                        .getMetrics(displayMetrics);
                //the temperature control is on the horizontal coordinate plane compared to the screen,
                //x and y need to be flipped accordingly
                float y = (displayMetrics.widthPixels/2 - event.getRawX())*-1;
                float x = binding.tempCircle.getY()+ binding.tempCircle.getHeight()/2- event.getRawY();

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.i("TAG", "touched down");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        //Log.i("TAG", "moving: (" + x + ", " + y + ")");

                        int angle = (int)(Math.atan(y/x)*180.0/Math.PI);
                        if(x < 0){
                            angle = (int)(Math.atan(y/x)*180.0/Math.PI) + 180;
                        }else if(x > 0 && y < 0){
                            angle = (int)(Math.atan(y/x)*180.0/Math.PI) + 360;
                        }

                        if(Math.abs(x) < 10){
                            //very close to width center, so our degrees are either 90 or 270
                            if(y > 0){
                                //we are above the middle, so the degrees are 90
                                angle = 90;
                            }else{
                                //below the middle so the degrees are 270
                                angle = 270;
                            }
                        }
                        if(Math.abs(y) < 10){
                            //very close to , height center so our degrees are either 0 or 180 (no 360)
                            if(x > 0 || angle  > 359){
                                //we are above the middle, so the degrees are 90
                                angle = 0;
                            }else{
                                //below the middle so the degrees are 270
                                angle = 180;
                            }
                        }

                        //Log.i("TAG", "moving: (" +angle+")");
                        binding.tempCircle.setProgress((int)((angle /360.0) * 100.0));
                        settings.targetTemperature = (int)((angle / 360.0) * 109.0) + 95;
                        if(settings.useImperial){
                            binding.tempText.setText(String.valueOf(settings.targetTemperature) + "°F");
                        }else{
                            binding.tempText.setText(String.valueOf((int)((settings.targetTemperature - 32.0) * (5.0/9.0))) + "°C");
                        }
                        //Log.i("TAG", "(" + (int)((angle / 360.0) * 100.0) + ")");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.i("TAG", "touched up");
                        break;
                }

                return true;
            }
        });
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}