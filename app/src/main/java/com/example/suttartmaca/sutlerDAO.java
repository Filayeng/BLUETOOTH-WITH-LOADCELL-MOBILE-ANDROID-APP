package com.example.suttartmaca;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

public class sutlerDAO {

    @SuppressLint("Range")
    public ArrayList<alinansutlerDBvariables> tumAlinanSutler(VeritabaniYardimcisi vt) {
        ArrayList<alinansutlerDBvariables> alinanSutlerArrayList = new ArrayList<alinansutlerDBvariables>();
        SQLiteDatabase dbx = vt.getWritableDatabase();
        Cursor cursor = dbx.rawQuery("SELECT * FROM alinan_sut_musteriler,alinansutler WHERE alinan_sut_musteriler.musteri_no = alinansutler.musteri_adi", null);
        while (cursor.moveToNext()) {
            musterilerDBvariables m = (new musterilerDBvariables(cursor.getInt(cursor.getColumnIndex("musteri_no")), cursor.getString(cursor.getColumnIndex("musteri_adi")), cursor.getString(cursor.getColumnIndex("musteri_telefon")), cursor.getString(cursor.getColumnIndex("musteri_aciklama"))));
            alinanSutlerArrayList.add(new alinansutlerDBvariables(cursor.getInt(cursor.getColumnIndex("musteri_no")), cursor.getInt(cursor.getColumnIndex("musteri_adi")), cursor.getString(cursor.getColumnIndex("alinan_litre")), cursor.getString(cursor.getColumnIndex("alinan_fiyat")), cursor.getString(cursor.getColumnIndex("alinan_tarih")),cursor.getString(cursor.getColumnIndex("alinan_aciklama")),m));
        }
        return alinanSutlerArrayList;
    }

    @SuppressLint("Range")
    public ArrayList<alinansutlerDBvariables> musteriAra(VeritabaniYardimcisi vt, String keyWord) {
        ArrayList<alinansutlerDBvariables> alinanSutlerArrayList = new ArrayList<>();
        Cursor cursor = vt.getWritableDatabase().rawQuery("SELECT * FROM alinansutler WHERE musteri_no like '%" + keyWord + "%' ", (String[]) null);
        while (cursor.moveToNext()) {
        }
        return alinanSutlerArrayList;
    }

    public void musteriSil(VeritabaniYardimcisi vt, int musteri_no) {
        SQLiteDatabase dbx = vt.getWritableDatabase();
        dbx.delete("alinanSutler", "musteri_no=?", new String[]{String.valueOf(musteri_no)});
        dbx.close();
    }

    public void alinanSutEkle(VeritabaniYardimcisi vt, int musteri_no, int musteri_adi, String alinan_litre, String alinan_fiyat,String alinan_tarih, String alinan_aciklama) {
        SQLiteDatabase dbx = vt.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("musteri_no", musteri_no);
        values.put("musteri_adi", musteri_adi);
        values.put("alinan_litre", alinan_litre);
        values.put("alinan_fiyat", alinan_fiyat);
        values.put("alinan_tarih", alinan_tarih);
        values.put("alinan_aciklama", alinan_aciklama);
        dbx.insertOrThrow("alinansutler", null, values);
        dbx.close();
    }
}
