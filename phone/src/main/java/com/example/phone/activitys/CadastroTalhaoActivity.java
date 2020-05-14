package com.example.phone.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.phone.R;
import com.example.phone.firebase.SalvarDadosPhone;
import com.example.phone.metodos.Talhao;

public class CadastroTalhaoActivity extends Activity {

    private EditText id;
    private EditText nome;
    private EditText area;
    private EditText ppm;
    private EditText cc;
    private Button cadastrar;
    private Button voltar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talhao);
        Cadastrar();
    }

    public void Cadastrar(){

        id = findViewById(R.id.texto_idCadastrar);
        nome = findViewById(R.id.texto_nomeCadastrar);
        area = findViewById(R.id.texto_areaCadastrar);
        ppm = findViewById(R.id.texto_ppmCadastrar);
        cc = findViewById(R.id.texto_ccCadastrar);
        cadastrar = findViewById(R.id.botao_cadastrar);
        voltar = findViewById(R.id.botao_voltar);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Talhao talhao = new Talhao();
                SalvarDadosPhone salvarDadosPhone = new SalvarDadosPhone();

                talhao.setArea(area.getText().toString());
                talhao.setCc(cc.getText().toString());
                talhao.setId(id.getText().toString());
                talhao.setNome(nome.getText().toString());
                talhao.setPpm(ppm.getText().toString());
                salvarDadosPhone.Salvar(talhao.getArea(), talhao.getCc(), talhao.getId(), talhao.getNome(),
                talhao.getPpm());

                Intent intent = new Intent(CadastroTalhaoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }

}
