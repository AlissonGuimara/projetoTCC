package com.example.testerasp.apiAdvisor;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.testerasp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LerPrevisao extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        previsao();
    }

    public void previsao() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiInterface.BASE_URL).addConverterFactory(GsonConverterFactory.
                create()).build();

        ApiInterface service = retrofit.create(ApiInterface.class);

        Call<classeData> requestResposta = service.lista();

        requestResposta.enqueue(new Callback<classeData>() {
            @Override
            public void onResponse(Call<classeData> call, Response<classeData> response) {
                //Datas d = new Datas();

                if (!response.isSuccessful()) {
                    Log.e("ERRO", "erro" + response.code());
                } else {
                    classeData resp = response.body();
                    for (Datas d : resp.data) {
                        Log.e("CERTO", "data: " + d.date);
                        Log.e("CERTO", "chuva: " + d.getRain().getPrecipitation());
                        Log.e("CERTO", "CERTO" + "-----------");

                        //for (Chuva  c: d.) {
                        //Log.e("CERTO", "CERTO" + c.precipitation);
                        //}
                    }

                }
            }

            @Override
            public void onFailure(Call<classeData> call, Throwable t) {
                Log.e("ERRO", "erro" + t.getMessage() + " aaaaa " + t.getStackTrace());
            }
        });
    }
}

