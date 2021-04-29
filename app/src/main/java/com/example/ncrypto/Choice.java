package com.example.ncrypto;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SuppressWarnings("ALL")
public class Choice extends AppCompatActivity {
    RadioButton cs,sha256,sha512,md5;
    RadioGroup choice;
    Button start;
    EditText etv2;
    String str="",name,hashtext="",rex;
    ProgressDialog progressBar;
    int pbs=0;
    Handler h = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        TextView nm = (TextView)findViewById(R.id.dname);
        Intent i = getIntent();
        name = i.getStringExtra("nme");
        nm.setText("Name: "+name);
        etv2 = findViewById(R.id.etv2);
        cs = findViewById(R.id.rb1);
        sha256 = findViewById(R.id.rb2);
        sha512 = findViewById(R.id.rb3);
        md5 = findViewById(R.id.rb4);
        choice = findViewById(R.id.choicegrp);
        start = findViewById(R.id.st);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (choice.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getApplicationContext(), "Please select one choice", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    str = etv2.getText().toString();
                    if (str.length() == 0) {
                        etv2.requestFocus();
                        etv2.setError("Field can't be empty");
                    }
                    else {
                        if (choice.getCheckedRadioButtonId() == cs.getId()) {
                            StringBuffer result = new StringBuffer();
                            for (int i = 0; i < str.length(); i++) {
                                if (Character.isUpperCase(str.charAt(i))) {
                                    char ch = (char) (((int) str.charAt(i) + 3 - 65) % 26 + 65);
                                    result.append(ch);
                                } else {
                                    char ch = (char) (((int) str.charAt(i) + 3 - 97) % 26 + 97);
                                    result.append(ch);
                                }
                            }
                            rex = result.toString();
                        }
                        if (choice.getCheckedRadioButtonId() == sha256.getId()) {
                            try {
                                MessageDigest md = MessageDigest.getInstance("SHA-256"); // Static getInstance method is called with hashing SHA-256
                                byte[] messageDigest = md.digest(str.getBytes()); // digest() method is called to calculate message digest of an input digest() return array of byte
                                BigInteger no = new BigInteger(1, messageDigest); // Convert byte array into signum representation
                                StringBuilder hashtext = new StringBuilder(no.toString(16)); // Convert message digest into hex value
                                while (hashtext.length() < 32) {
                                    hashtext.insert(0, '0');
                                }
                                rex = hashtext.toString();
                            }
                            // For specifying wrong message digest algorithms
                            catch (NoSuchAlgorithmException e) {
                                throw new RuntimeException(e);
                            }

                        }
                        if (choice.getCheckedRadioButtonId() == sha512.getId()) {
                            try {
                                MessageDigest md = MessageDigest.getInstance("SHA-512"); // Static getInstance method is called with hashing MD5
                                byte[] messageDigest = md.digest(str.getBytes()); // digest() method is called to calculate message digest of an input digest() return array of byte
                                BigInteger no = new BigInteger(1, messageDigest); // Convert byte array into signum representation
                                hashtext = no.toString(16); // Convert message digest into hex value
                                while (hashtext.length() < 32) {
                                    hashtext = "0" + hashtext;
                                }
                                rex = hashtext;
                            }
                            // For specifying wrong message digest algorithms
                            catch (NoSuchAlgorithmException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        if (choice.getCheckedRadioButtonId() == md5.getId()) {
                            try {
                                MessageDigest md = MessageDigest.getInstance("MD5"); // Static getInstance method is called with hashing MD5
                                byte[] messageDigest = md.digest(str.getBytes()); // digest() method is called to calculate message digest of an input digest() return array of byte
                                BigInteger no = new BigInteger(1, messageDigest); // Convert byte array into signum representation
                                hashtext = no.toString(16); // Convert message digest into hex value
                                while (hashtext.length() < 32) {
                                    hashtext = "0" + hashtext;
                                }
                                rex = hashtext;
                            }
                            // For specifying wrong message digest algorithms
                            catch (NoSuchAlgorithmException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                    progressBar = new ProgressDialog(v.getContext());
                    progressBar.setCancelable(true);                                //If you click outside pop-up, progress bar will terminate.
                    progressBar.setMessage("Text is getting encrypted.");          //Printing message on dialogue box
                    progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);  //Type of progress bar
                    progressBar.setProgress(0);
                    progressBar.setMax(100);
                    progressBar.show();                                             //Display Progress
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (pbs < 100) {
                                pbs += 1;
                                try {
                                    Thread.sleep(5);
                                } catch (InterruptedException a) {
                                    a.printStackTrace();
                                }
                                h.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        progressBar.setProgress(pbs);
                                        }
                                });
                            }
                            if (pbs >= 100) {
                                try {
                                    Thread.sleep(500);
                                }
                                catch (InterruptedException a) {
                                }
                                progressBar.dismiss();
                                Intent i = new Intent(getApplicationContext(), Encrypt.class);
                                i.putExtra("Code", rex);
                                startActivity(i);
                            }
                        }
                    });
                    t.start();
                }
            }
        });
    }
}