package com.example.adibf.detrening;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class Daftar extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private Button btnDaftar,btnLogin;
    private ProgressBar progressBar;
    private FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        auth = FirebaseAuth.getInstance();

        inputEmail = (EditText) findViewById(R.id.emailDaftar);
        inputPassword = (EditText) findViewById(R.id.passwordDaftar);
        btnDaftar = (Button) findViewById(R.id.Daftar);
        btnLogin = (Button) findViewById(R.id.Login);
        progressBar = (ProgressBar) findViewById(R.id.progress);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(), "Masukkan email anda", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(), "Masukkan password anda", Toast.LENGTH_SHORT).show();
                    return;

                }

                progressBar.setVisibility(View.VISIBLE);

                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Daftar.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(Daftar.this, "createUserWithEmail:onComplete:" +task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);

                                if (!task.isSuccessful()){
                                    Toast.makeText(Daftar.this, "Gagal" +task.getException(), Toast.LENGTH_SHORT).show();
                                } else {
                                    startActivity(new Intent(Daftar.this, Beranda.class));
                                    finish();
                                }

                            }
                        });

            }
        });


    }

    @Override
    protected void onResume(){
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}
