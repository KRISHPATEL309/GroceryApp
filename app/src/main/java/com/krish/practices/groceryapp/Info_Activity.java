package com.krish.practices.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Info_Activity extends AppCompatActivity {
    private ImageView image,back_icon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        String fruit_name2 = getIntent().getStringExtra("Fruit_Name");
        String price2 = getIntent().getStringExtra("Price");
        String quantity2 = getIntent().getStringExtra("Quantity");
        String image2 = getIntent().getStringExtra("Image");

        image = findViewById(R.id.image2);
        TextView fruitname = findViewById(R.id.fruitname2);
        TextView price = findViewById(R.id.price2);
        TextView quantity = findViewById(R.id.quantity2);
        TextView description = findViewById(R.id.description);
        back_icon = findViewById(R.id.back_icon);
        back_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Info_Activity.this,HomeFragment.class);
                startActivity(intent);
                finish();
            }
        });
//        image.setImageIcon(Icon.createWithContentUri(image2));
//        image.setImageURI(Uri.parse(image2));

        Picasso.get().load(image2).into(image);
        fruitname.setText(fruit_name2);
        price.setText(price2);
        quantity.setText(quantity2);
    }
}