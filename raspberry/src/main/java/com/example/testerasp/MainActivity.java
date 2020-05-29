package com.example.testerasp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.testerasp.apiAdvisor.LerPrevisao;
import com.example.testerasp.sensores.SensorUmidade;

import java.io.IOException;

public class MainActivity extends Activity {

    LerPrevisao lerPrevisao = new LerPrevisao();
    private SensorUmidade sensorUmidade = new SensorUmidade();

    public MainActivity() throws IOException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sensorUmidade.leituraUmidade();
        readPrevisao();
    }

    //método que tem o retorno da previsão para 3 dias
    // considerando somente a precipitação
    private void readPrevisao() {
        Thread myThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Double precipitacao = lerPrevisao.previsao();
                if (precipitacao == null) {
                    readPrevisao();
                } else {
                    Log.e("previsao", String.valueOf(precipitacao));
                }
            }
        });
        myThread.start();
    }

}