package com.example.list_karyawan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import model.karyawan;

public class karyawanRVAdapter extends RecyclerView.Adapter<karyawanRVAdapter.karyawanHolder> {

    private ArrayList<karyawan> arraylistkaryawan;

    private onCardListener listener;
    public karyawanRVAdapter(ArrayList<karyawan> Listkaryawan, onCardListener listener){
        this.arraylistkaryawan=Listkaryawan;
        this.listener=listener;
    }

    @Override
    public karyawanRVAdapter.karyawanHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutinflater=LayoutInflater.from(parent.getContext());

        View view=layoutinflater.inflate(R.layout.list_card_karyawan, parent, false);
        return new karyawanHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull karyawanRVAdapter.karyawanHolder holder, int position) {
        holder.cardView_full_name.setText(String.valueOf(arraylistkaryawan.get(position).getFull_name()));
        holder.cardView_age.setText(String.valueOf(arraylistkaryawan.get(position).getAge())+" years old" );
        holder.cardView_address.setText(String.valueOf(arraylistkaryawan.get(position).getAddres()));


    }

    @Override
    public int getItemCount() {
        return arraylistkaryawan.size();
    }

    public class karyawanHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView cardView_full_name, cardView_age, cardView_address;

        public karyawanHolder(@NonNull View itemView) {
            super(itemView);

            cardView_full_name=itemView.findViewById((R.id.cardView_full_name));
            cardView_age=itemView.findViewById((R.id.cardView_age));
            cardView_address=itemView.findViewById((R.id.cardView_address));
            itemView.setOnClickListener(this);




        }

        @Override
        public void onClick(View v) {
            listener.onButtonDetailClick(getAdapterPosition());
        }

    }


}
