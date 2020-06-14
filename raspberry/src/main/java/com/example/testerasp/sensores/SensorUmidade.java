package com.example.testerasp.sensores;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.io.IOException;

//esta e a classe que le a umidade so sensor, atraves do uso do
//conversor de sinal A/D
public class SensorUmidade extends Activity {

    private Adc0832 mAdc0832 = new Adc0832();
    private Handler mHandler;
    private int umidade;

    public SensorUmidade() throws IOException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        leituraUmidade();
    }

    public void leituraUmidade(){

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
        try {
            final int a = mAdc0832.getADCChannelValue(Adc0832.CHANNEL_0);
            Log.e("Sensor: ", a + " - ");
            setUmidade(a);
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    //AQUI PODE SER IMPLEMENTADO O METODO DE SALVAR
                }
            });
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //esta linha faz o sensor ficar em loop lendo a umidade
        //readAnalogData();
    }

    public int getUmidade() {

        Double umid = (-0.3185 * umidade) + 57.778;
        return umid.intValue();
    }

    public void setUmidade(int umidade) {
        this.umidade = umidade;
    }

}
