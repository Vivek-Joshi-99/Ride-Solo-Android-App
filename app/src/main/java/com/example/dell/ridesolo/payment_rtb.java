package com.example.dell.ridesolo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class payment_rtb extends AppCompatActivity {
Button b8,pb2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_rtb);
        b8=(Button)findViewById(R.id.button8);

        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(payment_rtb.this,login1.class));
                Toast.makeText(payment_rtb.this, "Owner will contact you soon!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
