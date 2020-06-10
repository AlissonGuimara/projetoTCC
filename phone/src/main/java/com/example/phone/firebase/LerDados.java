package com.example.phone.firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.phone.metodos.DadosSensor;
import com.example.phone.metodos.Planta;
import com.example.phone.metodos.Talhao;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LerDados{

    DadosSensor dadosSensor = new DadosSensor();
    Talhao talhao = new Talhao();
    Planta planta = new Planta();
    ArrayList<String> nome = new ArrayList<>();
    ArrayList<String> datas = new ArrayList<>();
    ArrayList<Double> somas = new ArrayList<>();

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
                //Log.e("firebase", dataSnapshot.getValue().toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //referencia a quantidade de agua
        qntdAguaReferencia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dadosSensor.setQntd_agua(Double.parseDouble(dataSnapshot.getValue().toString()));
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

        DatabaseReference firebaseReferencia = FirebaseDatabase.getInstance().getReference();
        DatabaseReference nomeReferencia = firebaseReferencia.child("talhao").child("id");

        nomeReferencia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot s : dataSnapshot.getChildren()){
                        nome.add(s.getKey() + " " + s.child("nome").getValue().toString());
                        talhao.setNomeList(nome);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void lerNomePlanta(String id){
        DatabaseReference firebaseReferencia = FirebaseDatabase.getInstance().getReference();
        DatabaseReference nomePlantaReferencia = firebaseReferencia.child("planta").child("id_talhao").child(id).child("nome");
        DatabaseReference profundidadeReferencia = firebaseReferencia.child("planta").child("id_talhao").child(id).child("profundidade_raiz");
        nomePlantaReferencia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                        planta.setNome(dataSnapshot.getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        profundidadeReferencia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    planta.setProfundidade_raiz(Integer.parseInt(dataSnapshot.getValue().toString()));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void lerGastoAgua(String id, final String data){

        DatabaseReference firebaseReferencia = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference dataReferencia = firebaseReferencia.child("sensor").child("id_talhao").child("1").child("data")
                .child(data).child("hora");

        dataReferencia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Double soma = 0.0;
                if(dataSnapshot.exists()){
                    for(DataSnapshot s: dataSnapshot.getChildren()){
                        soma = soma + Double.parseDouble(s.child("qntd_agua").getValue().toString());
                }
                    datas.add(data);
                    somas.add(soma);

                    //le a data e a qntd de agua, para depois ler no relatorio//
                    dadosSensor.setDataList(datas);
                    dadosSensor.setQntdAguaList(somas);
                    //Log.e("quantidade agua", data + "--" + soma);
                    //Log.e("quantidade agua", String.valueOf(datas) + "--"+ somas);
            }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}

