package com.example.list_karyawan;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import model.karyawan;

public class inputdataKaryawanActivity extends AppCompatActivity {

    private TextInputLayout textinput_fullname, textinput_age, textinput_address;
    private TextView inputData_title;
    private Button buttonsave;
    private karyawan objkaryawan, objkaryawannew;
    private boolean clickbutton;
    private ImageView imageback;
    private ProgressDialog progressdialog;
    private Intent intent;
    private int objposition;
    private ArrayList<karyawan> arraykaryawan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputdata_karyawan);

        inisialisasi();
        setListener();

        if(getIntent().hasExtra("karyawanarray")){

            arraykaryawan=getIntent().getParcelableArrayListExtra("karyawanarray");
            objposition=getIntent().getIntExtra("positionobj",0);
            objkaryawan=arraykaryawan.get(objposition);

            textinput_fullname.getEditText().setText(objkaryawan.getFull_name());
            textinput_age.getEditText().setText(objkaryawan.getAge());
            textinput_address.getEditText().setText(objkaryawan.getAddres());

            buttonsave.setText("Update Data");
            inputData_title.setText("Edit User");
        } else{
            inputData_title.setText("Add User");
        }
    }

    private void cekenablebutton() {
        String fullname=textinput_fullname.getEditText().getText().toString().trim();
        String age=textinput_age.getEditText().getText().toString().trim();
        String address=textinput_address.getEditText().getText().toString().trim();

        buttonsave.setEnabled(!fullname.isEmpty()&&!age.isEmpty()&&!address.isEmpty()&&clickbutton);

        if(!fullname.isEmpty()&&!age.isEmpty()&&!address.isEmpty()&&clickbutton){
            buttonsave.setBackgroundResource(R.drawable.buttonsave);
        }else{
            buttonsave.setBackgroundResource(R.drawable.buttonnosave);
        }
    }

    private void setListener() {

        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        buttonsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressdialog=new ProgressDialog(inputdataKaryawanActivity.this);
                progressdialog.show();
                progressdialog.setContentView(R.layout.dialogshow);
                progressdialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(300);
                        }catch (Exception e){
                                e.printStackTrace();
                        }
                        progressdialog.dismiss();
                    }
                }).start();

                String fullname=textinput_fullname.getEditText().getText().toString().trim();
                String age=textinput_age.getEditText().getText().toString().trim();
                String address=textinput_address.getEditText().getText().toString().trim();

                if(!getIntent().hasExtra("karyawanarray")) {

                    objkaryawan = new karyawan(fullname, age, address);

                    intent=new Intent();
                    intent.putExtra("newdata", objkaryawan);
                    setResult(200, intent);

                }else{

                    intent=new Intent(getApplicationContext(), RecyclerView_Activity.class);

                    karyawan objkaryawannew=new karyawan(fullname, age, address);

                    objposition=getIntent().getIntExtra("positionobj",0);

                    arraykaryawan.set(objposition, objkaryawannew);

                    intent.putExtra("dataEdit", arraykaryawan);
                    intent.putExtra("positionobj", objposition);
                    startActivity(intent);

                }
                finish();
            }
        });

        textinput_fullname.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String fullnameobj = textinput_fullname.getEditText().getText().toString().trim();

                if(fullnameobj.isEmpty()){
                    clickbutton=false;
                }else{
                    clickbutton=true;
                }
                cekenablebutton();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        textinput_age.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String ageobj = textinput_age.getEditText().getText().toString().trim();

                if(ageobj.isEmpty()){
                    clickbutton=false;
                }else{
                    clickbutton=true;
                }
                cekenablebutton();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        textinput_address.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String addressobj = textinput_address.getEditText().getText().toString().trim();

                if(addressobj.isEmpty()){
                    clickbutton=false;
                }else{
                    clickbutton=true;
                }
                cekenablebutton();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void inisialisasi() {
        imageback=findViewById(R.id.imageback);
        textinput_fullname=findViewById(R.id.textinput_fullname);
        textinput_age=findViewById(R.id.textinput_age);
        textinput_address=findViewById(R.id.textinput_address);
        buttonsave=findViewById(R.id.buttonsave);
        inputData_title=findViewById(R.id.inputData_title);
    }
}