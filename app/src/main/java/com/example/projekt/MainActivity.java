package com.example.projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //hooks
    View first, second, third, fourth, fifth;
    TextView created, appName;
    ImageView appLgoo;

    //Animacje
    Animation topAnimation, bottomAnimation, middleAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getSupportActionBar() != null) getSupportActionBar().hide(); //wylaczenie action bara

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //usuniecie gornego menu

        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top);
        middleAnimation = AnimationUtils.loadAnimation(this, R.anim.middle);
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom);

        //hooks
        first = findViewById(R.id.first);
        second = findViewById(R.id.second);
        third = findViewById(R.id.third);
        fourth = findViewById(R.id.fourth);
        fifth = findViewById(R.id.fifth);

        created = findViewById(R.id.created);
        appName = findViewById(R.id.appName);
        appLgoo = findViewById(R.id.appLogo);


        //animacje
        first.setAnimation(topAnimation);
        second.setAnimation(topAnimation);
        third.setAnimation(topAnimation);
        fourth.setAnimation(topAnimation);
        fifth.setAnimation(topAnimation);

        created.setAnimation(bottomAnimation);

        appName.setAnimation(middleAnimation);
        appLgoo.setAnimation(middleAnimation);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);

    }
}