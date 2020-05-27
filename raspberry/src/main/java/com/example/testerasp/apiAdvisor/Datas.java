package com.example.testerasp.apiAdvisor;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datas {

    @SerializedName("date")
    @Expose
    public String date;

    @SerializedName("rain")
    @Expose
    public Chuva rain;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Datas(Chuva rain) {
        this.rain = rain;
    }

    public Chuva getRain() {
        return rain;
    }

    public void setRain(Chuva rain) {
        this.rain = rain;
    }
}
