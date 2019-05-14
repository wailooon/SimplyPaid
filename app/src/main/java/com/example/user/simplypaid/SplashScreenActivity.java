package com.example.user.simplypaid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread myThread = new Thread(){
            @Override
            public void run() {
                try{
                    sleep(6000);
                    Intent myIntent = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(myIntent);
                    finish();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
            myThread.start();
    }
}
