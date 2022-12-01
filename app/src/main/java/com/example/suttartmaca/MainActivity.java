package com.example.suttartmaca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.Format;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    public String arduinoMsg = "";
    public String deviceName = null;
    public String deviceAddress;
    public static Handler handler;
    public static BluetoothSocket mmSocket;
    public static bluetoothConnncetorCls.ConnectedThread connectedThread;
    public static bluetoothConnncetorCls.CreateConnectThread createConnectThread;
    public String sensorData = "0";
    public final static int CONNECTING_STATUS = 1; // used in bluetooth handler to identify message status
    public final static int MESSAGE_READ = 2; // used in bluetooth handler to identify message update
    public static Integer sonDegisimMiktari=0,totalData=0,oncekiTotalData=0;
    public static String conncettingText="Hiçbir cihaza bağlanılmadı.";
    public static ArrayList<alinansutlerDBvariables> gelenSutDataList;
    public static ArrayList<musterilerDBvariables> gelenMusteriDataList;
    public static ArrayList<bosaltilanSutlerDBvariables> gelenBosaltilanSutlerDataList;
    public static VeritabaniYardimcisi vt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getApplication().setTheme(R.style.AppTheme);

        Toolbar toolbar = findViewById(R.id.toolbar);
        final ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        Button buttonConnect = findViewById(R.id.buttonConnect);
        buttonConnect.setEnabled(true);
        Button butTart = findViewById(R.id.butTart);
        Button butKalibrasyon = findViewById(R.id.butKalibrasyon);
        Button butKaydet = findViewById(R.id.butKaydet);
        Button butPage1 = findViewById(R.id.butPage1);
        Button butPage2 = findViewById(R.id.butPage2);
        Button butPage3 = findViewById(R.id.butPage3);
        TextView conncectInfoBox = findViewById(R.id.connectInfoBox2);
        TextView al_verLabel = findViewById(R.id.al_verLabel);
        EditText sonAlinanDataText = findViewById(R.id.sonAlinanDataText);
        EditText totalDataText = findViewById(R.id.totalDataText2);
        EditText tarihText = findViewById(R.id.tarihMainText);
        EditText saatText = findViewById(R.id.saatMainText);
        ImageView truckLevelImg = findViewById(R.id.truckImageView);

        butKalibrasyon.setBackgroundTintList(getResources().getColorStateList(R.color.butFalsecColor));
        butTart.setBackgroundTintList(getResources().getColorStateList(R.color.butFalsecColor));

        sonAlinanDataText.setText(Integer.toString(sonDegisimMiktari) + " L");
        totalDataText.setText(Integer.toString(totalData) + " L");
        truckLevelImg.setImageResource(animationClass.imageHolder());
        sonAlinanDataText.setFocusable(false);
        totalDataText.setFocusable(false);

        vt = new VeritabaniYardimcisi(getBaseContext());
        getDBData();

        //tarih kısmı
        methodsANDvariables.dateMain();
        saatText.setText(methodsANDvariables.todayTime);
        tarihText.setText(methodsANDvariables.todayDate + " " +methodsANDvariables.todayDay);

        deviceName = getIntent().getStringExtra("deviceName");
        //deviceName = "HC-06";
        if (deviceName != null){
            // Get the device address to make BT Connection
            deviceAddress = getIntent().getStringExtra("deviceAddress");
            // Show progree and connection status
            //toolbar.setSubtitle("Connecting to " + deviceName + "...");
            conncectInfoBox.setText(deviceName + " adlı cihaza bağlanılıyor...");
            progressBar.setVisibility(View.VISIBLE);
            buttonConnect.setEnabled(true);

            /*
            This is the most important piece of code. When "deviceName" is found
            the code will call a new thread to create a bluetooth connection to the
            selected device (see the thread code below)
             */
            BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            createConnectThread = new bluetoothConnncetorCls.CreateConnectThread(bluetoothAdapter,deviceAddress);
            createConnectThread.start();

        }


        /*
        Second most important piece of Code. GUI Handler
         */
        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg){
                switch (msg.what){
                    case CONNECTING_STATUS:
                        switch(msg.arg1){
                            case 1:
                                //toolbar.setSubtitle("Connected to " + deviceName);
                                conncettingText = deviceName + " adlı cihaza bağlandı.";
                                conncectInfoBox.setText(conncettingText);
                                progressBar.setVisibility(View.GONE);
                                buttonConnect.setEnabled(true);
                                butTart.setEnabled(true);
                                butKalibrasyon.setEnabled(true);
                                butKalibrasyon.setBackgroundTintList(getResources().getColorStateList(R.color.purple_200));
                                butTart.setBackgroundTintList(getResources().getColorStateList(R.color.purple_200));
                                break;
                            case -1:
                                //toolbar.setSubtitle("Device fails to connect");
                                conncettingText = "Bağlantı başarısız oldu.";
                                conncectInfoBox.setText(conncettingText);
                                progressBar.setVisibility(View.GONE);
                                buttonConnect.setEnabled(true);
                                break;
                        }
                        break;

                    case MESSAGE_READ:
                        //arduinoMsg = msg.obj.toString(); // Read message from Arduino

                        String[] sensorDataList = msg.toString().split("=");
                        sensorData = sensorDataList[3].replace(" target","").replace(" ","");
                        sensorData = sensorData.replace(" ","");
                        List<String> sensonDataList2 = new ArrayList<String>(Arrays.asList(sensorData.split("")));
                        sensorData = "";
                        for(int i=0;i<sensonDataList2.size();i++){
                            switch (sensonDataList2.get(i)){
                                case "0": sensorData = sensorData + "0";break;
                                case "1": sensorData = sensorData + "1";break;
                                case "2": sensorData = sensorData + "2";break;
                                case "3": sensorData = sensorData + "3";break;
                                case "4": sensorData = sensorData + "4";break;
                                case "5": sensorData = sensorData + "5";break;
                                case "6": sensorData = sensorData + "6";break;
                                case "7": sensorData = sensorData + "7";break;
                                case "8": sensorData = sensorData + "8";break;
                                case "9": sensorData = sensorData + "9";break;
                            }
                        }
                        Log.e("=",sensorData);
                        totalData =(int) Math.round(calculatorMethods.kgTOleter(Integer.parseInt(sensorData)));
                        sonDegisimMiktari = totalData - oncekiTotalData;
                        if(sonDegisimMiktari<0){al_verLabel.setText("Son          " +
                                "Boşaltılan:");}
                        else{al_verLabel.setText("Son Alinan :");}
                        sonAlinanDataText.setText(Integer.toString(sonDegisimMiktari) + " L");
                        totalDataText.setText(Integer.toString(totalData) + " L");
                        sensorData = "";
                        truckLevelImg.setImageResource(animationClass.imageHolder());

                }
            }
        };


        butKalibrasyon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sonDegisimMiktari = 0;
                oncekiTotalData = totalData;
                sonAlinanDataText.setText(Integer.toString(sonDegisimMiktari) + " L");
            }
        });


        // Select Bluetooth Device
        buttonConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Move to adapter list
                Intent intent = new Intent(MainActivity.this, SelectDeviceActivity.class);
                startActivity(intent);
            }
        });

        butTart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cmdText = null;
                //String btnState = buttonToggle.getText().toString().toLowerCase();
                cmdText = "<gtdt>";
                connectedThread.write(cmdText);
            }
        });

        butPage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SelectDeviceActivity.class);
                startActivity(intent);

            }
        });

        butPage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, customerMainPage.class);
                startActivity(intent);
            }
        });

        butPage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, settingMainPage.class);
                startActivity(intent);
            }
        });

        butKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vt = new VeritabaniYardimcisi(getBaseContext());

                methodsANDvariables.dateMain();

                for(alinansutlerDBvariables x : gelenSutDataList){
                    Log.e("data : ", gelenMusteriDataList.get(Integer.parseInt(x.getMusteri_adi_db().getMusteri_adsoyad())-1).getMusteri_adsoyad() + "   " +x.getAlinan_tarih());}
                for(musterilerDBvariables x : gelenMusteriDataList){
                    Log.e("mesaj >>>>>>>>>",x.getMusteri_id() + " " + x.getMusteri_adsoyad());}
                for(bosaltilanSutlerDBvariables x : gelenBosaltilanSutlerDataList){
                    Log.e("mesaj >>>>>>>>>",x.getMusteri_bos_adi() + " " + x.getBosaltilan_litre());}
                Intent intent = new Intent(MainActivity.this, getSutSaveScreen.class);
                startActivity(intent);

            }
        });


    }

    @Override
    public void onBackPressed() {
        // Terminate Bluetooth Connection and close app
        if (createConnectThread != null){
            createConnectThread.cancel();
        }
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    public static void getDBData(){
        gelenSutDataList = new sutlerDAO().tumAlinanSutler(vt);
        gelenMusteriDataList = new musterilerDAO().tumMusteriler(vt);
        gelenBosaltilanSutlerDataList = new bosaltilansutlerDAO().tumBosaltilanSutler(vt);
    }
}