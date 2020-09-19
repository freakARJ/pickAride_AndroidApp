package com.example.pickaride;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity {
    Button reg,callLogin;
    TextInputLayout regName,regPhone , regMail, regPass;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_registration);

        regName = findViewById(R.id.name);
        regPhone = findViewById(R.id.phone);
        regMail = findViewById(R.id.mail);
        regPass = findViewById(R.id.pass);
        callLogin=findViewById(R.id.calllogin);
        reg =findViewById(R.id.reg);

        callLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Registration.this,MainActivity.class);
                startActivity(intent);
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode=FirebaseDatabase.getInstance();
                reference=rootNode.getReference("users");

                if(!valName() | !valPhone() | !valMail()  | !valPass() ) {
                return;
                }

                String name = regName.getEditText().getText().toString();
                String phone = regPhone.getEditText().getText().toString();
                String mail = regMail.getEditText().getText().toString();
                String pass = regPass.getEditText().getText().toString();

             //
                userHelper helperClass = new userHelper(name,phone,mail,pass);
                reference.child(phone).setValue(helperClass);
                Intent intent= new Intent(getApplicationContext(),verifyPhone.class);
                   intent.putExtra("phone",phone);
                   startActivity(intent);
                   finish();

            }
        });
    }

    public Boolean valName(){
        String val = regName.getEditText().getText().toString();
        String nameVal = ".{4,}";
        if(val.isEmpty()){
            regName.setError("Field cannot be empty");
            return false;}
        else if(!val.matches(nameVal)){
            regPhone.setError("Name too Short");
            return false;

        }

            else{
                regName.setError(null);
                regName.setErrorEnabled(false);
                return true;
            }
        }
    public Boolean valPhone(){
        String val = regPhone.getEditText().getText().toString();
        String phnVal = ".{11,}";
        if(val.isEmpty()){
            regPhone.setError("Field cannot be empty");
            return false;}
        else if(!val.matches(phnVal)){
            regPhone.setError("MUST BE 11 DIGITS");
            return false;

        }
        else{
            regPhone.setError(null);
            regName.setErrorEnabled(false);
            return true;
        }
    }
    public Boolean valMail(){
        String val = regMail.getEditText().getText().toString();
        String mailVal = ".{8,}";
        if(val.isEmpty()){
            regMail.setError("Field cannot be empty");
            return false;}
        else if(!val.matches(mailVal)){
            regPhone.setError("Invalid Mail");
            return false;
        }
        else{
            regMail.setError(null);
            regName.setErrorEnabled(false);
            return true;
        }
    }
    public Boolean valPass(){
        String val = regPass.getEditText().getText().toString();
        String passVal = ".{4,}";
        if(val.isEmpty()){
            regPass.setError("Field cannot be empty");
            return false;}
        else if(!val.matches(passVal)){
            regPass.setError("MINIMUM 4 DIGIT");
            return false;

        }
        else{
            regPass.setError(null);
            regName.setErrorEnabled(false);
            return true;
        }
    }
    }

