package com.example.user.simplypaid;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    private EditText inputEmail,inputPassword;
    private Button LoginBtn,SignupBtn;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener lAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        if(auth.getCurrentUser() != null){
            startActivity(new Intent(LoginActivity.this,LoginActivity.class));
            finish();
        }

        //declare buttons and edit text in oncreate
        inputEmail = (EditText) findViewById(R.id.email_editText);
        inputPassword = (EditText) findViewById(R.id.password_editText);
        LoginBtn = (Button) findViewById(R.id.loginBtn);
        SignupBtn = (Button) findViewById(R.id.signupBtn);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        //Get Firebase auth instance;
        auth = FirebaseAuth.getInstance();

        SignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,OnboardActivity.class)); //change later
            }
        });

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();

                progressBar.setVisibility(View.VISIBLE);

                auth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if(task.isSuccessful()){
                                    Toast.makeText(getApplicationContext(), "Successfully login", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(LoginActivity.this,OnboardActivity.class));
                                    finish();
                                }else{
                                    Toast.makeText(getApplicationContext(), "Login failed" + task.getException(), Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });
    }

    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
