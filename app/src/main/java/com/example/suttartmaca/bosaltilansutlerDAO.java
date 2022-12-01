package com.example.suttartmaca;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;



public class bosaltilansutlerDAO {
    public void yeniBosaltilanSutEkle(VeritabaniYardimcisi vt, int musteri_bos_no, String musteri_bos_adi, String bosaltilan_litre, String bosaltilan_fiyat, String bosaltilan_tarih, String bosaltilan_aciklama) {
        SQLiteDatabase dbx = vt.getWritableDatabase();
        ContentValues values3 = new ContentValues();
        values3.put("musteri_bos_no", musteri_bos_no);
        values3.put("musteri_bos_adi", musteri_bos_adi);
        values3.put("bosaltilan_litre", bosaltilan_litre);
        values3.put("bosaltilan_fiyat", bosaltilan_fiyat);
        values3.put("bosaltilan_tarih", bosaltilan_tarih);
        values3.put("bosaltilan_aciklama", bosaltilan_aciklama);
        dbx.insertOrThrow("bosaltilanSutler", null, values3);
        dbx.close();
    }

    @SuppressLint("Range")
    public ArrayList<bosaltilanSutlerDBvariables> tumBosaltilanSutler(VeritabaniYardimcisi vt) {
        ArrayList<bosaltilanSutlerDBvariables> bosaltilanSutList = new ArrayList<bosaltilanSutlerDBvariables>();
        SQLiteDatabase dbx = vt.getWritableDatabase();
        Cursor cursor = dbx.rawQuery("SELECT * FROM bosaltilanSutler",null);

        while (cursor.moveToNext()) {
            bosaltilanSutList.add(new bosaltilanSutlerDBvariables(cursor.getInt(cursor.getColumnIndex("musteri_bos_no")), cursor.getString(cursor.getColumnIndex("musteri_bos_adi")), cursor.getString(cursor.getColumnIndex("bosaltilan_litre")), cursor.getString(cursor.getColumnIndex("bosaltilan_fiyat")),cursor.getString(cursor.getColumnIndex("bosaltilan_tarih")),cursor.getString(cursor.getColumnIndex("bosaltilan_aciklama"))));
        }

        return bosaltilanSutList;
    }

}
