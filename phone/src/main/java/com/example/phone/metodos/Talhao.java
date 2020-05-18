package com.example.phone.metodos;

import java.util.ArrayList;
import java.util.List;

public class Talhao {

    public static String id;
    public static String nome;
    public static String ppm;
    public static String cc;
    public static String area;

    public static ArrayList<String> idList = new ArrayList<>();
    public static ArrayList<String> nomeList = new ArrayList<>();


    public ArrayList<String> getIdList() {
        return idList;
    }

    public void setIdList(ArrayList<String> idList) {
        Talhao.idList = idList;
    }

    public ArrayList<String> getNomeList() {
        return nomeList;
    }

    public void setNomeList(ArrayList<String> nomeList, ArrayList<String> Id) {
        this.nomeList = nomeList;
        idList = Id;
    }

    public Talhao() {
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
