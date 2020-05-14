package com.example.phone.firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.phone.metodos.DadosApp;
import com.example.phone.metodos.DadosSensor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LerDados{

    DadosApp dadosApp = new DadosApp();
    DadosSensor dadosSensor = new DadosSensor();

    //cria as referencias ao firebase
    public void ler(String data, String hora) {

        String Data = data;
        String Hora = hora;

         DatabaseReference firebaseReferencia = FirebaseDatabase.getInstance().getReference();
         DatabaseReference dataReferencia = firebaseReferencia.child("sensor").child("id_talhao").child("1").child("data");

         DatabaseReference qntdAguaReferencia = firebaseReferencia.child("sensor").child("id_talhao").child("1").
                 child("data").child(Data).child("hora").child(Hora).child("qntd_agua");

         DatabaseReference umidadeReferencia = firebaseReferencia.child("sensor").child("id_talhao").child("1").
                 child("data").child(Data).child("hora").child(Hora).child("umidade");

         DatabaseReference statusReferencia = firebaseReferencia.child("sensor").child("id_talhao").child("1").
                 child("data").child(Data).child("hora").child(Hora).child("status");

         //referencia a data
        dataReferencia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e("firebase", dataSnapshot.getValue().toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //referencia a quantidade de agua
        qntdAguaReferencia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                dadosSensor.setQntd_agua(Integer.parseInt(dataSnapshot.getValue().toString()));
                Log.e("agua", "---" + dadosSensor.getQntd_agua());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //referencia a umidade
        umidadeReferencia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dadosSensor.setUmidade(Integer.parseInt(dataSnapshot.getValue().toString()));
                Log.e("umidade", "---" + dadosSensor.getUmidade());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //referencia ao status
        statusReferencia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dadosSensor.setStatus(dataSnapshot.getValue().toString());
                Log.e("status", "---" + dadosSensor.getStatus());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}

