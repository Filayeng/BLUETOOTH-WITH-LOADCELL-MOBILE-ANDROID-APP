package com.example.suttartmaca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class tumMusterileriGoruntuleme extends AppCompatActivity {
    ArrayList<String> t1 = new ArrayList<String>();ArrayList<String> t2 = new ArrayList<String>();ArrayList<String> t3 = new ArrayList<String>();ArrayList<String> t4 = new ArrayList<String>();
    ArrayList<Integer> t5 = new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tum_musterileri_goruntuleme);

        RecyclerView recyclerWievTumMusteriler = findViewById(R.id.recyclerWievTumMusteri);
        Button tumMusterilerScrnMainExit = findViewById(R.id.tumMusterilerScrnMainExit);

        methodsANDvariables.dateMain();
        ((TextView) findViewById(R.id.page231ToolbarDate)).setText(methodsANDvariables.todayDate);
        ((TextView) findViewById(R.id.page232ToolbarDay)).setText(methodsANDvariables.todayDay);
        ((TextView) findViewById(R.id.connectInfoBox233)).setText(MainActivity.conncettingText);

        for(musterilerDBvariables x : MainActivity.gelenMusteriDataList){
            t2.add(x.getMusteri_aciklama());
            t1.add(x.getMusteri_adsoyad());
            t5.add(x.getMusteri_id());
        }

        MyAdapter myAdapter = new MyAdapter(getBaseContext(),t1,t2,t1,t2,2);
        recyclerWievTumMusteriler.setHasFixedSize(false);
        recyclerWievTumMusteriler.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        recyclerWievTumMusteriler.setAdapter(myAdapter);

        recyclerWievTumMusteriler.addOnItemTouchListener(
                new RecyclerItemClickListener(getBaseContext(), recyclerWievTumMusteriler ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        methodsANDvariables.dateMain();
                        Intent intent = new Intent(tumMusterileriGoruntuleme.this, alinanSutGoruntuleme.class);
                        intent.putExtra("deger", 1);
                        methodsANDvariables.musteriDataMain = t5.get(position);
                        startActivity(intent);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

        tumMusterilerScrnMainExit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(tumMusterileriGoruntuleme.this, customerMainPage.class);startActivity(intent);
            }
        });

    }
}