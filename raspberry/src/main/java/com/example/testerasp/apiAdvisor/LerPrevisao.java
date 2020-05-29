package com.example.testerasp.apiAdvisor;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LerPrevisao {

    private Double precipitacao = null;

    // método que le a previsão do tempo para 3 dias
        public Double  previsao() {

                final Retrofit[] retrofit = {new Retrofit.Builder().baseUrl(ApiInterface.BASE_URL).addConverterFactory(GsonConverterFactory.
                        create()).build()};

                ApiInterface service = retrofit[0].create(ApiInterface.class);
                Call<classeData> requestResposta = service.lista();

                requestResposta.enqueue(new Callback<classeData>() {
                    @Override
                    public void onResponse(Call<classeData> call, Response<classeData> response) {
                        Double soma = 0.0;

                        if (!response.isSuccessful()) {
                            Log.e("ERRO", "erro" + response.code());
                        } else {
                            classeData resp = response.body();

                            for (Datas d : resp.data) {
                                soma += Double.parseDouble(d.getRain().getPrecipitation().toString());
                                Log.e("CERTO", "data: " + d.date);
                                Log.e("CERTO", "chuva: " + d.getRain().getPrecipitation());
                                Log.e("CERTO", "CERTO" + "-----------");
                            }
                            precipitacao = soma;
                            Log.e("CERTO", "soma1" + "-----------" + precipitacao);
                        }
                    }
                    @Override
                    public void onFailure(Call<classeData> call, Throwable t) {
                        Log.e("ERRO", "erro" + t.getMessage() + " aaaaa " + t.getStackTrace());
                    }
                });
                return precipitacao;
        }
    }

