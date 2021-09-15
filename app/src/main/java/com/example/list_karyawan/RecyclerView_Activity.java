package com.example.list_karyawan;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import model.karyawan;

public class RecyclerView_Activity extends AppCompatActivity implements onCardListener {

    private RecyclerView itemmodel_rycyclerView;
    private ArrayList<karyawan> datakaryawan;
    private karyawanRVAdapter karyawanadapter;
    private karyawan objkaryawan;
    private FloatingActionButton buttonaddfloating;
    private TextView textitemnodata;
    private boolean cekclick = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        inisialisasi();
        setRycycleView();

        textitemnodata.setText("No Data");

        setListener();
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1){
            if(resultCode==200){
                objkaryawan=data.getParcelableExtra("newdata");
                datakaryawan.add(objkaryawan);
                karyawanadapter.notifyDataSetChanged();
                if(datakaryawan.size()==0){
                    textitemnodata.setText("No Data");
                }else{
                    textitemnodata.setText("");
                }

            }
        }
    }

    private void setListener() {
        buttonaddfloating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), inputdataKaryawanActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    private void setRycycleView() {
        RecyclerView.LayoutManager layout=new LinearLayoutManager(getBaseContext());
        itemmodel_rycyclerView.setLayoutManager(layout);
        itemmodel_rycyclerView.setAdapter(karyawanadapter);
    }

    private void inisialisasi() {
        buttonaddfloating =findViewById(R.id.buttonaddfloating);
        textitemnodata=findViewById(R.id.textitemnodata);
        itemmodel_rycyclerView=findViewById(R.id.itemmodel_rycyclerView);

        datakaryawan=new ArrayList<karyawan>();
        karyawanadapter=new karyawanRVAdapter(datakaryawan, this);
    }

    @Override
    public void onButtonDetailClick(View v, int position) {
        Intent intent=new Intent(getApplicationContext(), detailActivity.class);

        intent.putExtra("karyawanobj", datakaryawan.get(position));
        intent.putExtra("indexdetail", position);

       startActivity(intent);
    }
}