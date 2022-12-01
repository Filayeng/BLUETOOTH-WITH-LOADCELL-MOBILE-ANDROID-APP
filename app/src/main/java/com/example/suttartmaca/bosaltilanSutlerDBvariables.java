package com.example.suttartmaca;

public class bosaltilanSutlerDBvariables {
    private int musteri_bos_no;
    private String musteri_bos_adi;
    private String bosaltilan_litre;
    private String bosaltilan_fiyat;
    private String bosaltilan_tarih;
    private String bosaltilan_aciklama;

    public bosaltilanSutlerDBvariables(int musteri_bos_no, String musteri_bos_adi, String bosaltilan_litre, String bosaltilan_fiyat, String bosaltilan_tarih, String bosaltilan_aciklama) {
        this.musteri_bos_no = musteri_bos_no;
        this.musteri_bos_adi = musteri_bos_adi;
        this.bosaltilan_litre = bosaltilan_litre;
        this.bosaltilan_fiyat = bosaltilan_fiyat;
        this.bosaltilan_tarih = bosaltilan_tarih;
        this.bosaltilan_aciklama = bosaltilan_aciklama;
    }

    public int getMusteri_bos_no() {return musteri_bos_no;}

    public void setMusteri_bos_no(int musteri_bos_no) {this.musteri_bos_no = musteri_bos_no;}

    public String getMusteri_bos_adi() {return musteri_bos_adi;}

    public void setMusteri_bos_adi(String musteri_bos_adi) {this.musteri_bos_adi = musteri_bos_adi;}

    public String getBosaltilan_litre() {return bosaltilan_litre;}

    public void setBosaltilan_litre(String bosaltilan_litre) {this.bosaltilan_litre = bosaltilan_litre;}

    public String getBosaltilan_fiyat() {return bosaltilan_fiyat;}

    public void setBosaltilan_fiyat(String bosaltilan_fiyat) {this.bosaltilan_fiyat = bosaltilan_fiyat;}

    public String getBosaltilan_tarih() {return bosaltilan_tarih;}

    public void setBosaltilan_tarih(String bosaltilan_tarih) {this.bosaltilan_tarih = bosaltilan_tarih;}

    public String getBosaltilan_aciklama() {return bosaltilan_aciklama;}

    public void setBosaltilan_aciklama(String bosaltilan_aciklama) {this.bosaltilan_aciklama = bosaltilan_aciklama;}

}
