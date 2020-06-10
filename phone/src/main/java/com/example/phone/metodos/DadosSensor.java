package com.example.phone.metodos;

import java.util.ArrayList;

public class DadosSensor {

    public static String status;
    public static Integer umidade;
    public static Double qntd_agua;
    public static ArrayList<String> dataList = new ArrayList<>();
    public static ArrayList<Double> qntdAguaList = new ArrayList<>();

    public DadosSensor() {
    }

    public DadosSensor(String status, Integer umidade, Double qntd_agua) {
        this.status = status;
        this.umidade = umidade;
        this.qntd_agua = qntd_agua;
    }

    public ArrayList<String> getDataList() {
        return dataList;
    }

    public void setDataList(ArrayList<String> dataList) {
        this.dataList = dataList;
    }

    public ArrayList<Double> getQntdAguaList() {
        return qntdAguaList;
    }

    public void setQntdAguaList(ArrayList<Double> qntdAguaList) {
        this.qntdAguaList = qntdAguaList;
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

    public Double getQntd_agua() {
        return qntd_agua;
    }

    public void setQntd_agua(Double qntd_agua) {
        this.qntd_agua = qntd_agua;
    }
}
