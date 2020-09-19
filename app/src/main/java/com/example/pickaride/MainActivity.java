package com.example.pickaride;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
  Button callSignUp,loginbtn;
  TextInputLayout Lphone,Lpass;
  ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_main);

        Lphone = findViewById(R.id.usernumber);
        Lpass = findViewById(R.id.password);
        progressBar = findViewById(R.id.pbar);
        progressBar.setVisibility(View.GONE);
        callSignUp = findViewById(R.id.signup);
        loginbtn = findViewById(R.id.go);

         callSignUp.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(MainActivity.this,Registration.class);
                 startActivity(intent);
                 finish();
             }
         });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressBar.setVisibility(View.VISIBLE);
                if(!valPhone() | !valPassword()){
                    return;
                }else {

                    isUser();
                }

            }
        });


    }
    public Boolean valPhone(){
        String val = Lphone.getEditText().getText().toString();
        if(val.isEmpty()){
            progressBar.setVisibility(View.GONE);
            Lphone.setError("Field cannot be empty");
            return false;}
        else{
            progressBar.setVisibility(View.GONE);
            Lphone.setError(null);
            Lphone.setErrorEnabled(false);
            return true;
        }
    }
    public Boolean valPassword(){
        String val = Lpass.getEditText().getText().toString();
        if(val.isEmpty()){
            Lpass.setError("Field cannot be empty");
            return false;}
        else{
            Lpass.setError(null);
            Lpass.setErrorEnabled(false);
            return true;
        }
    }

    public void isUser(){

     final String enteredphone = Lphone.getEditText().getText().toString().trim();
     final String enteredpass = Lpass.getEditText().getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");

        Query checkPhone = reference.orderByChild("phone").equalTo(enteredphone);

        checkPhone.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){
                    Lphone.setError(null);
                    Lphone.setErrorEnabled(false);

                    String passfromDB = dataSnapshot.child(enteredphone).child("pass").getValue(String.class);
                    if(passfromDB.equals(enteredpass)){

                        Lpass.setError(null);
                        Lpass.setErrorEnabled(false);

                        String namefromDB = dataSnapshot.child(enteredphone).child("name").getValue(String.class);
                        String phonefromDB = dataSnapshot.child(enteredphone).child("phone").getValue(String.class);
                        String mailfromDB = dataSnapshot.child(enteredphone).child("mail").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(),userProfile.class);

                        Intent intent3 = new Intent(getApplicationContext(),bike1.class);
                        intent3.putExtra("phone",phonefromDB);

                        intent.putExtra("name",namefromDB);
                        intent.putExtra("phone",phonefromDB);
                        intent.putExtra("pass",passfromDB);
                        intent.putExtra("mail",mailfromDB);

                        startActivity(intent);
                        finish();

                    }
                    else
                    {
                        Lpass.setError("Wrong Password");
                        Lpass.requestFocus();
                    }
                }else{
                    Lphone.setError("Number is not signed up");
                    Lphone.requestFocus();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
    }
}
