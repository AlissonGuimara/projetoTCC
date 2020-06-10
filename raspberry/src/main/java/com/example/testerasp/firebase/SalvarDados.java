package com.example.testerasp.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SalvarDados {

    public DatabaseReference firebaseReferencia = FirebaseDatabase.getInstance().getReference();

    public void  Salvar(int umidade, Double qntd_agua, String status){

        // data/hora atual
        LocalDateTime agora = LocalDateTime.now();

       // formatar a data
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd-MM-uuuu");
        String dataFormatada = formatterData.format(agora);
        // formatar a hora
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH");
        String horaFormatada = formatterHora.format(agora);

        firebaseReferencia.child("sensor").child("id_talhao").child("1").child("data").
                child(dataFormatada).child("hora").child(String.valueOf(horaFormatada)).child("qntd_agua")
                .setValue(qntd_agua);
        firebaseReferencia.child("sensor").child("id_talhao").child("1").child("data").
                child(dataFormatada).child("hora").child(String.valueOf(horaFormatada)).child("status")
                .setValue(status);
        firebaseReferencia.child("sensor").child("id_talhao").child("1").child("data").
                child(dataFormatada).child("hora").child(String.valueOf(horaFormatada)).child(String.valueOf("umidade"))
                .setValue(umidade);

    }
}
