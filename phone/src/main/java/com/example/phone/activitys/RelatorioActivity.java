package com.example.phone.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.phone.R;
import com.example.phone.firebase.LerDados;
import com.example.phone.metodos.DadosApp;
import com.example.phone.metodos.DadosSensor;

public class RelatorioActivity extends Activity {

    private EditText data;
    private EditText hora;
    private TextView status;
    private TextView agua;
    private Button gerarRelatorio;
    private TextView umidadeTermometro;
    private ImageView termometro;
    private Spinner spinnerTalhao;
    private TextView textoNome;
    private TextView textoUmidade;
    private TextView textoCc;
    private TextView textoPpm;
    private TextView textoStatus;

    DadosApp dadosApp = new DadosApp();
    DadosSensor dadosSensor = new DadosSensor();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorios);
        Relatorio();
    }

    //implementa todos os relatorios da view
    public void Relatorio() {

        umidadeTermometro = findViewById(R.id.texto_umidade_termometro);
        termometro = findViewById(R.id.view_termometro);
        spinnerTalhao = findViewById(R.id.spinner_IdTalhao);
        textoNome = findViewById(R.id.text_nome);
        textoUmidade = findViewById(R.id.text_umidade);
        textoCc = findViewById(R.id.text_cc);
        textoPpm = findViewById(R.id.text_ppm);
        textoStatus = findViewById(R.id.text_status);

        //implemetar o spinner;

        /*
        data = findViewById(R.id.texto_data);
        hora = findViewById(R.id.texto_hora);
        status = findViewById(R.id.texto_status);
        agua = findViewById(R.id.texto_agua);
        gerarRelatorio = findViewById(R.id.botao_relatorio);

        //evento de click do botão
        gerarRelatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LerDados lerDados = new LerDados();

                dadosApp.setData(data.getText().toString());
                dadosApp.setHora(hora.getText().toString());
                lerDados.ler(dadosApp.getData(), dadosApp.getHora());

                if (dadosSensor.getStatus() != null) {
                    if (dadosSensor.getQntd_agua() != null) {
                        agua.setText(dadosSensor.getQntd_agua().toString());
                        status.setText(dadosSensor.getStatus());
                    }
                }

                Log.e("teste", "dados" + dadosSensor.getQntd_agua() + "------" + dadosSensor.getStatus());

            }
        });*/
    }

}
