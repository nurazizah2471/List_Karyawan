package com.example.list_karyawan;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import model.karyawan;

public class inputdataKaryawanActivity extends AppCompatActivity {

    private TextInputLayout textinput_fullname, textinput_age, textinput_address;
    private Button buttonsave;
    private karyawan objkaryawan;
    private boolean clickbutton;
    private ImageView imageback;
    private ProgressDialog progressdialog;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputdata_karyawan);

        inisialisasi();
        setListener();

        if(getIntent().hasExtra("karyawan")){
            objkaryawan=intent.getParcelableExtra("karyawan");
            textinput_fullname.getEditText().setText(objkaryawan.getFull_name());
            textinput_age.getEditText().setText(objkaryawan.getAge());
            textinput_address.getEditText().setText(objkaryawan.getAddres());
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        progressdialog.dismiss();
    }

    private void setListener() {
        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                setResult(100, intent);
                finish();
            }
        });

        buttonsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fullname=textinput_fullname.getEditText().getText().toString().trim();
                String age=textinput_age.getEditText().getText().toString().trim();
                String address=textinput_address.getEditText().getText().toString().trim();


                progressdialog=new ProgressDialog(inputdataKaryawanActivity.this);
                progressdialog.show();
                progressdialog.setContentView(R.layout.dialogshow);

                progressdialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


                if(!getIntent().hasExtra("karyawanobj")) {
                    objkaryawan = new karyawan(fullname, age, address);
                    intent=new Intent(getApplicationContext(), RecyclerView_Activity.class);
                    intent.putExtra("newdata", objkaryawan);
                    setResult(200, intent);
                    finish();
                }else{

                    objkaryawan.setFull_name(fullname);
                    objkaryawan.setAge(age);
                    objkaryawan.setAddres(address);


                }
            }
        });


        textinput_fullname.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String fullnameobj = textinput_fullname.getEditText().getText().toString().trim();

                if(fullnameobj.length()<0){
                    textinput_fullname.setError("Silakan Diisi!");
                    clickbutton=false;
                }else{
                    textinput_fullname.setError("");
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

                if(ageobj.length()<0){
                    textinput_age.setError("Silakan Diisi!");
                    clickbutton=false;
                }else{
                    textinput_age.setError("");
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

                if(addressobj.length()<0){
                    textinput_address.setError("Silakan Diisi!");
                    clickbutton=false;
                }else{
                    textinput_address.setError("");
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
    }
}