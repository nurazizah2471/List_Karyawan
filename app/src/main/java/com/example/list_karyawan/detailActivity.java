package com.example.list_karyawan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import model.karyawan;

public class detailActivity extends AppCompatActivity {

    private TextView fullname_detail, age_detail, address_detail;
    private Intent intent;
    private karyawan objkaryawan;
    private int objposition;
    private ArrayList<karyawan> arraykaryawan;
    private static final String TAG = "detailActivity";
    private ImageView detail_buttonedit, detail_buttondelete, imageback_detailActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        inisialisasi();

        arraykaryawan=getIntent().getParcelableArrayListExtra("karyawanarray");
        objposition=getIntent().getIntExtra("objposition",0);

        objkaryawan=arraykaryawan.get(objposition);

        Log.d(TAG, objkaryawan.getFull_name());

        fullname_detail.setText(objkaryawan.getFull_name());
        age_detail.setText(objkaryawan.getAge()+" years old");
        address_detail.setText(objkaryawan.getAddres());

        setListener();
    }

    private void setListener() {

        detail_buttonedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                objposition=getIntent().getIntExtra("objposition",0);
                intent=new Intent(getApplicationContext(), inputdataKaryawanActivity.class);
                intent.putExtra("positionobj", objposition);
                intent.putExtra("karyawanarray", arraykaryawan);

                startActivity(intent);
            }
        });

        detail_buttondelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alert = new AlertDialog.Builder(detailActivity.this);
                alert.setTitle("Konfirmasi");
                alert.setIcon(R.drawable.ic_android_black_24dp);
                alert.setMessage("Are you sure to delete "+objkaryawan.getFull_name()+" data?");

                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        intent=new Intent();
                        intent.putExtra("positionobj", objposition);

                        setResult(500,intent);
                        finish();
                    }
                });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

                AlertDialog dialog = alert.create();
                dialog.show();
            }
        });

        imageback_detailActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void inisialisasi() {
        fullname_detail=findViewById(R.id.fullname_detail);
        age_detail=findViewById(R.id.age_detail);
        address_detail=findViewById(R.id.address_detail);
        detail_buttonedit=findViewById(R.id.detail_buttonedit);
        detail_buttondelete=findViewById(R.id.detail_buttondelete);
        imageback_detailActivity=findViewById(R.id.imageback_detailActivity);
    }
}