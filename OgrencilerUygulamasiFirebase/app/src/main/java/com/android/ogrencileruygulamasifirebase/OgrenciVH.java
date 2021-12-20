package com.android.ogrencileruygulamasifirebase;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OgrenciVH extends RecyclerView.ViewHolder {
  public TextView txt_option,ogr_no,ogr_ad,ogr_bolum;

  public OgrenciVH(@NonNull View itemView) {
    super(itemView);
    txt_option = itemView.findViewById(R.id.txt_option);
    ogr_no = itemView.findViewById(R.id.ogr_no);
    ogr_ad = itemView.findViewById(R.id.ogr_ad);
    ogr_bolum = itemView.findViewById(R.id.ogr_bolum);
  }
}
