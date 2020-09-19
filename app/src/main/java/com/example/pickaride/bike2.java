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

public class bike2 extends AppCompatActivity {
    TextInputLayout h2,addr,phn,quantity;
    Button con2;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_bike2);
        addr = findViewById(R.id.add);
        h2 = findViewById(R.id.hours);
        con2 =findViewById(R.id.c2);
        phn =findViewById(R.id.phone);
        quantity =findViewById(R.id.quan);

        con2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode=FirebaseDatabase.getInstance();
                reference=rootNode.getReference("bike");

                if(!valh2()|| !valPhn() || !valqn()){
                    return;
                }


                String hours = h2.getEditText().getText().toString();
                String address = addr.getEditText().getText().toString();
                String phone = phn.getEditText().getText().toString();
                String unit = quantity.getEditText().getText().toString();

                userhelper2 helperClass = new userhelper2(hours,address,address,unit);
                reference.child("livo_"+phone).setValue(helperClass);

                Intent intent = new Intent(getApplicationContext(),waiting.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public Boolean valh2() {
        String val = h2.getEditText().getText().toString();
        if (val.isEmpty()) {
            h2.setError("Field cannot be empty");
            return false;
        } else {
            h2.setError(null);
            h2.setErrorEnabled(false);
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
        String val = quantity.getEditText().getText().toString();
        if (val.isEmpty()) {
            quantity.setError("Field cannot be empty");
            return false;
        } else {
            quantity.setError(null);
            quantity.setErrorEnabled(false);
            return true;
        }
    }

}