package com.example.dell.ridesolo;

import android.content.Intent;
import android.icu.util.ULocale;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


import com.squareup.picasso.Picasso;

public class rent_bike extends AppCompatActivity {


        FirebaseDatabase database;
        DatabaseReference category;
        RecyclerView recyclerView;
        RecyclerView.LayoutManager layoutManager;
        FirebaseRecyclerAdapter adapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_rent_bike);

            database = FirebaseDatabase.getInstance();
            category = database.getReference("Category");

            recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            recyclerView.setHasFixedSize(true);

            layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);

            loadMenu();

        }

        private void loadMenu() {

            Query query = FirebaseDatabase
                    .getInstance()
                    .getReference()
                    .child("Category");

            FirebaseRecyclerOptions<Category> options =
                    new FirebaseRecyclerOptions.Builder<Category>()
                            .setQuery(query, Category.class)
                            .build();

            adapter = new FirebaseRecyclerAdapter<Category, MenuViewHolder>(options) {
                protected void onBindViewHolder(@NonNull MenuViewHolder holder, int position, @NonNull Category model) {
                    holder.vehicle.setText(model.getVehicle());
                    Picasso.get().load(model.getImage())
                            .into(holder.turfimage);
                    holder.numberplate.setText(model.getNumberPlate());
                    holder.price.setText(model.getPrice());
                    final Category clickItem = model;
                    holder.setItemClickListener(new ItemClickListener() {
                        @Override
                        public void onClick(View view, int position, boolean isLongClick) {
                            Toast.makeText(rent_bike.this, "You Have Selected :" + clickItem.getVehicle(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(rent_bike.this,payment_rtb.class));


                        }
                    });
                }

                @NonNull
                @Override
                public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                    View view = LayoutInflater.from(viewGroup.getContext())
                            .inflate(R.layout.bikemenu, viewGroup, false);

                    return new MenuViewHolder(view);
                }
            };

            recyclerView.setAdapter(adapter);
        }

        @Override
        protected void onStart() {
            super.onStart();
            adapter.startListening();
        }

        @Override
        protected void onStop() {
            super.onStop();
            adapter.stopListening();
        }

    }
