package com.example.suttartmaca;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class VeritabaniYardimcisi extends SQLiteOpenHelper {
    private static final int Surum = 1;
    private static String veritabaniAdi = "sutlerData";

    public VeritabaniYardimcisi(Context context) {
        super(context, veritabaniAdi, (SQLiteDatabase.CursorFactory) null, 1);
        SQLiteDatabase db = this.getWritableDatabase();

    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE alinan_sut_musteriler (musteri_no INTEGER PRIMARY KEY AUTOINCREMENT,musteri_adi TEXT ,musteri_telefon TEXT,musteri_aciklama TEXT);");
        db.execSQL("CREATE TABLE \"alinansutler\" (\n" +
                "\t\"musteri_no\"\tINTEGER,\n" +
                "\t\"musteri_adi\"\tINTEGER,\n" +
                "\t\"alinan_litre\"\tTEXT,\n" +
                "\t\"alinan_fiyat\"\tTEXT,\n" +
                "\t\"alinan_tarih\"\tTEXT,\n" +
                "\t\"alinan_aciklama\"\tTEXT,\n" +
                "\tFOREIGN KEY(\"musteri_adi\") REFERENCES \"alinan_sut_musteriler\"(\"musteri_no\")\n" +
                ")");
        db.execSQL("CREATE TABLE \"bosaltilanSutler\" (\n" +
                "\t\"musteri_bos_no\"\tINTEGER,\n" +
                "\t\"musteri_bos_adi\"\tTEXT,\n" +
                "\t\"bosaltilan_litre\"\tTEXT,\n" +
                "\t\"bosaltilan_fiyat\"\tTEXT,\n" +
                "\t\"bosaltilan_tarih\"\tTEXT,\n" +
                "\t\"bosaltilan_aciklama\"\tTEXT\n" +
                ")");
    }

    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        db.execSQL("DROP TABLE IF EXISTS alinan_sut_musteriler");
        db.execSQL("DROP TABLE IF EXISTS alinansutler");
        db.execSQL("DROP TABLE IF EXISTS bosaltilanSutler");
        onCreate(db);
    }
}
