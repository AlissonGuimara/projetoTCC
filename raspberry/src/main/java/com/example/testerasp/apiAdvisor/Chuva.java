package com.example.testerasp.apiAdvisor;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Chuva {

    @SerializedName("precipitation")
    @Expose
    public  Double  precipitation;

    public Chuva(Double  precipitation) {
        this.precipitation = precipitation;
    }

    public Double  getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(Double  precipitation) {
        this.precipitation = precipitation;
    }
}
