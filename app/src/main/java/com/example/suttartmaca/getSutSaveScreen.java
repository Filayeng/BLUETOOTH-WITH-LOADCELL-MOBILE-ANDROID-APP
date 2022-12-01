package com.example.suttartmaca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class getSutSaveScreen extends AppCompatActivity {
    public RecyclerView recyclerWiev;
    private VeritabaniYardimcisi vt;
    public static int musteriID;
    ArrayList<String> t1 = new ArrayList<String>();ArrayList<String> t2 = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_get_sut_save_screen);
        RecyclerView recyclerWiev = findViewById(R.id.recyclerView);
        CardView saveScreen = findViewById(R.id.saveScreen);
        CardView personSaveScreenn = findViewById(R.id.personSaveScreen);
        Button butSvScrnSave = findViewById(R.id.butSvScrnSave);
        Button butSvScrnCik = findViewById(R.id.butSvScrnCik);
        Button butMusteriDegistir = findViewById(R.id.butMusteriDegistir);
        Button butMusteriEkle = findViewById(R.id.butMusteriEkle);
        Button butPersonSvScrnSave = findViewById(R.id.butPersonSvScrnSave);
        Button butPersonSvScrnCik = findViewById(R.id.butPersonSvScrnCik);

        EditText textAlınanMiktarSave = findViewById(R.id.textAlınanMiktarSave);
        EditText textMusteriAdSave = findViewById(R.id.textMusteriAdSave);
        EditText textAlinanTarihSave = findViewById(R.id.alinanTarihSave);
        EditText textAciklama = findViewById(R.id.aciklamaSave);
        EditText textSutFiyatıSave = findViewById(R.id.sutFiyatıSave);
        EditText textYeniMusteriAdSave = findViewById(R.id.textYeniMusteriAdSave);
        EditText textPersonAciklamaSave = findViewById(R.id.textPersonAciklamaSave);
        EditText textPersonTelNo = findViewById(R.id.textPersonTelNo);
        Button getSaveScrnMainExit = findViewById(R.id.getSaveScrnMainExit);
        saveScreen.setVisibility(View.INVISIBLE);
        personSaveScreenn.setVisibility(View.INVISIBLE);

        vt = new VeritabaniYardimcisi(getBaseContext());

        //t1.add("ömer");t1.add("ali");t1.add("ayşe");t1.add("seliin");t1.add("mustafa");
        //t2.add("1000 L");t2.add("900 L");t2.add("1500 L");t2.add("850 L");t2.add("8550 L");

        for(musterilerDBvariables x : MainActivity.gelenMusteriDataList){
            t2.add(String.valueOf(x.getMusteri_id()));
            t1.add(x.getMusteri_adsoyad());}

        MyAdapter myAdapter = new MyAdapter(getBaseContext(),t1,t2,t1,t2,0);
        recyclerWiev.setHasFixedSize(false);
        recyclerWiev.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        recyclerWiev.setAdapter(myAdapter);

        recyclerWiev.addOnItemTouchListener(
                new RecyclerItemClickListener(getBaseContext(), recyclerWiev ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        methodsANDvariables.dateMain();
                        //Log.e("mesaj>>>>>>", t1.get(position));
                        musteriID = Integer.parseInt(t2.get(position));
                        saveScreen.setVisibility(View.VISIBLE);
                        recyclerWiev.setVisibility(View.INVISIBLE);
                        textMusteriAdSave.setText(t1.get(position));
                        textAlınanMiktarSave.setText(String.valueOf(MainActivity.sonDegisimMiktari));
                        textAlinanTarihSave.setText(methodsANDvariables.todayNumDate);

                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

        getSaveScrnMainExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getSutSaveScreen.this, MainActivity.class);
                startActivity(intent);
            }
        });

        butMusteriDegistir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerWiev.setVisibility(View.VISIBLE);
                saveScreen.setVisibility(View.INVISIBLE);

            }
        });

        butSvScrnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new sutlerDAO().alinanSutEkle(vt,musteriID,musteriID,textAlınanMiktarSave.getText().toString(), textSutFiyatıSave.getText().toString(),textAlinanTarihSave.getText().toString(),textAciklama.getText().toString());
                saveScreen.setVisibility(View.INVISIBLE);
                Toast.makeText(getBaseContext(), "Yeni alım başarıyla eklendi !", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getSutSaveScreen.this, MainActivity.class);
                startActivity(intent);
            }
        });

        butSvScrnCik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getSutSaveScreen.this, MainActivity.class);
                startActivity(intent);
            }
        });

        butMusteriEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                personSaveScreenn.setVisibility(View.VISIBLE);
                recyclerWiev.setVisibility(View.INVISIBLE);
            }
        });

        butPersonSvScrnCik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveScreen.setVisibility(View.INVISIBLE);
                personSaveScreenn.setVisibility(View.INVISIBLE);
                recyclerWiev.setVisibility(View.VISIBLE);
            }
        });

        butPersonSvScrnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new musterilerDAO().yeniMusteriEkle(vt,textYeniMusteriAdSave.getText().toString(),textPersonTelNo.getText().toString(),textPersonAciklamaSave.getText().toString());
                personSaveScreenn.setVisibility(View.INVISIBLE);
                t1.clear();t2.clear();
                MainActivity.getDBData();
                for(musterilerDBvariables x : MainActivity.gelenMusteriDataList){
                    t2.add(String.valueOf(x.getMusteri_id()));
                    t1.add(x.getMusteri_adsoyad());}

                MyAdapter myAdapter = new MyAdapter(getBaseContext(),t1,t2,t1,t2,0);
                recyclerWiev.setHasFixedSize(false);
                recyclerWiev.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                recyclerWiev.setAdapter(myAdapter);
                recyclerWiev.setVisibility(View.VISIBLE);
            }
        });



    }
}

