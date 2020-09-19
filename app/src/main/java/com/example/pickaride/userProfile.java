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

public class userProfile extends AppCompatActivity {

    TextView pname,pphone,pmail,ppass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_profile);

        pname=findViewById(R.id.name);
        pphone=findViewById(R.id.phone);
        pmail=findViewById(R.id.mail);
        ppass=findViewById(R.id.pass);

        showuserdata();

        Button ext = (Button) findViewById(R.id.exit);
        ext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });

        Button rnt = (Button) findViewById(R.id.rent);
        rnt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),rent.class);
            startActivity(intent);
                finish();
            }
        });

    }

    private void showuserdata() {
        Intent intent = getIntent();
        String user_name = intent.getStringExtra("name");
        String user_phone = intent.getStringExtra("phone");
        String user_mail = intent.getStringExtra("mail");
        String user_pass = intent.getStringExtra("pass");


        pname.setText(user_name);
        pphone.setText(user_phone);
        pmail.setText(user_mail);
        ppass.setText(user_pass);
    }


}