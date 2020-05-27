package com.example.phone.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SalvarDadosPhone {

    public DatabaseReference firebaseReferencia = FirebaseDatabase.getInstance().getReference();

    public void SalvarTalhao(String area, String cc, String id, String nome, String ppm , String densidade){

        firebaseReferencia.child("talhao").child("id").child(id).child("area").setValue(area);
        firebaseReferencia.child("talhao").child("id").child(id).child("cc").setValue(cc);
        firebaseReferencia.child("talhao").child("id").child(id).child("nome").setValue(nome);
        firebaseReferencia.child("talhao").child("id").child(id).child("ppm").setValue(ppm);
        firebaseReferencia.child("talhao").child("id").child(id).child("densidade").setValue(densidade);

    }

    public void SalvarPlanta(String id, String nome, Integer profundidade){

        String Id = id;
        String Nome = nome;
        Integer Profundidade = profundidade;

        firebaseReferencia.child("planta").child("id_talhao").child(Id).child("nome").setValue(nome);
        firebaseReferencia.child("planta").child("id_talhao").child(Id).child("profundidade_raiz").setValue(profundidade);

    }

}
