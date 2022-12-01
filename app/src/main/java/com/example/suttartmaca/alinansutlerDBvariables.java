package com.example.suttartmaca;

public class alinansutlerDBvariables {
    private int musteri_adi;
    private int musteri_no;
    private String alinan_fiyat;
    private String alinan_litre;
    private String alinan_tarih;
    private String alinan_aciklama;
    private musterilerDBvariables musteri_adi_db;



    public alinansutlerDBvariables(int musteri_adi, int musteri_no, String alinan_fiyat, String alinan_litre, String alinan_tarih, String alinan_aciklama, musterilerDBvariables musteri_adi_db) {
        this.musteri_adi = musteri_adi;
        this.alinan_fiyat = alinan_fiyat;
        this.alinan_litre = alinan_litre;
        this.alinan_tarih = alinan_tarih;
        this.alinan_aciklama = alinan_aciklama;
        this.musteri_adi_db = musteri_adi_db;

    }

    public int getMusteri_adi() {
        return musteri_adi;
    }

    public void setMusteri_adi(int musteri_adi) {
        this.musteri_adi = musteri_adi;
    }

    public int getMusteri_no() {
        return musteri_no;
    }

    public void setMusteri_no(int musteri_no) {
        this.musteri_no = musteri_no;
    }

    public String getAlinan_fiyat() {
        return alinan_fiyat;
    }

    public void setAlinan_fiyat(String alinan_fiyat) {
        this.alinan_fiyat = alinan_fiyat;
    }

    public String getAlinan_litre() {
        return alinan_litre;
    }

    public void setAlinan_litre(String alinan_litre) {
        this.alinan_litre = alinan_litre;
    }

    public String getAlinan_tarih() {
        return alinan_tarih;
    }

    public void setAlinan_tarih(String alinan_tarih) {
        this.alinan_tarih = alinan_tarih;
    }

    public String getAlinan_aciklama() {
        return alinan_aciklama;
    }

    public void setAlinan_aciklama(String alinan_aciklama) {this.alinan_aciklama = alinan_aciklama;}

    public musterilerDBvariables getMusteri_adi_db() {return musteri_adi_db;}

    public void setMusteri_adi_db(musterilerDBvariables musteri_adi_db) {this.musteri_adi_db = musteri_adi_db;}


}
