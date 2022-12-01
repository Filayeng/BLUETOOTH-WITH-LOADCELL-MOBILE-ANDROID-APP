package com.example.suttartmaca;

import android.icu.text.SimpleDateFormat;

import java.text.Format;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class methodsANDvariables {
    public static String todayDate,todayTime,todayDay,todayNumDate,basDateMain="1/1/2022",bitisDateMain;
    public static int musteriDataMain,musteriControlMain=0;


    public static void dateMain(){
        SimpleDateFormat sdf = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("tr"));
            todayNumDate = sdf.format(new Date());
            sdf = new SimpleDateFormat("dd MMMM yyyy", new Locale("tr"));
            todayDate = sdf.format(new Date());
            sdf = new SimpleDateFormat("HH:mm", new Locale("tr"));   //:ss saniye verir.
            todayTime = sdf.format(new Date());
            Format f = new SimpleDateFormat("EEEE");
            todayDay = f.format(new Date());
            bitisDateMain=todayNumDate;
        }
    }

}
