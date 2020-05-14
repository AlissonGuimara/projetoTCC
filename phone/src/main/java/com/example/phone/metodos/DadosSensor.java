package com.example.phone.metodos;

public class DadosSensor {

    public static String status;
    public static Integer umidade;
    public static Integer qntd_agua;

    public DadosSensor() {
    }

    public DadosSensor(String status, Integer umidade, Integer qntd_agua) {
        this.status = status;
        this.umidade = umidade;
        this.qntd_agua = qntd_agua;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getUmidade() {
        return umidade;
    }

    public void setUmidade(Integer umidade) {
        this.umidade = umidade;
    }

    public Integer getQntd_agua() {
        return qntd_agua;
    }

    public void setQntd_agua(Integer qntd_agua) {
        this.qntd_agua = qntd_agua;
    }
}
