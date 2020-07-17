package com.example.testerasp.irrigar;

import android.util.Log;

import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.PeripheralManager;

import java.io.IOException;

public class Irrigacao {

    //private String pinBomba = "BCM19";
    private Double VazaoPM = 2.0;
    private Double AreaM2 = 0.5; // EM SITUAÇÃO REAL, AQUI DEVE SER PEGO A ÁREA SALVA NO BANCO DE DADOS
    private long tempo;

    public PeripheralManager manager = PeripheralManager.getInstance();

    private Gpio gpioBomba;

    public void LigarBomba(Double qntd_agua) throws IOException {

        tempo = (long) (((qntd_agua * AreaM2) / VazaoPM) * 60000);
        Log.e("tempo", "tempo: " + tempo);

        manager = PeripheralManager.getInstance();
        gpioBomba = manager.openGpio("BCM19");
        gpioBomba.setDirection(Gpio.DIRECTION_OUT_INITIALLY_HIGH);
        gpioBomba.setActiveType(Gpio.ACTIVE_HIGH);

        Thread myThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    gpioBomba.setValue(false);
                    Log.e("bomba", "a bomba esta ligada");
                    Thread.sleep(tempo);
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
                try {
                    gpioBomba.setValue(true);
                    Log.e("bomba", "a bomba esta desligada");
                    close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        myThread.start();
    }

    public void close() {
        try {
            if (gpioBomba != null) gpioBomba.close();
        } catch (IOException e) {
            Log.e("close", "error on closing GPIO");
        } finally {
            gpioBomba = null;
        }
    }

}
