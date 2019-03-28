package com.example.dell.ridesolo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.example.dell.ridesolo.Booking_pg;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Other extends AppCompatActivity {
Button b1,b2;
TextView t1,t2;
double f1;
Float f2;
String res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.Other));
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        b1= (Button)findViewById(R.id.pay_but);
        b2= (Button)findViewById(R.id.button2);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Booking_pg.class));
            }
        });
        t1= (TextView)findViewById(R.id.textView2);
       t2=(TextView)findViewById(R.id.textView3) ;
        //Toast.makeText(this, ""+s1, Toast.LENGTH_SHORT).show();

        Double r1 =(Math.random()*((1500-400)+1))+400;
      //  Random rand =new Random();
       // int r1=rand.nextInt(400);

        Intent intent = getIntent();
        String result = intent.getStringExtra("MY_KEY");
        f1=(Float.valueOf(result)/r1);

        t1.setText("Distance : "+f1+" KM");


        f2= Float.valueOf((float) f1)*8;
        res= Float.toString(f2);

        t2.setText("Amount   :RS "+f2.toString().trim());
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Other.this, paytm.class);
                i.putExtra("My_pay",res);
                startActivity(i);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Other.this,detail.class));
            }
        });
    }

    }
