package com.example.phone.metodos;

public class Planta {

    public static String nome;
    public static String id_talhao;
    public static Integer profundidade_raiz;

    public Planta() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        Planta.nome = nome;
    }

    public String getId_talhao() {
        return id_talhao;
    }

    public void setId_talhao(String id_talhao) {
        Planta.id_talhao = id_talhao;
    }

    public Integer getProfundidade_raiz() {
        return profundidade_raiz;
    }

    public void setProfundidade_raiz(Integer profundidade_raiz) {
        Planta.profundidade_raiz = profundidade_raiz;
    }
}
