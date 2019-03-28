package com.example.dell.ridesolo;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import in.shadowfax.proswipebutton.ProSwipeButton;

public class detail extends AppCompatActivity {
    private FirebaseFirestore db2;
    Animation fromleft2;
    FirebaseAuth mAuth;
TextView t9,t8,t10,t11;
ImageView iv6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mAuth = FirebaseAuth.getInstance();
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.Booking_page));
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Other.class));
            }
        });

        t9=(TextView)findViewById(R.id.textView9);
        t8=(TextView)findViewById(R.id.textView8);
        t10=(TextView)findViewById(R.id.textView10);
        t11=(TextView)findViewById(R.id.textView11);
        db2 = FirebaseFirestore.getInstance();
        DocumentReference dr = db2.collection("Drivers").document(mAuth.getCurrentUser().getEmail());
        dr.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
           if (task.isSuccessful())
           {
               DocumentSnapshot doc= task.getResult();
               if(doc.exists()) {
                   t8.setText("DRIVER Name     :"+doc.getData().get("Driver Name"));
                   t9.setText("DRIVER ID       :"+doc.getData().get("Driver ID"));
                   t10.setText("DRIVER number  :"+doc.getData().get("Driver number"));
                   t11.setText("Vehicle Number :"+doc.getData().get("Vehicle Number"));


               }else
               {
                   t9.setText("Driver is Busy.");
               }

           }
           else
           {
               t9.setText("");

           }

            }
        });

        final ProSwipeButton proSwipeBtn = (ProSwipeButton) findViewById(R.id.awesome_btn);

        proSwipeBtn.setOnSwipeListener(new ProSwipeButton.OnSwipeListener() {
            @Override
            public void onSwipeConfirm() {
                // user has swiped the btn. Perform your async operation now
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // task success! show TICK icon in ProSwipeButton
                        proSwipeBtn.showResultIcon(true); // false if task failed
                        startActivity(new Intent(detail.this,feedback.class));

                    }
                }, 2000);
            }
        });
        proSwipeBtn.showResultIcon(true); //if task succeeds
        proSwipeBtn.showResultIcon(false); //if task fails

        fromleft2= AnimationUtils.loadAnimation(this,R.anim.fromleft2);
        iv6=(ImageView)findViewById(R.id.imageView6);
        iv6.setAnimation(fromleft2);


    }
}

