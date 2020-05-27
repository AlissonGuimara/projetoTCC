package com.example.phone.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.phone.R;
import com.example.phone.firebase.LerDados;
import com.example.phone.firebase.SalvarDadosPhone;
import com.example.phone.metodos.Planta;
import com.example.phone.metodos.Talhao;

public class PlantaActivity extends Activity {

    private Spinner spinnerTalhao;
    private EditText nomePlanta;
    private EditText profundidade;
    private Button cadastrarPlanta;

    LerDados lerDados = new LerDados();
    Talhao talhao = new Talhao();
    SalvarDadosPhone salvarDadosPhone = new SalvarDadosPhone();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planta);

        lerDados.lerNomeTalhao();
        spinnerTalhao = findViewById(R.id.spinner_IdTalhao_planta);
        nomePlanta = findViewById(R.id.text_nome_planta);
        profundidade = findViewById(R.id.text_profundidade);
        cadastrarPlanta = findViewById(R.id.botao_cadastrar_planta);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, talhao.getNomeList());
        spinnerTalhao.setAdapter(adapter);

        CadastrarPlanta();

    }

    public void CadastrarPlanta(){


        cadastrarPlanta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Planta planta  = new Planta();

                if(spinnerTalhao.getSelectedItem()!=null) {
                    String idString = (String) spinnerTalhao.getSelectedItem();
                     String[] id2 = idString.split(" ");
                    if(id2!=null){
                        planta.setId_talhao(id2[0]);
                        planta.setNome(nomePlanta.getText().toString());
                        planta.setProfundidade_raiz(Integer.parseInt(profundidade.getText().toString()));
                        salvarDadosPhone.SalvarPlanta(planta.getId_talhao(), planta.getNome(), planta.getProfundidade_raiz());
                        finish();
                    }
                }
                else {
                    CadastrarPlanta();
                }
            }
        });

    }

}
