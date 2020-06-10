package com.example.phone.activitys;

import android.app.Activity;
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
    private TextView umidadeTermometro;
    private ImageView termometro;
    private Spinner spinnerTalhao;
    private TextView textoNome;
    private TextView textoCc;
    private TextView textoPpm;
    private TextView textoStatus;
    private TextView area;
    private TextView nomePlanta;

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
        relatorioTalhao = findViewById(R.id.botao_relatorio_talhao);
        umidadeTermometro = findViewById(R.id.texto_umidade_termometro);
        termometro = findViewById(R.id.view_termometro);
        textoNome = findViewById(R.id.text_nome);
        textoCc = findViewById(R.id.text_cc);
        textoPpm = findViewById(R.id.text_ppm);
        textoStatus = findViewById(R.id.text_status);
        nomePlanta = findViewById(R.id.text_planta);
        area = findViewById(R.id.text_area);
        data = findViewById(R.id.text_data_talhao);
        String horaAtual = "17"; //gc.get(Calendar.HOUR_OF_DAY);
        hora = findViewById(R.id.text_hora_talhao);
        hora.setText(horaAtual);

        // data/hora atual
        LocalDateTime agora = LocalDateTime.now();

        // formatar a data
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd-MM-uuuu");
        String dataFormatada = formatterData.format(agora);

        // formatar a hora
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH");
        String horaFormatada = formatterHora.format(agora);

        data = findViewById(R.id.text_data_talhao);
        data.setText("04-06-2020");
        hora = findViewById(R.id.text_hora_talhao);
        hora.setText("17");

            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, talhao.getNomeList());
            spinnerTalhao.setAdapter(adapter);

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

                    for (int dia = 1; dia <= 31; dia++) {
                        if (dia < 10) {
                            String data = "0" + dia + "-06-2020";
                            lerDados.lerGastoAgua("1", data);
                        } else {
                            String data = dia + "-06-2020";
                            lerDados.lerGastoAgua("1", data);
                        }

                    }

                    umidadeTermometro.setText("Umidade: " + dadosSensor.getUmidade());
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

                    GraphView graph = (GraphView) findViewById(R.id.graph);

                    LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                            new DataPoint(0, 1)
                    });
                    graph.addSeries(series);

                    Log.e("listasensor", dadosSensor.getDataList().toString() + "----" + dadosSensor.getQntdAguaList());
                    //chama o relatório novamente, pois pode ser inserido outra data e/ou hora
                    Relatorio();
                }
            });


        }
    }

