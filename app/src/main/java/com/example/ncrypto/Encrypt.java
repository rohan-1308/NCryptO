package com.example.ncrypto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Encrypt extends AppCompatActivity {
    Button rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypt);
        Intent i = getIntent();
        String code = i.getStringExtra("Code");
        TextView tv3 = findViewById(R.id.tv3);
        tv3.setText(code);
        rate = findViewById(R.id.rate);
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RateUs.class);
                startActivity(intent);
            }
        });
    }
}