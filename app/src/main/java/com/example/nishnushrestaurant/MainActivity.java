package com.example.nishnushrestaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nishnushrestaurant.helpClasses.Restaurant;
import com.example.nishnushrestaurant.helpClasses.RestaurantSingleton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {

    EditText passwordEditText, userNameEditText;
    Button logInBtn;


    DatabaseReference mDatabase;
    FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        passwordEditText = findViewById(R.id.password_edit_text_main_activity);
        userNameEditText = findViewById(R.id.user_name_edit_text_main_activity);
        logInBtn = findViewById(R.id.log_in_btn_main_activity);


        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        db = FirebaseFirestore.getInstance();


        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = userNameEditText.getText().toString();
                String password = passwordEditText.getText().toString();


                db.collection(getString(R.string.RESTAURANTS_PATH)).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {

                            for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {



                                Restaurant restaurant = documentSnapshot.toObject(Restaurant.class);
                                restaurant.setId(documentSnapshot.getId());
                                if (restaurant != null) {
                                    if (restaurant.getRestaurantUserName().equals(userName) && restaurant.getRestaurantUserPassword().equals(password)) {
                                        Intent intent = new Intent(MainActivity.this, RestaurantOrdersActivity.class);
                                        intent.putExtra("restaurant", restaurant);
                                        intent.putExtra("id", restaurant.getId());
                                        startActivity(intent);
                                        finish();
                                    }
                                }

                            }

                        }

                    }
                });


//                if (!userName.isEmpty() && !password.isEmpty()) {
//
//                    mAuth.signInWithEmailAndPassword(RestaurantSingleton.userFormatted(userName, getApplicationContext()), password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//
//                            if (task.isSuccessful()) {
//                                //update UI
//                                Intent intent = new Intent(MainActivity.this, RestaurantOrdersActivity.class);
//                                intent.putExtra("id", task.getResult().getUser().getUid());
//                                startActivity(intent);
//
//                            }
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                }
            }
        });


    }
}