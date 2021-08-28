package com.example.projekt;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class BrightnessFragment  extends Fragment {

    private static final int TYPE_LIGHT = 5;
    private SensorManager sensorManager;
    private Sensor lightSenosr;
    private SensorEventListener lightEventListener;
    private View root;
    private  float maxValue;
    TextView editTextBrightness;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_brightness, container, false);
        root = view.findViewById(R.id.root);
        editTextBrightness = view.findViewById(R.id.editTextBrightness);

        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        lightSenosr = sensorManager.getDefaultSensor(TYPE_LIGHT);

        if(lightSenosr == null) {
            Toast.makeText(getContext(), "The device has not light sensor", Toast.LENGTH_SHORT).show();
            return view;
        }

        maxValue = lightSenosr.getMaximumRange();

        lightEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float value = event.values[0];
                editTextBrightness.setText("Luminosity: " + value + " lx");
                int newValue = (int)(255f * value / maxValue);
                root.setBackgroundColor(Color.rgb(newValue,newValue,newValue));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };


        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(lightEventListener, lightSenosr, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(lightEventListener);
    }
}
