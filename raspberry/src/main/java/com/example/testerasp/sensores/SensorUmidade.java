package com.example.testerasp.sensores;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.google.android.things.userdriver.UserDriverManager;
import com.google.android.things.userdriver.sensor.UserSensor;
import com.google.android.things.userdriver.sensor.UserSensorDriver;
import com.google.android.things.userdriver.sensor.UserSensorReading;

import java.io.IOException;

import static android.hardware.Sensor.TYPE_DEVICE_PRIVATE_BASE;

public class SensorUmidade{

    public UserSensorDriver driver = new UserSensorDriver() {
        @Override
        public UserSensorReading read() throws IOException {
            float valor;
            driver.setEnabled(true);
            try {
                // ...read the sensor hardware...
                valor = umidade.getResolution();
                // Return a new reading
                return new UserSensorReading(new float[] {valor});
            } catch (Exception e) {
                // Error occurred reading the sensor hardware
                throw new IOException("Unable to read sensor");
            }
        }
    };

    UserSensor umidade = new UserSensor.Builder()
            .setName("umidade")
            .setCustomType(TYPE_DEVICE_PRIVATE_BASE,
                    "com.example.umidade",
                    Sensor.REPORTING_MODE_CONTINUOUS)
            .setDriver(driver)
            .build();

    /*public class SensorDriverService extends Service {

        @Override
        public void onCreate() {
            super.onCreate();

            UserDriverManager manager = UserDriverManager.getInstance();

            // Create a new driver implementation
            // Register the new driver with the framework
            manager.registerSensor(umidade);
        }

        @Override
        public void onDestroy() {
            super.onDestroy();

            UserDriverManager manager = UserDriverManager.getInstance();
            // Unregister the driver when finished
            manager.unregisterSensor(umidade);
        }

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

    }*/
}
