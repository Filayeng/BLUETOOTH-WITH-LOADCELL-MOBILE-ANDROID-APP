package com.example.suttartmaca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class bosaltmaEkrani extends AppCompatActivity {
    ArrayList<String> t1 = new ArrayList<String>();ArrayList<String> t2 = new ArrayList<String>();ArrayList<String> t3 = new ArrayList<String>();ArrayList<String> t4 = new ArrayList<String>();
    public static VeritabaniYardimcisi vt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bosaltma_ekrani);

        CardView saveBosaltilanScreen  = findViewById(R.id.saveBosaltilanScreen);
        Button butBosaltilanScrnMainExit = findViewById(R.id.BosaltilanScrnMainExit);
        Button butBosaltilanSvScrnCik = findViewById(R.id.butBosaltilanSvScrnCik);
        Button butBosaltilanSutEkleme = findViewById(R.id.butBosaltilanSutEkleme);
        Button butBosaltilanSvScrnSave = findViewById(R.id.butBosaltilanSvScrnSave);
        RecyclerView recyclerWievAlSutler = findViewById(R.id.recyclerWievBosaltilanVeriler);
        EditText textBosaltilanMusteriAdSave = findViewById(R.id.textBosaltilanMusteriAdSave);
        EditText bosaltilantextAlınanMiktarSave = findViewById(R.id.bosaltilantextAlınanMiktarSave);
        EditText bosaltilansutFiyatıSave = findViewById(R.id.bosaltilansutFiyatıSave);
        EditText bosaltilanalinanTarihSave = findViewById(R.id.bosaltilanalinanTarihSave);
        EditText bosaltilanaciklamaSave = findViewById(R.id.bosaltilanaciklamaSave);



        methodsANDvariables.dateMain();
        ((TextView) findViewById(R.id.page411ToolbarDate)).setText(methodsANDvariables.todayDate);
        ((TextView) findViewById(R.id.page412ToolbarDay)).setText(methodsANDvariables.todayDay);
        ((TextView) findViewById(R.id.connectInfoBox413)).setText(MainActivity.conncettingText);

        saveBosaltilanScreen.setVisibility(View.INVISIBLE);

        for(bosaltilanSutlerDBvariables x : MainActivity.gelenBosaltilanSutlerDataList){
            t1.add(x.getMusteri_bos_adi());
            t2.add(x.getBosaltilan_litre()+ "L");
            t3.add(x.getBosaltilan_tarih());
            t4.add(x.getBosaltilan_fiyat()+ " TL");
        }

        MyAdapter myAdapter = new MyAdapter(getBaseContext(),t1,t2,t3,t4,1);
        recyclerWievAlSutler.setHasFixedSize(false);
        recyclerWievAlSutler.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        recyclerWievAlSutler.setAdapter(myAdapter);



        butBosaltilanScrnMainExit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(bosaltmaEkrani.this, customerMainPage.class);startActivity(intent);
            }
        });


        butBosaltilanSvScrnCik.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                saveBosaltilanScreen.setVisibility(View.INVISIBLE);
            }

        });

        butBosaltilanSvScrnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                vt = new VeritabaniYardimcisi(getBaseContext());
                new bosaltilansutlerDAO().yeniBosaltilanSutEkle(vt, 1,textBosaltilanMusteriAdSave.getText().toString(),bosaltilantextAlınanMiktarSave.getText().toString(),bosaltilansutFiyatıSave.getText().toString(),methodsANDvariables.todayNumDate,bosaltilanaciklamaSave.getText().toString());
                saveBosaltilanScreen.setVisibility(View.INVISIBLE);
                Toast.makeText(getBaseContext(), "Yeni alım başarıyla eklendi !", Toast.LENGTH_LONG).show();
                t1.clear();t2.clear();t3.clear();t4.clear();
                MainActivity.getDBData();
                for(bosaltilanSutlerDBvariables x : MainActivity.gelenBosaltilanSutlerDataList){
                    t1.add(x.getMusteri_bos_adi());
                    t2.add(x.getBosaltilan_litre()+ "L");
                    t3.add(x.getBosaltilan_tarih());
                    t4.add(x.getBosaltilan_fiyat()+ " TL");
                }

                MyAdapter myAdapter = new MyAdapter(getBaseContext(),t1,t2,t3,t4,1);
                recyclerWievAlSutler.setHasFixedSize(false);
                recyclerWievAlSutler.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                recyclerWievAlSutler.setAdapter(myAdapter);
            }
        });

        butBosaltilanSutEkleme.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                saveBosaltilanScreen.setVisibility(View.VISIBLE);
                bosaltilanalinanTarihSave.setText(methodsANDvariables.todayNumDate);
            }

        });



    }
}