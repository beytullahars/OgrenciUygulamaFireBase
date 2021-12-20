package com.android.ogrencileruygulamasifirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    final EditText EditTextAd = findViewById(R.id.EditTextAd);
    final EditText EditTextSoyad = findViewById(R.id.EditTextSoyad);
    final EditText EditTextNo = findViewById(R.id.EditTextNo);
    final EditText EditTextBolum = findViewById(R.id.EditTextBolum);
    Button ButtonEkle = findViewById(R.id.ButtonEkle);

    Button btn_open = findViewById(R.id.ButtonListele);
    btn_open.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, RVActivity.class);
        startActivity(intent);
      }
    });

    DAOOgrenci dao = new DAOOgrenci();

    Ogrenci ogr_edit = (Ogrenci) getIntent().getSerializableExtra("GUNCELLE");
    if(ogr_edit != null) {
      ButtonEkle.setText("Güncelle");
      EditTextAd.setText(ogr_edit.getAd());
      EditTextSoyad.setText(ogr_edit.getSoyad());
      EditTextNo.setText(ogr_edit.getNo());
      EditTextBolum.setText(ogr_edit.getBolum());
      btn_open.setVisibility(View.GONE);
    }
    else {
      ButtonEkle.setText("EKLE");
      btn_open.setVisibility(View.VISIBLE);
    }

    ButtonEkle.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Ogrenci ogr = new Ogrenci(EditTextAd.getText().toString(), EditTextSoyad.getText().toString(), EditTextNo.getText().toString(), EditTextBolum.getText().toString());
        if(ogr_edit == null) {
          dao.add(ogr).addOnSuccessListener(suc -> {
            Toast.makeText(MainActivity.this, "Öğrenci veri tabanına eklendi", Toast.LENGTH_SHORT).show();
          }).addOnFailureListener(er -> {
            Toast.makeText(MainActivity.this, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
          });
        }
        else {
          HashMap<String, Object> hashMap = new HashMap<>();
          hashMap.put("ad",EditTextAd.getText().toString());
          hashMap.put("soyad",EditTextSoyad.getText().toString());
          hashMap.put("no",EditTextNo.getText().toString());
          hashMap.put("bolum",EditTextBolum.getText().toString());
          dao.update(ogr_edit.getKey(),hashMap).addOnSuccessListener(suc -> {
            Toast.makeText(MainActivity.this, "Öğrenci veri tabanından güncellendi", Toast.LENGTH_SHORT).show();
            finish();
          }).addOnFailureListener(er -> {
            Toast.makeText(MainActivity.this, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
          });
        }
      }
    });
  }
}