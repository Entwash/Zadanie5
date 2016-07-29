package com.example.jakub.zadanie5b;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    SensorManager mSensorManagerBrightness;
    SensorManager mSensorManagerProximity;

    Sensor mProximitySensor;
    Sensor mBrightnessSensor;

    @BindView(R.id.ImageView_campOnSmall)
    ImageView campOnSmall;
    @BindView(R.id.ImageView_campOnMedium)
    ImageView campOnMedium;
    @BindView(R.id.ImageView_campOnLarge)
    ImageView campOnLarge;
    @BindView(R.id.ImageView_campOnMax)
    ImageView campOnMax;
    @BindView(R.id.imageView_campOffSmall)
    ImageView campOffSmall;
    @BindView(R.id.ImageView_campOffmedium)
    ImageView campOffMedium;
    @BindView(R.id.ImageView_campOfflarge)
    ImageView campOffLarge;
    @BindView(R.id.imageView_campOffMax)
    ImageView campOffMax;
    @BindView(R.id.Layout)
    RelativeLayout layout;

    float proxim;

    //Telefon na którym testowalem ma api 16

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSensorManagerBrightness = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensorManagerProximity = (SensorManager) getSystemService(SENSOR_SERVICE);
        mProximitySensor = mSensorManagerBrightness.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        mBrightnessSensor = mSensorManagerProximity.getDefaultSensor(Sensor.TYPE_LIGHT);


    }

    @Override
    public void onResume() {
        super.onResume();
        mSensorManagerBrightness.registerListener(mSensorEventListenerBrightness, mBrightnessSensor, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManagerProximity.registerListener(mSensorEventListenerProximal, mProximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        mSensorManagerBrightness.unregisterListener(mSensorEventListenerBrightness);
        mSensorManagerProximity.unregisterListener(mSensorEventListenerProximal);

    }


    final SensorEventListener mSensorEventListenerBrightness = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            final float values = sensorEvent.values[0];
            setImage(values);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    final SensorEventListener mSensorEventListenerProximal = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            proxim = sensorEvent.values[0];
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    public void setInvisibleAll() {
        campOnSmall.setVisibility(View.INVISIBLE);
        campOnMedium.setVisibility(View.INVISIBLE);
        campOnLarge.setVisibility(View.INVISIBLE);
        campOnMax.setVisibility(View.INVISIBLE);
        campOffSmall.setVisibility(View.INVISIBLE);
        campOffMedium.setVisibility(View.INVISIBLE);
        campOffLarge.setVisibility(View.INVISIBLE);
        campOffMax.setVisibility(View.INVISIBLE);
    }

    public void setImage(float brightness) {
        layout.setBackgroundColor(getResources().getColor(R.color.brightnessSmall));
        if (brightness <= 40) {
            if (proxim == 9) {
                setInvisibleAll();
                campOnMax.setVisibility(View.VISIBLE);
            } else {
                setInvisibleAll();
                campOffMax.setVisibility(View.VISIBLE);
            }


        }
        if (brightness > 40 && brightness <= 80) {
            layout.setBackgroundColor(getResources().getColor(R.color.brightnessMedium));
            if (proxim == 9) {
                setInvisibleAll();
                campOnLarge.setVisibility(View.VISIBLE);
            } else {
                setInvisibleAll();
                campOffLarge.setVisibility(View.VISIBLE);
            }


        }
        if (brightness > 80 && brightness <= 120) {
            layout.setBackgroundColor(getResources().getColor(R.color.brightnessLarge));
            if (proxim == 9) {
                setInvisibleAll();
                campOnMedium.setVisibility(View.VISIBLE);
            } else {
                setInvisibleAll();
                campOffMedium.setVisibility(View.VISIBLE);
            }

        }
        if (brightness > 120) {
            layout.setBackgroundColor(getResources().getColor(R.color.brightnessMax));
            if (proxim == 9) {
                setInvisibleAll();
                campOnSmall.setVisibility(View.VISIBLE);
            } else {
                setInvisibleAll();
                campOffSmall.setVisibility(View.VISIBLE);
            }

        }
    }

}