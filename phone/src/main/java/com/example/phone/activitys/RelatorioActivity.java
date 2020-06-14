package com.example.phone.activitys;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.phone.R;
import com.example.phone.firebase.LerDados;
import com.example.phone.metodos.DadosSensor;
import com.example.phone.metodos.Planta;
import com.example.phone.metodos.Talhao;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class RelatorioActivity extends Activity {

    private EditText data;
    private EditText hora;
    private Button relatorioTalhao;
    private Button relatorioGastoAgua;
    private TextView umidadeTermometro;
    private ImageView termometro;
    private Spinner spinnerTalhao;
    private Spinner spinnerAgua;
    private TextView textoNome;
    private TextView textoCc;
    private TextView textoPpm;
    private TextView textoStatus;
    private TextView area;
    private TextView nomePlanta;
    private AlertDialog alerta;

    DadosSensor dadosSensor = new DadosSensor();
    LerDados lerDados = new LerDados();
    Talhao talhao = new Talhao();
    Calendar myCalendar = Calendar.getInstance();
    Planta planta = new Planta();

    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorios);
        lerDados.lerNomeTalhao();

        spinnerTalhao = findViewById(R.id.spinner_IdTalhao);
        spinnerAgua = findViewById(R.id.spinner_qntdAgua);
        relatorioTalhao = findViewById(R.id.botao_relatorio_talhao);
        relatorioGastoAgua = findViewById(R.id.botao_relatorio_agua);
        umidadeTermometro = findViewById(R.id.texto_umidade_termometro);
        termometro = findViewById(R.id.view_termometro);
        textoNome = findViewById(R.id.text_nome);
        textoCc = findViewById(R.id.text_cc);
        textoPpm = findViewById(R.id.text_ppm);
        textoStatus = findViewById(R.id.text_status);
        nomePlanta = findViewById(R.id.text_planta);
        area = findViewById(R.id.text_area);

        // data/hora atual
        LocalDateTime agora = LocalDateTime.now();

        // formatar a data
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd-MM-uuuu");
        String dataFormatada = formatterData.format(agora);

        // formatar a hora
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH");
        String horaFormatada = formatterHora.format(agora);

        data = findViewById(R.id.text_data_talhao);
        data.setText(dataFormatada);
        hora = findViewById(R.id.text_hora_talhao);
        hora.setText(horaFormatada);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, talhao.getNomeList());
        spinnerTalhao.setAdapter(adapter);

        String spinnerArray[] = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAgua.setAdapter(spinnerAdapter);

        Relatorio();

    }

    //implementa todos os relatorios da view
    public void Relatorio() {

        //verifica se o item selecionado não é nulo
        if (spinnerTalhao.getSelectedItem() != null) {
            String idString = (String) spinnerTalhao.getSelectedItem();
            String[] id2 = idString.split(" ");
            lerDados.ler(data.getText().toString(), hora.getText().toString(), id2[0]);
            lerDados.lerNomePlanta(id2[0]);
        }

        // Implementa o método do click do botão
        relatorioTalhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (spinnerTalhao.getSelectedItem() == null) {
                  Alerta("Erro", "Selecione um talhão!!");
                } else if (planta.getNome() == null || talhao.getNome() == null || dadosSensor.getStatus() == null) {
                        Alerta("Erro", "Não existe registro para Data ou Hora selecionada!");
                } else {
                    umidadeTermometro.setText("Umidade: " + dadosSensor.getUmidade() + "%");
                    nomePlanta.setText(planta.getNome());
                    textoNome.setText(talhao.getNome());
                    textoCc.setText(talhao.getCc());
                    textoPpm.setText(talhao.getPpm());
                    area.setText(talhao.getArea());
                    textoStatus.setText(dadosSensor.getStatus());
                    int umidade = dadosSensor.getUmidade();
                    int cc = Integer.parseInt(talhao.getCc());
                    int ppm = Integer.parseInt(talhao.getPpm());

                    if (umidade + 3 >= cc) {
                        termometro.setImageResource(R.drawable.umidade_alta);
                    } else if (umidade - 3 > ppm) {
                        termometro.setImageResource(R.drawable.umidade_boa);
                    } else {
                        termometro.setImageResource(R.drawable.umidade_baixa);
                    }

                    //chama o relatório novamente, pois pode ser inserido outra data e/ou hora
                }
                Relatorio();
            }

        });

        relatorioGastoAgua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mes = spinnerAgua.getSelectedItem().toString();

                for (int dia = 1; dia <= 31; dia++) {

                    if (dia < 10) {
                        String data = "0" + dia + "-" + mes + "-2020";
                        lerDados.lerGastoAgua("1", data);
                    } else {
                        String data = dia + "-" + mes + "-2020";
                        lerDados.lerGastoAgua("1", data);
                    }

                }

                GraphView graph = findViewById(R.id.graph);
                if (dadosSensor.getQntdAguaList().size() > 0) {
                    LineGraphSeries<DataPoint> series = new LineGraphSeries<>();
                    for (int i = 0; i < dadosSensor.getQntdAguaList().size(); i++) {
                        Integer dia = Integer.parseInt(dadosSensor.getDataList().get(i).substring(0, 2));
                        DataPoint point = new DataPoint(dia, dadosSensor.getQntdAguaList().get(i));
                        series.appendData(point, true, dadosSensor.getQntdAguaList().size());
                        Log.e("listasensor", dadosSensor.getDataList().toString() + "----" + dadosSensor.getQntdAguaList());
                    }
                    dadosSensor.getQntdAguaList().clear();
                    dadosSensor.getDataList().clear();
                    graph.addSeries(series);
                } else {
                    Alerta("Erro", "Não existe leitura para o mês selecionado!");
                }

            }
        });

    }

    public void Alerta(String title, String msg){
        final AlertDialog.Builder builder = new AlertDialog.Builder(RelatorioActivity.this);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(RelatorioActivity.this, "Ok=", Toast.LENGTH_SHORT).show();
            }
        });
        alerta = builder.create();
        alerta.show();
    }
}



