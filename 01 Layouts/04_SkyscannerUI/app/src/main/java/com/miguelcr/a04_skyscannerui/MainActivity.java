package com.miguelcr.a04_skyscannerui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    ImageView ivPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Get a reference to the image_view_car
        ivPhoto = findViewById(R.id.image_view_car);

        // 2. Load the car image into the ivPhoto
        Glide.with(this)
                .load("https://cdn1.carbuyer.co.uk/sites/carbuyer_d7/files/styles/article_main_image/public/car_images/q5.jpg")
                .into(ivPhoto);


    }
}
