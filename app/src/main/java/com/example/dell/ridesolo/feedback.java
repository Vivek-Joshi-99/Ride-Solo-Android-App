package com.example.dell.ridesolo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hsalf.smilerating.BaseRating;
import com.hsalf.smilerating.SmileRating;

import java.util.HashMap;
import java.util.Map;

public class feedback extends AppCompatActivity {
    Button b4;
    private FirebaseFirestore db;
    FirebaseAuth mAuth;
public int r;
String r1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        SmileRating smileRating = (SmileRating)findViewById(R.id.smile_rating);
        smileRating.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(int smiley, boolean reselected) {
                Map<String,Object> h2 = new HashMap<>();

                switch (smiley) {
                    case SmileRating.BAD:
                        Toast.makeText(feedback.this,"BAD",Toast.LENGTH_SHORT).show();

                        h2.put("Trip feedback","BAD");
                        db.collection("Drivers Ratings").document(mAuth.getCurrentUser().getEmail()).set(h2).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(feedback.this, "Rating not Registered!!!!", Toast.LENGTH_SHORT).show();
                            }
                        });

                        break;
                    case SmileRating.GOOD:
                        Toast.makeText(feedback.this,"GOOD",Toast.LENGTH_SHORT).show();
                        h2.put("Trip feedback","GOOD");
                        db.collection("Drivers Ratings").document(mAuth.getCurrentUser().getEmail()).set(h2).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(feedback.this, "Rating not Registered!!!!", Toast.LENGTH_SHORT).show();
                            }
                        });

                        break;
                    case SmileRating.GREAT:
                        Toast.makeText(feedback.this,"GREAT",Toast.LENGTH_SHORT).show();
                        h2.put("Trip feedback","GREAT");
                        db.collection("Drivers Ratings").document(mAuth.getCurrentUser().getEmail()).set(h2).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(feedback.this, "Rating not Registered!!!!", Toast.LENGTH_SHORT).show();
                            }
                        });

                        break;
                    case SmileRating.OKAY:
                        Toast.makeText(feedback.this,"OKAY",Toast.LENGTH_SHORT).show();
                        h2.put("Trip feedback","OK");
                        db.collection("Drivers Ratings").document(mAuth.getCurrentUser().getEmail()).set(h2).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(feedback.this, "Rating not Registered!!!!", Toast.LENGTH_SHORT).show();
                            }
                        });

                        break;
                    case SmileRating.TERRIBLE:
                        Toast.makeText(feedback.this,"TERRIBLE",Toast.LENGTH_SHORT).show();
                        h2.put("Trip feedback","TERRIBLE");
                        db.collection("Drivers Ratings").document(mAuth.getCurrentUser().getEmail()).set(h2).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(feedback.this, "Rating not Registered!!!!", Toast.LENGTH_SHORT).show();
                            }
                        });

                        break;
                }
            }
        });
        smileRating.setOnRatingSelectedListener(new SmileRating.OnRatingSelectedListener() {
            @Override
            public void onRatingSelected(int level, boolean reselected) {

                Toast.makeText(feedback.this,"Selected rating " +level,Toast.LENGTH_SHORT).show();
            }
        });

        //sending to fire base



        b4=(Button)findViewById(R.id.button4);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(feedback.this,login1.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(feedback.this,login1.class));
    }
}
