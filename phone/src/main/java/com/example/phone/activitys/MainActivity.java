package com.example.phone.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.phone.R;

public class MainActivity extends Activity {

    private ImageView relatorio;
    private ImageView adicionar;
    private ImageView cadastroPlanta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relatorio = findViewById(R.id.icon_relatorio);
        adicionar = findViewById(R.id.icon_adicionar);
        cadastroPlanta = findViewById(R.id.icon_planta);

        relatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RelatorioActivity.class);
                startActivity(intent);
            }
        });

        adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, CadastroTalhaoActivity.class);
                    startActivity(intent);
            }
        });

        cadastroPlanta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PlantaActivity.class);
                startActivity(intent);
            }
        });

    }

}
