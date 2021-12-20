package com.android.ogrencileruygulamasifirebase;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
  private Context context;
  ArrayList<Ogrenci> list = new ArrayList<>();

  public RVAdapter(Context ctx) {
    this.context = ctx;
  }

  public void setItems(ArrayList<Ogrenci> ogr) {
    list.addAll(ogr);
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.layout_item,parent,false);
    return new OgrenciVH(view);
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    OgrenciVH vh = (OgrenciVH) holder;
    Ogrenci ogr = list.get(position);
    vh.ogr_no.setText(ogr.getNo());
    vh.ogr_ad.setText(ogr.getAd()+" "+ogr.getSoyad());
    vh.ogr_bolum.setText(ogr.getBolum());

    vh.txt_option.setOnClickListener(v -> {
      PopupMenu popupMenu = new PopupMenu(context,vh.txt_option);
      popupMenu.inflate(R.menu.option_menu);
      popupMenu.setOnMenuItemClickListener(item -> {
        switch (item.getItemId()) {
          case R.id.menu_edit:
            Intent intent = new Intent(context,MainActivity.class);
            intent.putExtra("GUNCELLE", ogr);
            context.startActivity(intent);
            break;
          case R.id.menu_remove:
            DAOOgrenci dao = new DAOOgrenci();
            dao.remove(ogr.getKey()).addOnSuccessListener(suc -> {
              Toast.makeText(context, "Öğrenci veri tabanınından silindi!", Toast.LENGTH_SHORT).show();
              notifyItemRemoved(position);
            }).addOnFailureListener(er -> {
              Toast.makeText(context, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
            });
            break;
        }
        return false;
      });
      popupMenu.show();
    });
  }

  @Override
  public int getItemCount() {
    return list.size();
  }
}
