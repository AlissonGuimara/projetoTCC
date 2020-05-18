package com.example.phone.firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.phone.metodos.DadosSensor;
import com.example.phone.metodos.Talhao;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LerDados{

    DadosSensor dadosSensor = new DadosSensor();
    Talhao talhao = new Talhao();
    ArrayList<String> nome = new ArrayList<>();
    ArrayList<String> id = new ArrayList<>();

    //cria as referencias ao firebase
    public void ler(String data, String hora, String id) {

        String Data = data;
        String Hora = hora;
        String Id = id;

         DatabaseReference firebaseReferencia = FirebaseDatabase.getInstance().getReference();
         DatabaseReference dataReferencia = firebaseReferencia.child("sensor").child("id_talhao").child(Id).child("data");
         DatabaseReference talhaoReferenciaId = firebaseReferencia.child("talhao").child("id");
         DatabaseReference qntdAguaReferencia = dataReferencia.child(Data).child("hora").child(Hora).child("qntd_agua");
         DatabaseReference umidadeReferencia = dataReferencia.child(Data).child("hora").child(Hora).child("umidade");
         DatabaseReference statusReferencia = dataReferencia.child(Data).child("hora").child(Hora).child("status");
         DatabaseReference areaReferencia = talhaoReferenciaId.child(Id).child("area");
         DatabaseReference ccReferencia = talhaoReferenciaId.child(Id).child("cc");
         DatabaseReference nomeReferencia = talhaoReferenciaId.child(Id).child("nome");
         DatabaseReference ppmReferencia = talhaoReferenciaId.child(Id).child("ppm");

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

        //referencia a área
        areaReferencia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                talhao.setArea(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //referencia a CC
        ccReferencia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                talhao.setCc(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //referencia ao nome
        nomeReferencia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                talhao.setNome(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //referencia a PPM
        ppmReferencia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                talhao.setPpm(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void lerNomeTalhao(){
        ArrayList<Talhao> ta = new ArrayList<>();
        DatabaseReference firebaseReferencia = FirebaseDatabase.getInstance().getReference();
        DatabaseReference nomeReferencia = firebaseReferencia.child("talhao").child("id");

        nomeReferencia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot s : dataSnapshot.getChildren()){
                        nome.add(s.child("nome").getValue().toString());
                        id.add(s.getKey());
                        talhao.setNomeList(nome, id);


                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}

