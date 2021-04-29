package com.example.ncrypto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText et1,et2;
    Button st;
    String name,pwd;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Name = "nameKey";
    public static final String Password = "passkey";
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1 = (EditText)findViewById(R.id.et1);
        et2 = (EditText)findViewById(R.id.et2);
        st = (Button)findViewById(R.id.start);
        sp = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        st.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = et1.getText().toString();
                pwd = et2.getText().toString();
                if(name.length()==0){
                    et1.requestFocus();
                    et1.setError("Field can't be empty");
                }
                else if(!name.matches("[a-zA-Z]*")){
                    et1.requestFocus();
                    et1.setError("Only alphabets allowed.");
                }
                else if(pwd.length()==0){
                    et2.requestFocus();
                    et2.setError("Field can't be empty");
                }
                else if(pwd.length()>10){
                    et2.requestFocus();
                    et2.setError("Max. 10 characters allowed");
                }
                else {
                    SharedPreferences.Editor editor = sp.edit();
                    editor.clear().commit();
                    editor.putString(Name,name);
                    editor.putString(Password,pwd);
                    editor.commit();
                    LayoutInflater i = getLayoutInflater();
                    View l = i.inflate(R.layout.activity_custom_toast,findViewById(R.id.ct));
                    Toast tv = new Toast(getApplicationContext());
                    tv.setGravity(Gravity.CENTER_VERTICAL,0,0);
                    tv.setDuration(Toast.LENGTH_LONG);
                    tv.setView(l);
                    tv.show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Welcome: "+name,Toast.LENGTH_SHORT).show();
                            Intent ix = new Intent(getApplicationContext(), Choice.class);
                            ix.putExtra("nme", name);
                            startActivity(ix);
                        }
                    }, 3000);
                }
            }
        });
    }
}