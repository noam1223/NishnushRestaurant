package com.example.nishnushrestaurant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nishnushrestaurant.helpClasses.Restaurant;
import com.squareup.picasso.Picasso;

public class RestaurantProfileActivity extends AppCompatActivity {

    ImageView logoImgView;
    TextView nameTextView, detailsTextView;
    AppCompatButton backBtn;

    Restaurant restaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_profile);

        restaurant = (Restaurant) getIntent().getSerializableExtra("restaurant");


        logoImgView = findViewById(R.id.logo_image_view_restaurant_profile_activity);
        nameTextView = findViewById(R.id.restaurant_name_text_view_restaurant_profile_activity);
        detailsTextView = findViewById(R.id.details_text_view_restaurant_profile_activity);
        backBtn = findViewById(R.id.back_btn_restaurant_profile_activity);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        nameTextView.setText(restaurant.getName());

        StringBuilder stringBuilder = new StringBuilder();

        if (restaurant.getRestaurantUserName() != null)
            stringBuilder.append("שם משתמש: ").append(restaurant.getRestaurantUserName()).append("\n");

        if (restaurant.getPhoneNumber() != null)
            stringBuilder.append("מספר טלפון: ").append(restaurant.getPhoneNumber()).append("\n");

        if (restaurant.getDateOfAdd() != null)
            stringBuilder.append("תאריך הרשמה: ").append(restaurant.getDateOfAdd()).append("\n");

        if (restaurant.getMyAddress().fullMyAddress() != null)
            stringBuilder.append("כתובת: ").append(restaurant.getMyAddress().fullMyAddress()).append("\n");


        detailsTextView.setText(stringBuilder.toString());


        if (restaurant.getLogoUri() != null)
            Picasso.get().load(restaurant.getLogoUri()).fit().centerCrop().into(logoImgView);


    }
}