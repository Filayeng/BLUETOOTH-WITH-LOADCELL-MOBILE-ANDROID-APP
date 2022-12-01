package com.example.suttartmaca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class settingMainPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_main_page);

        Button butPage1S = findViewById(R.id.butPage1S);
        Button butPage2S = findViewById(R.id.butPage2S);
        Button butPage3S = findViewById(R.id.butPage3S);
        TextView conncectInfoBox3 = findViewById(R.id.connectInfoBox313);
        TextView page3ToolbarDate = findViewById(R.id.page311ToolbarDate);
        TextView page3ToolbarDay = findViewById(R.id.page312ToolbarDay);
        EditText inputMultipier = findViewById(R.id.inputMultipier);
        EditText inputTankerCap = findViewById(R.id.inputTankerCap);
        butPage1S.setBackgroundTintList(getResources().getColorStateList(R.color.purple_200));
        butPage2S.setBackgroundTintList(getResources().getColorStateList(R.color.purple_200));
        butPage3S.setBackgroundTintList(getResources().getColorStateList(R.color.pageColor));

        methodsANDvariables.dateMain();
        page3ToolbarDate.setText(methodsANDvariables.todayDate);
        page3ToolbarDay.setText(methodsANDvariables.todayDay);

        conncectInfoBox3.setText(MainActivity.conncettingText);
        //DecimalFormat df = new DecimalFormat("#.####");
        localvariables.readFromFile();
        inputTankerCap.setText(String.valueOf(String.valueOf(calculatorMethods.tankerCapacity)));
        inputMultipier.setText((String.valueOf(calculatorMethods.lMultipier)).substring(0,5));



        butPage1S.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(settingMainPage.this, MainActivity.class);
                startActivity(intent);
                calculatorMethods.tankerCapacity = Integer.parseInt(String.valueOf(inputTankerCap.getText()));
                calculatorMethods.lMultipier = Float.valueOf(String.valueOf(inputMultipier.getText()));
                localvariables.writeToSDFile();
                //localvariables.checkExternalMedia();
            }
        });

        butPage2S.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(settingMainPage.this, customerMainPage.class);
                startActivity(intent);
                calculatorMethods.tankerCapacity = Integer.parseInt(String.valueOf(inputTankerCap.getText()));
                calculatorMethods.lMultipier = Float.valueOf(String.valueOf(inputMultipier.getText()));
                localvariables.writeToSDFile();

            }
        });

        butPage3S.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(settingMainPage.this, settingMainPage.class);
                startActivity(intent);
                calculatorMethods.tankerCapacity = Integer.parseInt(String.valueOf(inputTankerCap.getText()));
                calculatorMethods.lMultipier = Float.valueOf(String.valueOf(inputMultipier.getText()));
                localvariables.writeToSDFile();
            }
        });
    }



}