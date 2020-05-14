package com.example.testerasp.sensores;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.google.android.things.userdriver.UserDriverManager;

public class SensorDriverService extends Service {

    SensorUmidade sensor = new SensorUmidade();

    @Override
    public void onCreate() {
        super.onCreate();

        UserDriverManager manager = UserDriverManager.getInstance();

        // Create a new driver implementation

        // Register the new driver with the framework
        manager.registerSensor(sensor.umidade);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        UserDriverManager manager = UserDriverManager.getInstance();
        // Unregister the driver when finished
        manager.unregisterSensor(sensor.umidade);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}

