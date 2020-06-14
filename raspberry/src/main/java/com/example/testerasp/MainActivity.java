package com.example.testerasp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.testerasp.apiAdvisor.LerPrevisao;
import com.example.testerasp.firebase.LerDados;
import com.example.testerasp.firebase.SalvarDados;
import com.example.testerasp.irrigar.IrrigarDados;
import com.example.testerasp.sensores.SensorUmidade;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {

    private LerPrevisao lerPrevisao = new LerPrevisao();
    private SensorUmidade sensorUmidade = new SensorUmidade();
    private SalvarDados salvarDados = new SalvarDados();
    private LerDados lerDados = new LerDados();
    private IrrigarDados irrigarDados = new IrrigarDados();
    private Double precipitacao = null;
    private Integer umidade = null;

    public MainActivity() throws IOException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable()
                {
                    public void run()
                    {
                        lerDados.lerDadosIrrigacao(1);
                        SalvarPrevisaoEUmidade();
                        sensorUmidade.leituraUmidade();
                    }
                });
            }

        },0, 6000);

    }

    //ler os dados da previsão do tempo para 3 dias e da umidade do solo
    //passa por parametro para o método irrigar
    public void SalvarPrevisaoEUmidade(){
        umidade = sensorUmidade.getUmidade();
        precipitacao = lerPrevisao.previsao();
        final Thread myThread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (precipitacao == null || umidade == null) {
                    SalvarPrevisaoEUmidade();
                } else {
                   Irrigacao(umidade, precipitacao);
                }
            }

        });
        myThread.start();
    }


    // desenvolver metodo que pega dados para irrigação
    //pega os dados da CC, PPM e densidade cadastrado no talhão, junto com a profundidade da raiz cadastrada na planta
    //é recebido por parametro a umidade e precipitação para 3 dias
    //com estes dados é montado a lógica para irrigar ou não, e é salvo os dados no firebase no nó do sensor
    public void Irrigacao(final Integer umidade, final Double precipitacao ){

                while(irrigarDados.getCc() == 0 || irrigarDados.getPpm() == 0 || irrigarDados.getProfundidade_raiz() == 0
                || irrigarDados.getDensidade() == null){
                    lerDados.lerDadosIrrigacao(1);
                }

                if(umidade > irrigarDados.getPpm() + 3){
                    salvarDados.Salvar(umidade, 0.0, "desligada");
                }

                //aqui tem que desenvolver a lógica para irrigar ou não dependendo da umidade e previsão
                else if(umidade <= irrigarDados.getPpm() + 3){
                    Double diferenca_umidade = Double.parseDouble(String.valueOf(irrigarDados.getCc() - umidade));
                    Double qntd_agua = (diferenca_umidade / 10) * Double.parseDouble(irrigarDados.getDensidade()) *
                            Double.parseDouble(String.valueOf(irrigarDados.getProfundidade_raiz()));
                    Log.e("qntd agua", qntd_agua.toString());

                    if(umidade <= irrigarDados.getPpm() + 1){
                        salvarDados.Salvar(umidade, qntd_agua, "ligada");
                    }
                    else if(precipitacao == 0.0 ){
                        salvarDados.Salvar(umidade, qntd_agua, "ligada");
                    }
                    else if(precipitacao >= qntd_agua){
                        salvarDados.Salvar(umidade, 0.0, "desligada");
                    }
                    else if(precipitacao >= (qntd_agua / 5)){
                        salvarDados.Salvar(umidade, 0.0, "desligada");
                    }
                    else{
                        salvarDados.Salvar(umidade, qntd_agua, "ligada");
                    }

                }

                Log.e("irrigação", "cc :" +irrigarDados.getCc());
                Log.e("irrigação", "ppm: " + irrigarDados.getPpm());
                Log.e("irrigação", "raiz: " + irrigarDados.getProfundidade_raiz());
                Log.e("irrigação", "densidade: " + irrigarDados.getDensidade());
                Log.e("irrigação", "umidade: " + umidade);
                Log.e("irrigação", "precipitacao: " + precipitacao);
                Log.e("irrigação", "--------------------------------------------------------");
            }

    }

