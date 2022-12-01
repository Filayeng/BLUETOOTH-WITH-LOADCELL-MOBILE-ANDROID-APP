package com.example.suttartmaca;

import android.os.Environment;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class localvariables {
    public static void checkExternalMedia() {
        String state = Environment.getExternalStorageState();
        if (!"mounted".equals(state)) {
            if (!"mounted_ro".equals(state)) {
            }
        }
    }

    public static void writeToSDFile() {
        //Environment.getExternalStorageDirectory().getAbsolutePath() +
        Log.e("yol _-_-_-_",Environment.getExternalStorageDirectory().getAbsolutePath());
        File dir = new File("/data/data/com.example.suttartmaca/variables");
        dir.mkdirs();
        try {
            FileOutputStream f = new FileOutputStream(new File(dir, "myData.txt"));
            PrintWriter pw = new PrintWriter(f);
            pw.println("capacity=" + calculatorMethods.tankerCapacity + "/n");
            pw.println("multipier=" + calculatorMethods.lMultipier);
            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.i("ContentValues", "******* File not found. Did you add a WRITE_EXTERNAL_STORAGE permission to the   manifest?");
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public static void readFromFile() {
        String aBuffer = "";
        try {
            BufferedReader myReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("/data/data/com.example.suttartmaca/variables/myData.txt"))));
            while (true) {
                String readLine = myReader.readLine();
                String aDataRow = readLine;
                if (readLine == null) {
                    break;
                }
                aBuffer = aBuffer + aDataRow;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        String[] parts = aBuffer.split("/n");
        calculatorMethods.tankerCapacity = Integer.valueOf(String.valueOf(parts[0].substring(parts[0].indexOf("=") + 1)));
        calculatorMethods.lMultipier = (double) Float.valueOf(String.valueOf(parts[1].substring(parts[1].indexOf("=") + 1))).floatValue();
    }
}
