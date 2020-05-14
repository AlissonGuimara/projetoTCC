package com.example.testerasp;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

import com.example.testerasp.firebase.salvarDados;
import com.example.testerasp.sensores.SensorUmidade;
import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.GpioCallback;
import com.google.android.things.pio.PeripheralManager;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;


/**
 * Skeleton of an Android Things activity.
 * <p>
 * Android Things peripheral APIs are accessible through the PeripheralManager
 * For example, the snippet below will open a GPIO pin and set it to HIGH:
 * <p>
 * PeripheralManager manager = PeripheralManager.getInstance();
 * try {
 * Gpio gpio = manager.openGpio("BCM6");
 * gpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
 * gpio.setValue(true);
 * } catch (IOException e) {
 * Log.e(TAG, "Unable to access GPIO");
 * }
 * <p>
 * You can find additional examples on GitHub: https://github.com/androidthings
 */
public class MainActivity extends Activity /*implements SensorEventListener*/ {

    //private SensorManager sensorManager;
    //private SensorCallback callback = new SensorCallback();
    //private DatabaseReference firebaseReferencia = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        salvarDados salvar = new salvarDados();
        salvar.Salvar();

        /*
        PeripheralManager service =  PeripheralManager.getInstance();
       // UserDriverManager manager = UserDriverManager.getInstance();
        SensorUmidade sensor = new SensorUmidade();
        //manager.registerSensor(sensor.umidade);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorManager.registerDynamicSensorCallback(callback);


        try {
            Gpio gpio = service.openGpio("BCM6");

            gpio.setDirection(Gpio.DIRECTION_IN);
            gpio.setActiveType(Gpio.ACTIVE_HIGH);
            gpio.setEdgeTriggerType(Gpio.EDGE_NONE);
            gpio.registerGpioCallback(gpioCallback);


        } catch (IOException e) {
            Log.e("erro GPIO", "Unable to access GPIO");
         }
        }


    private GpioCallback gpioCallback = new GpioCallback() {

        @Override
        public boolean onGpioEdge(Gpio gpio) {
            // Read the active low pin state
            SensorUmidade sensor = new SensorUmidade();

            try {
                if (gpio.getValue()) {                    // Pin is HIGH
                    Log.e("GPIO: ", gpio.getName() + gpio.getValue());
                    Log.e("SENSOR", "TESTE SENSOR" + sensor.driver.read());

                } else {
                    // Pin is LOW
                    Log.e("ERRO", "pin low");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Continue listening for more interrupts
            return true;
        }

        @Override
        public void onGpioError(Gpio gpio, int error) {
            Log.w("TAG", gpio + ": Error event " + error);
        }
    };



    @Override
    protected void onDestroy() {
        super.onDestroy();
        sensorManager.unregisterDynamicSensorCallback(callback);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        System.out.println("teste");

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    // Listen for registration events from the sensor driver
    private class SensorCallback extends SensorManager.DynamicSensorCallback {
        @Override
        public void onDynamicSensorConnected(Sensor sensor) {
            // Begin listening for sensor readings
            sensorManager.registerListener(MainActivity.this, sensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }

        @Override
        public void onDynamicSensorDisconnected(Sensor sensor) {
            // Stop receiving sensor readings
            sensorManager.unregisterListener(MainActivity.this);
        }*/
    }
}

