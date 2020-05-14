package com.example.phone.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SalvarDadosPhone {

    public DatabaseReference firebaseReferencia = FirebaseDatabase.getInstance().getReference();

    public void Salvar(String area, String cc, String id, String nome, String ppm ){

        String Area = area;
        String Cc = cc;
        String Id = id;
        String Nome = nome;
        String Ppm = ppm;

        firebaseReferencia.child("talhao").child("id").child(Id).child("area").setValue(Area);
        firebaseReferencia.child("talhao").child("id").child(Id).child("cc").setValue(Cc);
        firebaseReferencia.child("talhao").child("id").child(Id).child("nome").setValue(Nome);
        firebaseReferencia.child("talhao").child("id").child(Id).child("ppm").setValue(Ppm);

    }

}
