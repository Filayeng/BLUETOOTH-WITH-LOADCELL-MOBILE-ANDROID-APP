package com.example.suttartmaca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class alinanSutGoruntuleme extends AppCompatActivity {
    public static boolean bas1 = false, bitis2=false,mainControl=false;
    public static String basDate="1/1/2022",bitisDate= methodsANDvariables.todayNumDate;
    ArrayList<String> t1 = new ArrayList<String>();ArrayList<String> t2 = new ArrayList<String>();ArrayList<String> t3 = new ArrayList<String>();ArrayList<String> t4 = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alinan_sut_goruntuleme);  // bununyeri önelemi Place of the code text very importante.

        RecyclerView recyclerWievAlSutler = findViewById(R.id.recyclerWievAlSutler);
        CalendarView takvim1 = findViewById(R.id.takvim1);
        CalendarView takvim2 = findViewById(R.id.takvim2);
        Button butBasFilterDate = findViewById(R.id.butBasFilterDate);
        Button butBitFilterDate = findViewById(R.id.butBitFilterDate);
        Button butDateFilter = findViewById(R.id.butDateFilter);
        Button getSutAlisDataScrnMainExit = findViewById(R.id.getSutAlisDataScrnMainExit);
        Button butSutGorGeri = findViewById(R.id.butSutGorGeri);
        methodsANDvariables.dateMain();
        ((TextView) findViewById(R.id.page211ToolbarDate)).setText(methodsANDvariables.todayDate);
        ((TextView) findViewById(R.id.page212ToolbarDay)).setText(methodsANDvariables.todayDay);
        ((TextView) findViewById(R.id.connectInfoBox213)).setText(MainActivity.conncettingText);

        takvim1.setVisibility(View.INVISIBLE);
        takvim2.setVisibility(View.INVISIBLE);
        takvim1.setDate(1640995200);
        takvim2.setDate(System.currentTimeMillis());
        butBitFilterDate.setText(methodsANDvariables.todayNumDate);
        MainActivity.getDBData();


        butSutGorGeri.setVisibility(View.INVISIBLE);
        try{
        int data = getIntent().getExtras().getInt("deger");
        if(data == 1){
            mainControl = true;
            t1.clear();t2.clear();t3.clear();t4.clear();
            butSutGorGeri.setVisibility(View.VISIBLE);
            basDate = methodsANDvariables.basDateMain;
            bitisDate = methodsANDvariables.bitisDateMain;
            String[] basDateParse = basDate.split("/");
            String[] bitisDateParse = bitisDate.split("/");
            Date d1 = new Date(Integer.parseInt(basDateParse[2]), Integer.parseInt(basDateParse[1]), Integer.parseInt(basDateParse[0]));   // başlangıç
            Date d3 = new Date(Integer.parseInt(bitisDateParse[2]), Integer.parseInt(bitisDateParse[1]), Integer.parseInt(bitisDateParse[0]));

            for(alinansutlerDBvariables x : MainActivity.gelenSutDataList){
                String[] gelenDateParse = x.getAlinan_tarih().split("/");
                Date d2 = new Date(Integer.parseInt(gelenDateParse[2]), Integer.parseInt(gelenDateParse[1]), Integer.parseInt(gelenDateParse[0]));
                if((d2.after(d1) && d2.before(d3)) || (d2.equals(d1) || d2.equals(d3))){
                if(x.getMusteri_adi_db().getMusteri_id() == methodsANDvariables.musteriDataMain){
                        t4.add(x.getAlinan_fiyat() + " L");
                        t3.add(x.getAlinan_tarih());
                        t2.add(x.getAlinan_litre() + " TL");
                        t1.add(MainActivity.gelenMusteriDataList.get(Integer.parseInt(x.getMusteri_adi_db().getMusteri_adsoyad())-1).getMusteri_adsoyad());
                    }
                }
            }

        }

        }
        catch (Exception e){
            basDate = methodsANDvariables.basDateMain;
            bitisDate = methodsANDvariables.bitisDateMain;
            String[] basDateParse = basDate.split("/");
            String[] bitisDateParse = bitisDate.split("/");
            Date d1 = new Date(Integer.parseInt(basDateParse[2]), Integer.parseInt(basDateParse[1]), Integer.parseInt(basDateParse[0]));   // başlangıç
            Date d3 = new Date(Integer.parseInt(bitisDateParse[2]), Integer.parseInt(bitisDateParse[1]), Integer.parseInt(bitisDateParse[0]));
            //Log.e("burada","ydı");
            for(alinansutlerDBvariables x : MainActivity.gelenSutDataList){
                Log.e("mesaj >>>>>>>>>>>>>",x.getAlinan_tarih());
                String[] gelenDateParse = x.getAlinan_tarih().split("/");
                Date d2 = new Date(Integer.parseInt(gelenDateParse[2]), Integer.parseInt(gelenDateParse[1]), Integer.parseInt(gelenDateParse[0]));
                if((d2.after(d1) && d2.before(d3)) || (d2.equals(d1) || d2.equals(d3))){
                    t4.add(x.getAlinan_fiyat() + " L");
                    t3.add(x.getAlinan_tarih());
                    t2.add(x.getAlinan_litre() + " TL");
                    t1.add(MainActivity.gelenMusteriDataList.get(Integer.parseInt(x.getMusteri_adi_db().getMusteri_adsoyad())-1).getMusteri_adsoyad());
                }
            }
        }

        if(t1.size() == 0){
            Toast.makeText(getBaseContext(), "Hiçbir kayıt bulunamadı !", Toast.LENGTH_LONG).show();
        }

        MyAdapter myAdapter = new MyAdapter(getBaseContext(),t1,t4,t3,t2,1);
        recyclerWievAlSutler.setHasFixedSize(false);
        recyclerWievAlSutler.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        recyclerWievAlSutler.setAdapter(myAdapter);


        getSutAlisDataScrnMainExit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(alinanSutGoruntuleme.this, customerMainPage.class);startActivity(intent);
            }
        });

        butBasFilterDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                takvim1.setVisibility(View.VISIBLE);
                butBitFilterDate.setEnabled(false);
                recyclerWievAlSutler.setVisibility(View.INVISIBLE);
            }
        });

        butBitFilterDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                 takvim2.setVisibility(View.VISIBLE);
                 butBasFilterDate.setEnabled(false);
                recyclerWievAlSutler.setVisibility(View.INVISIBLE);
            }
        });

        butSutGorGeri.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(alinanSutGoruntuleme.this, tumMusterileriGoruntuleme.class);startActivity(intent);
            }
        });

        takvim1.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                methodsANDvariables.basDateMain = i2 + "/" + (i1+1) + "/" + i;
                butBasFilterDate.setText(methodsANDvariables.basDateMain);
                takvim1.setVisibility(View.INVISIBLE);
                butBitFilterDate.setEnabled(true);
                recyclerWievAlSutler.setVisibility(View.VISIBLE);

            }
        });

        takvim2.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                methodsANDvariables.bitisDateMain = i2 + "/" + (i1+1) + "/" + i;
                butBitFilterDate.setText(methodsANDvariables.bitisDateMain);
                takvim2.setVisibility(View.INVISIBLE);
                recyclerWievAlSutler.setVisibility(View.VISIBLE);
                butBasFilterDate.setEnabled(true);
            }
        });


        butDateFilter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                t1.clear();t2.clear();t3.clear();t4.clear();
                basDate = methodsANDvariables.basDateMain;
                bitisDate = methodsANDvariables.bitisDateMain;
                String[] basDateParse = basDate.split("/");
                String[] bitisDateParse = bitisDate.split("/");
                Date d1 = new Date(Integer.parseInt(basDateParse[2]), Integer.parseInt(basDateParse[1]), Integer.parseInt(basDateParse[0]));   // başlangıç
                Date d3 = new Date(Integer.parseInt(bitisDateParse[2]), Integer.parseInt(bitisDateParse[1]), Integer.parseInt(bitisDateParse[0]));

                for(alinansutlerDBvariables x : MainActivity.gelenSutDataList){
                    String[] gelenDateParse = x.getAlinan_tarih().split("/");
                    Date d2 = new Date(Integer.parseInt(gelenDateParse[2]), Integer.parseInt(gelenDateParse[1]), Integer.parseInt(gelenDateParse[0]));

                    if((d2.after(d1) && d2.before(d3)) || (d2.equals(d1) || d2.equals(d3))){
                        Log.e(">>>>>>>>>>>>>>>>>",bitisDate);
                        if(mainControl){
                            if(x.getMusteri_adi_db().getMusteri_id() == methodsANDvariables.musteriDataMain){
                                t4.add(x.getAlinan_fiyat() + " L");
                                t3.add(x.getAlinan_tarih());
                                t2.add(x.getAlinan_litre() + " TL");
                                t1.add(MainActivity.gelenMusteriDataList.get(Integer.parseInt(x.getMusteri_adi_db().getMusteri_adsoyad())-1).getMusteri_adsoyad());
                                }
                        }
                        else{
                            t4.add(x.getAlinan_fiyat() + " L");
                            t3.add(x.getAlinan_tarih());
                            t2.add(x.getAlinan_litre() + " TL");
                            t1.add(MainActivity.gelenMusteriDataList.get(Integer.parseInt(x.getMusteri_adi_db().getMusteri_adsoyad())-1).getMusteri_adsoyad());
                        }
                    }
                }

                if(t1.size() == 0){
                    Toast.makeText(getBaseContext(), "Hiçbir kayıt bulunamadı !", Toast.LENGTH_LONG).show();
                }
                MyAdapter myAdapter = new MyAdapter(getBaseContext(),t1,t4,t3,t2,1);
                recyclerWievAlSutler.setHasFixedSize(false);
                recyclerWievAlSutler.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                recyclerWievAlSutler.setAdapter(myAdapter);

            }
        });

    }
}