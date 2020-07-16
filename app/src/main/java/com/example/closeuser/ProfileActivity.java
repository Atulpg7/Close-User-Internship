package com.example.closeuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    ImageView back_profile;
    TextView my_orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getReferences();

        setBtnClicks();
    }

    private void setBtnClicks() {

        back_profile.setOnClickListener(view -> {
            finish();
        });

        my_orders.setOnClickListener(view -> {
            startActivity(new Intent(ProfileActivity.this, MyOrdersActivity.class));
        });
    }

    private void getReferences() {

        back_profile= findViewById(R.id.back_profile);
        my_orders = findViewById(R.id.my_orders);

    }

}