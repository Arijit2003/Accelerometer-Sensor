package com.example.accelerometer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.service.autofill.TextValueSanitizer;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    TextView accelerometerSensorTV;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        accelerometerSensorTV=findViewById(R.id.accelerometerSensorTV);
        SensorManager sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        if(sensorManager!=null){
            Sensor accelerometerSensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            if(accelerometerSensor!=null){
                sensorManager.registerListener(this,accelerometerSensor,SensorManager.SENSOR_DELAY_NORMAL);
            }
        }
        else{
            Toast.makeText(this, "Sensor service not detected", Toast.LENGTH_SHORT).show();
        }
        
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            String sensorValue="X:"+sensorEvent.values[0]+" Y:"+sensorEvent.values[1]+" Z:"+sensorEvent.values[2];
            accelerometerSensorTV.setText(sensorValue);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}