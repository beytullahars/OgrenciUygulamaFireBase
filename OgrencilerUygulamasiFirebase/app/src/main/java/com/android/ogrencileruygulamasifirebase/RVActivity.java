package com.android.ogrencileruygulamasifirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RVActivity extends AppCompatActivity {
  RecyclerView recyclerView;
  RVAdapter adapter;
  DAOOgrenci dao;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_rvactivity);

    recyclerView = findViewById(R.id.rv);
    recyclerView.setHasFixedSize(true);
    LinearLayoutManager manager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(manager);
    adapter = new RVAdapter(this);
    recyclerView.setAdapter(adapter);
    dao = new DAOOgrenci();
    loadData();
  }

  private void loadData() {
    dao.get().addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot snapshot) {
        ArrayList<Ogrenci> ogrenciler = new ArrayList<>();
        for(DataSnapshot data: snapshot.getChildren()) {
          Ogrenci ogr = data.getValue(Ogrenci.class);
          ogr.setKey(data.getKey());
          ogrenciler.add(ogr);
        }
        adapter.setItems(ogrenciler);
        adapter.notifyDataSetChanged();
      }

      @Override
      public void onCancelled(@NonNull DatabaseError error) {

      }
    });
  }
}