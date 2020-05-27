package com.example.testerasp.apiAdvisor;

public class RespostaServidor {

    private String name;
    private String state;

    //List<RespostaServidor> list = new ArrayList<>();

    //public RespostaServidor(){}

    public RespostaServidor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
