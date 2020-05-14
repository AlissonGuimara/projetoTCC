package com.example.testerasp.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class salvarDados{

    public DatabaseReference firebaseReferencia = FirebaseDatabase.getInstance().getReference();
    GregorianCalendar gc = new GregorianCalendar();

    public void  Salvar(){
        int hora = gc.get(Calendar.HOUR_OF_DAY);
        int dia = gc.get(Calendar.DAY_OF_MONTH);
        int mes = gc.get(Calendar.MONTH);
        int ano = gc.get(Calendar.YEAR);
        String data = dia + "-" + mes +"-"+ ano;

        firebaseReferencia.child("sensor").child("id_talhao").child("2").child("data").
                child(data).child("hora").child(String.valueOf(hora)).child("qntd_agua")
                .setValue(0);
        firebaseReferencia.child("sensor").child("id_talhao").child("2").child("data").
                child(data).child("hora").child(String.valueOf(hora)).child("status")
                .setValue("desligada");
        firebaseReferencia.child("sensor").child("id_talhao").child("2").child("data").
                child(data).child("hora").child(String.valueOf(hora)).child("umidade")
                .setValue(10);

    }
}
