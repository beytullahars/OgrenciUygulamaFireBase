package com.android.ogrencileruygulamasifirebase;


import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class DAOOgrenci {
  private DatabaseReference databaseReference;
  public DAOOgrenci() {
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    databaseReference = db.getReference(Ogrenci.class.getSimpleName());
  }

  public Task<Void> add(Ogrenci ogr) {
    return databaseReference.push().setValue(ogr);
  }

  public Task<Void> update(String key, HashMap<String, Object> hashMap) {
    return databaseReference.child(key).updateChildren(hashMap);
  }

  public Task<Void> remove(String key) {
    return databaseReference.child(key).removeValue();
  }

  public Query get() {
    return databaseReference.orderByKey();
  }
}
