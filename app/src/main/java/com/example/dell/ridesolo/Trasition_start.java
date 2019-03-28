package com.example.dell.ridesolo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import pl.droidsonroids.gif.GifImageView;

public class Trasition_start extends AppCompatActivity {
 Animation frombottom2,frombottom,fromleft,fromtop;
 ImageView iv1,iv2,iv3;
 GifImageView gif;
    private static int SPLASH_TIME_OUT = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trasition_start);
        frombottom= AnimationUtils.loadAnimation(this,R.anim.frombottom);
       fromleft= AnimationUtils.loadAnimation(this,R.anim.fromleft);
        fromtop=AnimationUtils.loadAnimation(this,R.anim.fromtop);
        frombottom2= AnimationUtils.loadAnimation(this,R.anim.frombottom2);
        iv1=(ImageView)findViewById(R.id.imageView);
       iv1.setAnimation(fromleft);
        iv2=(ImageView)findViewById(R.id.imageView2);
        iv2.setAnimation(frombottom);
        iv3=(ImageView)findViewById(R.id.imageView3);
        iv3.setAnimation(fromtop);
       // gif=(GifImageView)findViewById(R.id.gifImageView);
//        gif.setAnimation(frombottom2);

        //startActivity(new Intent(Trasition_start.this,LoginPage.class));

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent homeIntent = new Intent(Trasition_start.this, LoginPage.class);
                startActivity(homeIntent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }

/*
    @Override
    protected void onSwipeRight() {

        startActivity(new Intent(Trasition_start.this,LoginPage.class));
        overridePendingTransition(R.anim.go_right,R.anim.go_down);



    }

    @Override
    protected void onSwipeLeft() {
        startActivity(new Intent(Trasition_start.this,LoginPage.class));
        overridePendingTransition(R.anim.go_right,R.anim.go_down);

    }
*/
}

