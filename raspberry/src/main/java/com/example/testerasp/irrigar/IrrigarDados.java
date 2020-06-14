package com.example.testerasp.irrigar;

public class IrrigarDados {

    public static int profundidade_raiz;
    public static int cc;
    public static int ppm;
    public static String densidade;

    public IrrigarDados() {
    }

    public int getProfundidade_raiz() {
        return profundidade_raiz;
    }

    public void setProfundidade_raiz(int profundidade_raiz) {
        IrrigarDados.profundidade_raiz = profundidade_raiz;
    }

    public int getCc() {
        return cc;
    }

    public void setCc(int cc) {
        IrrigarDados.cc = cc;
    }

    public int getPpm() {
        return ppm;
    }

    public void setPpm(int ppm) {
        IrrigarDados.ppm = ppm;
    }

    public String getDensidade() {
        return densidade;
    }

    public void setDensidade(String densidade) {
        IrrigarDados.densidade = densidade;
    }
}
