package com.android.ogrencileruygulamasifirebase;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class Ogrenci implements Serializable {
  @Exclude
  private String key;
  private String ad;
  private String soyad;
  private String no;
  private String bolum;

  public Ogrenci() {
  }

  public Ogrenci(String ad, String soyad, String no, String bolum) {
    this.ad = ad;
    this.soyad = soyad;
    this.no = no;
    this.bolum = bolum;
  }

  public String getAd() {
    return ad;
  }

  public void setAd(String ad) {
    this.ad = ad;
  }

  public String getSoyad() {
    return soyad;
  }

  public void setSoyad(String soyad) {
    this.soyad = soyad;
  }

  public String getNo() {
    return no;
  }

  public void setNo(String no) {
    this.no = no;
  }

  public String getBolum() {
    return bolum;
  }

  public void setBolum(String bolum) {
    this.bolum = bolum;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }
}
