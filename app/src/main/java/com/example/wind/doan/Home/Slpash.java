package com.example.wind.doan.Home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.wind.doan.R;

public class Slpash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slpash);

        Thread thread = new Thread(){
            @Override
            public void run (){
                try {
                    sleep(3000);
                    Intent intent = new Intent(getApplicationContext(), HomeMain.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
}
