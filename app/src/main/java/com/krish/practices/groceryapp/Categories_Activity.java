package com.krish.practices.groceryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Categories_Activity extends AppCompatActivity {

    CardView fruit,vegetable;
    ImageView back_icon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        back_icon = findViewById(R.id.back_icon);
        fruit=findViewById(R.id.fruits);
        vegetable=findViewById(R.id.vegetables);
        back_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Categories_Activity.this,HomeFragment.class);
                startActivity(intent);

                fruit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Categories_Activity.this,Fruit_Activity.class);
                        startActivity(intent);
                    }
                });
                vegetable.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Categories_Activity.this,Vegetable_Activity.class);
                        startActivity(intent);
                    }
                });
            }
        });

    }
}