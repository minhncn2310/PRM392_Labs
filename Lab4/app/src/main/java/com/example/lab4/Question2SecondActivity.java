package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class Question2SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question2_second);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("AAA", "OnStart Second");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("AAA", "OnRestart Second");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("AAA", "OnResume Second");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("AAA", "OnPause Second");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("AAA", "OnStop Second");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("AAA", "OnDestroy Second");
    }
}