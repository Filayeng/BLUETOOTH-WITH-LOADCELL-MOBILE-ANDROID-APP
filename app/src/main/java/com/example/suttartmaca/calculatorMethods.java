package com.example.suttartmaca;

import android.content.Context;
import android.util.Log;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class calculatorMethods {
    public static double lMultipier = 1.19999;
    public static Integer tankerCapacity = 1200;

    public static double kgTOleter(int receiverData) {
        return ((double) receiverData) * lMultipier;
    }

    public static int imageDividerNumber() {
        return Math.round((float) (tankerCapacity.intValue() / 15));
    }

    public static void writeToFile(String data, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("config.txt", 0));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}
