package com.example.phone.metodos;

import java.util.ArrayList;

public class Talhao {

    public static String id;
    public static String nome;
    public static String ppm;
    public static String cc;
    public static String area;
    public static String densidade;

    public static ArrayList<String> nomeList = new ArrayList<>();


    public ArrayList<String> getNomeList() {
        return nomeList;
    }

    public void setNomeList(ArrayList<String> nomeList) {
        this.nomeList = nomeList;
    }

    public Talhao() {
    }

    public String getDensidade() {
        return densidade;
    }

    public void setDensidade(String densidade) {
        Talhao.densidade = densidade;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPpm() {
        return ppm;
    }

    public void setPpm(String ppm) {
        this.ppm = ppm;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
