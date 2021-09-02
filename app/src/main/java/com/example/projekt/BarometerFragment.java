package com.example.projekt;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BarometerFragment extends Fragment implements SensorEventListener {

    private TextView textView;
    private SensorManager sensorManager;
    private Sensor pressureSensor;
    private boolean isPressureSensorAvailable;



    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_barometer, container, false);

        textView = view.findViewById(R.id.editTextBarometer);
        sensorManager =  (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);

       if(sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE) != null){
           pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
           isPressureSensorAvailable = true;
       } else {
           Toast.makeText(getContext(), "Pressure Senosr is not Available", Toast.LENGTH_SHORT).show();
           isPressureSensorAvailable = false;
       }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(isPressureSensorAvailable){
            sensorManager.registerListener(this, pressureSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(isPressureSensorAvailable) {
            sensorManager.unregisterListener(this);
        }
    }

    /**
     * Called when there is a new sensor event.  Note that "on changed"
     * is somewhat of a misnomer, as this will also be called if we have a
     * new reading from a sensor with the exact same sensor values (but a
     * newer timestamp).
     *
     * <p>See {@link SensorManager SensorManager}
     * for details on possible sensor types.
     * <p>See also {@link SensorEvent SensorEvent}.
     *
     * <p><b>NOTE:</b> The application doesn't own the
     * {@link SensorEvent event}
     * object passed as a parameter and therefore cannot hold on to it.
     * The object may be part of an internal pool and may be reused by
     * the framework.
     *
     * @param event the {@link SensorEvent SensorEvent}.
     */
    @Override
    public void onSensorChanged(SensorEvent event) {
        textView.setText(event.values[0] + " mbar");
    }

    /**
     * Called when the accuracy of the registered sensor has changed.  Unlike
     * onSensorChanged(), this is only called when this accuracy value changes.
     *
     * <p>See the SENSOR_STATUS_* constants in
     * {@link SensorManager SensorManager} for details.
     *
     * @param sensor
     * @param accuracy The new accuracy of this sensor, one of
     *                 {@code SensorManager.SENSOR_STATUS_*}
     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}




