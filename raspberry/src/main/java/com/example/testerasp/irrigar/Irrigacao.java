package com.example.testerasp.irrigar;

import android.util.Log;

import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.PeripheralManager;

import java.io.IOException;

public class Irrigacao {

    private static String pinBomba = "BCM19";
    private Double VazaoPM = 2.0;
    private Double AreaM2 = 0.5;
    private long tempo;

    public PeripheralManager manager = PeripheralManager.getInstance();

    private Gpio gpioBomba;

    public void LigarBomba(Double qntd_agua) throws IOException {

        tempo = (long) (((qntd_agua * AreaM2) / VazaoPM) * 60000);
        Log.e("tempo", "tempo: " + tempo);

        manager = PeripheralManager.getInstance();
        gpioBomba = manager.openGpio(pinBomba);
        gpioBomba.setDirection(Gpio.DIRECTION_OUT_INITIALLY_HIGH);
        gpioBomba.setActiveType(Gpio.ACTIVE_HIGH);

        Thread myThread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    gpioBomba.setActiveType(Gpio.ACTIVE_LOW);
                    Thread.sleep(tempo);
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }
        });
        myThread.start();
        gpioBomba.setActiveType(Gpio.ACTIVE_HIGH);
    }

}
