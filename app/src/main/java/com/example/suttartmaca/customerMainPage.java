package com.example.suttartmaca;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class customerMainPage extends AppCompatActivity {
    public String todayDate;
    public String todayDay;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_main_page);

        Button butPage1C = (Button) findViewById(R.id.butPage1C);
        Button butPage2C = (Button) findViewById(R.id.butPage2C);
        Button butPage3C = (Button) findViewById(R.id.butPage3C);
        Button butAllDataAlinan = (Button) findViewById(R.id.butAllDataAlinan);
        Button butTodayDataAlinan = (Button) findViewById(R.id.butTodayDataAlinan);
        Button butMusteriData = (Button) findViewById(R.id.butMusteriData);
        Button butDataBosaltilan = (Button) findViewById(R.id.butDataBosaltilan);

        butPage1C.setBackgroundTintList(getResources().getColorStateList(R.color.purple_200));
        butPage2C.setBackgroundTintList(getResources().getColorStateList(R.color.pageColor));
        butPage3C.setBackgroundTintList(getResources().getColorStateList(R.color.purple_200));
        ((TextView) findViewById(R.id.connectInfoBox2)).setText(MainActivity.conncettingText);
        ((ImageView) findViewById(R.id.truckImageView2)).setImageResource(animationClass.imageHolder());
        ((EditText) findViewById(R.id.totalDataText2)).setText(Integer.toString(MainActivity.totalData.intValue()) + " L");
        ((TextView) findViewById(R.id.page2ToolbarDate)).setText(methodsANDvariables.todayDate);
        ((TextView) findViewById(R.id.page2ToolbarDay)).setText(methodsANDvariables.todayDay);

        methodsANDvariables.dateMain();

        butPage1C.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                customerMainPage.this.startActivity(new Intent(customerMainPage.this, MainActivity.class));
            }
        });

        butPage2C.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                customerMainPage.this.startActivity(new Intent(customerMainPage.this, customerMainPage.class));
            }
        });

        butPage3C.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                customerMainPage.this.startActivity(new Intent(customerMainPage.this, settingMainPage.class));
            }
        });



        butAllDataAlinan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                methodsANDvariables.basDateMain= "1/1/2022";
                customerMainPage.this.startActivity(new Intent(customerMainPage.this, alinanSutGoruntuleme.class));
            }
        });

        butTodayDataAlinan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                methodsANDvariables.basDateMain= methodsANDvariables.todayNumDate;
                customerMainPage.this.startActivity(new Intent(customerMainPage.this, alinanSutGoruntuleme.class));
            }
        });

        butMusteriData.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                customerMainPage.this.startActivity(new Intent(customerMainPage.this, tumMusterileriGoruntuleme.class));
            }
        });

        butDataBosaltilan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                customerMainPage.this.startActivity(new Intent(customerMainPage.this, bosaltmaEkrani.class));
            }
        });

    }
}
