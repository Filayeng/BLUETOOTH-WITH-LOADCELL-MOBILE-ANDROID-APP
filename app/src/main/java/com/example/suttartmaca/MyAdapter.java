package com.example.suttartmaca;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    ArrayList<String> t1 = new ArrayList<String>();ArrayList<String> t2 = new ArrayList<String>();ArrayList<String> t3 = new ArrayList<String>();ArrayList<String> t4 = new ArrayList<String>();
    int t5 = 0;
    Context context;
    public MyAdapter(Context ct, ArrayList<String> d1, ArrayList<String> d2, ArrayList<String> d3, ArrayList<String> d4,int d5){
        context = ct;
        t1 = d1;
        t2 = d2;
        t3 = d3;
        t4 = d4;
        t5 = d5;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.wordshett1,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textMusteriAdi.setText(t1.get(position));
        holder.textToplamLitre.setText(t2.get(position));
        holder.textTarihDB.setText(t3.get(position));
        holder.textGunDB.setText(t4.get(position));
        //holder.wordColor.setBackgroundColor(Color.parseColor("#00FF0A"));

        //System.out.println(t4.get(position).matches("A1"));
    }

    @Override
    public int getItemCount() {
        return t1.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textMusteriAdi,textToplamLitre,textTarihDB,textGunDB,textAciklamaKutusu;
        CardView cizgi1, cizgi2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textMusteriAdi = itemView.findViewById(R.id.textMusteriAdi);
            textToplamLitre = itemView.findViewById(R.id.textToplamLitre);
            textGunDB = itemView.findViewById(R.id.textGunDB);
            textTarihDB = itemView.findViewById(R.id.textTarihDB);
            textAciklamaKutusu = itemView.findViewById(R.id.textAciklamaKutusu);
            cizgi1 = itemView.findViewById(R.id.cizgi1);
            cizgi2 = itemView.findViewById(R.id.cizgi2);

            if(t5 == 0){
                cizgi1.setVisibility(View.INVISIBLE);
                cizgi2.setVisibility(View.INVISIBLE);
                textGunDB.setVisibility(View.INVISIBLE);
                textTarihDB.setVisibility(View.INVISIBLE);
                textAciklamaKutusu.setVisibility(View.INVISIBLE);
                textToplamLitre.setWidth(80);
            }

            else if(t5 == 1){
                cizgi1.setVisibility(View.VISIBLE);
                cizgi2.setVisibility(View.VISIBLE);
                textGunDB.setVisibility(View.VISIBLE);
                textTarihDB.setVisibility(View.VISIBLE);
                textToplamLitre.setWidth(80);
            }

            else if(t5 == 2){
                cizgi1.setVisibility(View.VISIBLE);
                cizgi2.setVisibility(View.INVISIBLE);
                textGunDB.setVisibility(View.INVISIBLE);
                textTarihDB.setVisibility(View.INVISIBLE);
                textToplamLitre.setWidth(190);
            }


        }
    }
}
