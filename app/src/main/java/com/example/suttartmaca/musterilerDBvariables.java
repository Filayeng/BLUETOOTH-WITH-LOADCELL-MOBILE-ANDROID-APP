package com.example.suttartmaca;

public class musterilerDBvariables {
    private String musteri_aciklama;
    private String musteri_adsoyad;
    private int musteri_id;
    private String musteri_tel;

    public musterilerDBvariables(int musteri_id2, String musteri_adsoyad2, String musteri_tel2, String musteri_aciklama2) {
        this.musteri_id = musteri_id2;
        this.musteri_adsoyad = musteri_adsoyad2;
        this.musteri_tel = musteri_tel2;
        this.musteri_aciklama = musteri_aciklama2;
    }

    public int getMusteri_id() {
        return this.musteri_id;
    }

    public void setMusteri_id(int musteri_id2) {
        this.musteri_id = musteri_id2;
    }

    public String getMusteri_adsoyad() {
        return this.musteri_adsoyad;
    }

    public void setMusteri_adsoyad(String musteri_adsoyad2) {this.musteri_adsoyad = musteri_adsoyad2;}

    public String getMusteri_tel() {
        return this.musteri_tel;
    }

    public void setMusteri_tel(String musteri_tel2) {
        this.musteri_tel = musteri_tel2;
    }

    public String getMusteri_aciklama() {
        return this.musteri_aciklama;
    }

    public void setMusteri_aciklama(String musteri_aciklama2) {this.musteri_aciklama = musteri_aciklama2;}
}
