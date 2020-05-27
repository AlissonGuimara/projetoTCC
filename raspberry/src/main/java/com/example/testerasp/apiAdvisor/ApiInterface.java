package com.example.testerasp.apiAdvisor;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    String BASE_URL = "http://apiadvisor.climatempo.com.br/api/v1/forecast/locale/3477/hours/";
    //List<RespostaServidor> list = new ArrayList<>();
    @GET("72?token=643c73fff94c5b2fe221c5ede99cbeb4")
    Call<classeData> lista();

}
