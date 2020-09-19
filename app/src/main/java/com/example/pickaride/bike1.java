package com.example.pickaride;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.BreakIterator;

public class bike1 extends AppCompatActivity {
    TextInputLayout h1,addr,phn,quant;
    Button con1;

    FirebaseDatabase rootNode;
    DatabaseReference reference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_bike1);

        h1 = findViewById(R.id.hours);
        addr = findViewById(R.id.add);
        con1 =findViewById(R.id.c1);
        phn =findViewById(R.id.phone);
        quant =findViewById(R.id.quan);

        con1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode=FirebaseDatabase.getInstance();
                reference=rootNode.getReference("bike");

                if(!valh1()|| !valPhn()|| !valqn()) {
                    return;
                }

                String hours = h1.getEditText().getText().toString();
                String address = addr.getEditText().getText().toString();
                String phone = phn.getEditText().getText().toString();
                String unit = quant.getEditText().getText().toString();

                userhelper2 helperClass = new userhelper2(hours,address,phone,unit);
                reference.child("fzsV2_"+phone).setValue(helperClass);

                Intent intent = new Intent(getApplicationContext(),waiting.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public Boolean valh1() {
        String val = h1.getEditText().getText().toString();
        if (val.isEmpty()) {
            h1.setError("Field cannot be empty");
            return false;
        } else {
            h1.setError(null);
            h1.setErrorEnabled(false);
            return true;
        }
    }
    public Boolean valPhn(){
        String val = phn.getEditText().getText().toString();
        String phnVal = ".{11,}";
        if(val.isEmpty()){
            phn.setError("Field cannot be empty");
            return false;}
        else if(!val.matches(phnVal)){
            phn.setError("MUST BE 11 DIGITS");
            return false;

        }
        else{
            phn.setError(null);
            phn.setErrorEnabled(false);
            return true;
        }
    }
    public Boolean valqn() {
        String val = quant.getEditText().getText().toString();
        if (val.isEmpty()) {
            quant.setError("Field cannot be empty");
            return false;
        } else {
            quant.setError(null);
            quant.setErrorEnabled(false);
            return true;
        }
    }

    }