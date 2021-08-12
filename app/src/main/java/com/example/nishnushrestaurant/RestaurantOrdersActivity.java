package com.example.nishnushrestaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.nishnushrestaurant.adapters.NewOrdersAdapter;
import com.example.nishnushrestaurant.helpClasses.Order;
import com.example.nishnushrestaurant.helpClasses.Restaurant;
import com.example.nishnushrestaurant.helpClasses.enums.OrderStatus;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RestaurantOrdersActivity extends AppCompatActivity {

    RecyclerView newOrdersRecyclerView, oldOrdersRecyclerView;
    RecyclerView.Adapter newOrderAdapter, oldOrderAdapter;
    ImageView logoRestaurantImageView;
    ImageButton settingImageBtn;

    DatabaseReference mDatabase;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();


    Restaurant restaurant;
    List<Order> orderList = new ArrayList<>();
    List<Order> finishedOrderList = new ArrayList<>();
    List<Order> newOrderList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_orders);

        String key = getIntent().getStringExtra("id");
        restaurant = (Restaurant) getIntent().getSerializableExtra("restaurant");


        logoRestaurantImageView = findViewById(R.id.restaurant_logo_image_view_restaurant_orders_activity);
        settingImageBtn = findViewById(R.id.setting_image_btn_restaurant_orders_activity);

        logoRestaurantImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RestaurantOrdersActivity.this, RestaurantProfileActivity.class);
                intent.putExtra("restaurant",restaurant);
                startActivity(intent);
            }
        });

        newOrdersRecyclerView = findViewById(R.id.new_orders_recycler_view_restaurant_orders_activity);
        oldOrdersRecyclerView = findViewById(R.id.old_orders_recycler_view_restaurant_orders_activity);

        initializeNewOrdersRecyclerView();
        initializeOldOrdersRecyclerView();

        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

        mDatabase = firebaseDatabase.getReference("Orders");
//        mDatabase = FirebaseDatabase.getInstance()

        if (restaurant.getLogoUri() != null)
            Picasso.get().load(restaurant.getLogoUri()).fit().centerCrop().into(logoRestaurantImageView);


        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Order order = dataSnapshot.getValue(Order.class);

                    if (order != null) {

//                        if (order.getRestaurantKey().equals(R))

                        Log.i("DOCUMENT", order.toString());

                        if (order.getOrderStatus() == OrderStatus.IN_PROGRESS) {
                            finishedOrderList.add(order);
                        }else newOrderList.add(order);
                    }

                }

                newOrderAdapter.notifyDataSetChanged();
                oldOrderAdapter.notifyDataSetChanged();;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(RestaurantOrdersActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }




    private void initializeNewOrdersRecyclerView() {

        newOrdersRecyclerView.setHasFixedSize(false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        newOrdersRecyclerView.setLayoutManager(layoutManager);
        newOrderAdapter = new NewOrdersAdapter(this, newOrderList);
        newOrdersRecyclerView.setAdapter(newOrderAdapter);

    }



    private void initializeOldOrdersRecyclerView() {

        oldOrdersRecyclerView.setHasFixedSize(false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        oldOrdersRecyclerView.setLayoutManager(layoutManager);
        oldOrderAdapter = new NewOrdersAdapter(this, finishedOrderList);
        oldOrdersRecyclerView.setAdapter(oldOrderAdapter);

    }




}