package com.example.suttartmaca;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

public class musterilerDAO {
    public void yeniMusteriEkle(VeritabaniYardimcisi vt, String musteri_adsoyad, String musteri_telefon, String musteri_aciklama) {
        SQLiteDatabase dbx = vt.getWritableDatabase();
        ContentValues values2 = new ContentValues();
        values2.put("musteri_adi", musteri_adsoyad);
        values2.put("musteri_telefon", musteri_telefon);
        values2.put("musteri_aciklama", musteri_aciklama);
        dbx.insertOrThrow("alinan_sut_musteriler", null, values2);
        dbx.close();
    }

    public void musteriSilmece(VeritabaniYardimcisi vt, int musteri_no) {
        SQLiteDatabase dbx = vt.getWritableDatabase();
        dbx.delete("alinan_sut_musteriler", "musteri_no=?", new String[]{String.valueOf(musteri_no)});
        dbx.close();
    }

    @SuppressLint("Range")
    public ArrayList<musterilerDBvariables> tumMusteriler(VeritabaniYardimcisi vt) {
        ArrayList<musterilerDBvariables> musteriList = new ArrayList<>();
        SQLiteDatabase dbx = vt.getWritableDatabase();
        Cursor cursor = dbx.rawQuery("SELECT * FROM alinan_sut_musteriler",null);

        while (cursor.moveToNext()) {
            musteriList.add(new musterilerDBvariables(cursor.getInt(cursor.getColumnIndex("musteri_no")), cursor.getString(cursor.getColumnIndex("musteri_adi")), cursor.getString(cursor.getColumnIndex("musteri_telefon")), cursor.getString(cursor.getColumnIndex("musteri_aciklama"))));
        }

        return musteriList;
    }
}
