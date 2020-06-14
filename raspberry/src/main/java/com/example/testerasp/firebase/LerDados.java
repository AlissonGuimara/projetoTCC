package com.example.testerasp.firebase;

import androidx.annotation.NonNull;

import com.example.testerasp.irrigar.IrrigarDados;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LerDados {

    IrrigarDados irrigar = new IrrigarDados();

    public void lerDadosIrrigacao(int id){

        DatabaseReference firebaseReferencia = FirebaseDatabase.getInstance().getReference();
        DatabaseReference plantaReferencia = firebaseReferencia.child("planta");
        DatabaseReference talhaoReferencia = firebaseReferencia.child("talhao");
        DatabaseReference profundidadeRaiz = plantaReferencia.child("id_talhao").child(String.valueOf(id)).child("profundidade_raiz");
        DatabaseReference cc = talhaoReferencia.child("id").child(String.valueOf(id)).child("cc");
        DatabaseReference ppm = talhaoReferencia.child("id").child(String.valueOf(id)).child("ppm");
        DatabaseReference densidade = talhaoReferencia.child("id").child(String.valueOf(id)).child("densidade");

        //pegar valor da profundidade da raiz
        profundidadeRaiz.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                irrigar.setProfundidade_raiz(Integer.parseInt(dataSnapshot.getValue().toString()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //pegar o valor do cc
        cc.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                irrigar.setCc(Integer.parseInt(dataSnapshot.getValue().toString()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //pegar o valor do ppm
        ppm.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                irrigar.setPpm(Integer.parseInt(dataSnapshot.getValue().toString()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //pegar o valor da densidade
        densidade.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                irrigar.setDensidade(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
