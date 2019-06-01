package br.usjt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class LocalizacoesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localizacoes);

        List<Localizacao> localizacoes = new LocalizacaoDAO(this).recuperaLocalizacoes();

        RecyclerView localizacoesRecyclerView = findViewById(R.id.localizacoes_list);
        localizacoesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        localizacoesRecyclerView.setNestedScrollingEnabled(false);
        localizacoesRecyclerView.setAdapter(new LocalizacoesAdapter(localizacoes));
    }
}
