package com.example.phone.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.phone.R;
import com.example.phone.firebase.LerDados;
import com.example.phone.metodos.DadosSensor;
import com.example.phone.metodos.Talhao;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class RelatorioActivity extends Activity {

    private EditText data;
    private EditText hora;
    private TextView status;
    private TextView agua;
    private Button relatorioTalhao;
    private TextView umidadeTermometro;
    private ImageView termometro;
    private Spinner spinnerTalhao;
    private TextView textoNome;
    private TextView textoUmidade;
    private TextView textoCc;
    private TextView textoPpm;
    private TextView textoStatus;
    private TextView area;

    DadosSensor dadosSensor = new DadosSensor();
    LerDados lerDados = new LerDados();
    Talhao talhao = new Talhao();
    GregorianCalendar gc = new GregorianCalendar();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorios);

        lerDados.lerNomeTalhao();
        spinnerTalhao = findViewById(R.id.spinner_IdTalhao);
        relatorioTalhao = findViewById(R.id.botao_relatorio_talhao);
        umidadeTermometro = findViewById(R.id.texto_umidade_termometro);
        termometro = findViewById(R.id.view_termometro);
        textoNome = findViewById(R.id.text_nome);
        textoUmidade = findViewById(R.id.text_umidade);
        textoCc = findViewById(R.id.text_cc);
        textoPpm = findViewById(R.id.text_ppm);
        textoStatus = findViewById(R.id.text_status);
        area = findViewById(R.id.text_area);

        //insere no view a data e a hora atual
        String horaAtual = "17"; //gc.get(Calendar.HOUR_OF_DAY);
        int dia = gc.get(Calendar.DAY_OF_MONTH);
        int mes = gc.get(Calendar.MONTH);
        int ano = gc.get(Calendar.YEAR);
        String dataAtual = "1-4-2020"; //dia + "-" + mes +"-"+ ano;
        data = findViewById(R.id.text_data_talhao);
        data.setText(dataAtual);
        hora = findViewById(R.id.text_hora_talhao);
        hora.setText(horaAtual);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, talhao.getNomeList());
        spinnerTalhao.setAdapter(adapter);

            Relatorio();

    }

    //implementa todos os relatorios da view
    public void Relatorio() {

        //verifica se o item selecionado não é nulo
        if(spinnerTalhao.getSelectedItem()!=null){
            String idString = (String) spinnerTalhao.getSelectedItem();
            String[] id2 = idString.split(" ");
            lerDados.ler(data.getText().toString(), hora.getText().toString(), id2[0]);
        }

        relatorioTalhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //chama o relatório novamente, pois pode ser inserido outra data e/ou hora

                umidadeTermometro.setText("Umidade: " + dadosSensor.getUmidade());
                textoNome.setText(talhao.getNome());
                textoUmidade.setText(dadosSensor.getUmidade().toString());
                textoCc.setText(talhao.getCc());
                textoPpm.setText(talhao.getPpm());
                area.setText(talhao.getArea());
                textoStatus.setText(dadosSensor.getStatus());
                int umidade = dadosSensor.getUmidade();
                int cc = Integer.parseInt(talhao.getCc());
                int ppm = Integer.parseInt(talhao.getPpm());

                if(umidade+3>=cc){
                    termometro.setImageResource(R.drawable.umidade_alta);
                }else if(umidade-3>ppm){
                    termometro.setImageResource(R.drawable.umidade_boa);
                }else{
                    termometro.setImageResource(R.drawable.umidade_baixa);
                }

                Relatorio();
            }
        });

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
