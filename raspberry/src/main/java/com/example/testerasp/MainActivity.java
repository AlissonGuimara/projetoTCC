package com.example.testerasp;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.testerasp.apiAdvisor.LerPrevisao;
import com.example.testerasp.sensores.Adc0832;

import java.io.IOException;

public class MainActivity extends Activity {

    private Adc0832 mAdc0832 = new Adc0832();
    private Handler mHandler;

    public MainActivity() throws IOException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LerPrevisao lerPrevisao = new LerPrevisao();
        lerPrevisao.previsao();

        //set up mAdc0832
        try {
            mAdc0832 = new Adc0832(Adc0832.DEFAULT_PI_PIN_CLK, Adc0832.DEFAULT_PI_PIN_D0,
                    Adc0832.DEFAULT_PI_PIN_D1, Adc0832.DEFAULT_PI_PIN_CS);
        } catch (IOException e) {
            e.printStackTrace();
        }
        readAnalogData();
        mHandler = new Handler();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdc0832.close();
    }

    private void readAnalogData() {
        Thread myThread = new Thread(new Runnable() {
            @Override
            public void run() {
                readValueAndLog();
            }
        });
        myThread.start();
    }

    private void readValueAndLog() {
        //for (int i = 0; i < 10; i++) {
            try {
                final int a = mAdc0832.getADCChannelValue(Adc0832.CHANNEL_0);
                //final int b = mAdc0832.getADCChannelValue(Adc0832.CHANNEL_1);
                Log.d("Sensor: ", a + " - ");
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //valueTextView.setText(a + " - " + b);
                    }
                });
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            readAnalogData();
        //}
    }

}
