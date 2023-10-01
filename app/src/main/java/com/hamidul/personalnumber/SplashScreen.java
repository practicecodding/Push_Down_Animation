package com.hamidul.personalnumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class SplashScreen extends AppCompatActivity {
    BroadcastReceiver broadcastReceiver;
    public boolean splash;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.WHITE);
        setContentView(R.layout.shplash_screen);

        sharedPreferences = getSharedPreferences(getString(R.string.app_name),MODE_PRIVATE);

        broadcastReceiver = new InternetCheck();
        RegisterNetwork();

        splash = sharedPreferences.getBoolean("splash",false);

        if (splash){
            startActivity(new Intent(SplashScreen.this, LogIn.class));
            finish();
        }else {
            startHandler();
        }


    }

    protected void RegisterNetwork (){
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
            registerReceiver(broadcastReceiver,new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }

    protected void UnregisterNetwork (){
        try {
            unregisterReceiver(broadcastReceiver);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        UnregisterNetwork();
        super.onDestroy();
    }

    public void startHandler() {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this,SignUp.class));
                finish();
            }
        }, 1000);

    }

}