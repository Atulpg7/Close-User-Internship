package com.example.closeuser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class MyOrdersActivity extends AppCompatActivity {

    ImageView back_my_orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);

        getReferences();
        setBtnClicks();
    }

    private void setBtnClicks() {

        back_my_orders.setOnClickListener(view -> {
            finish();
        });
    }

    private void getReferences() {

        back_my_orders = findViewById(R.id.back_my_orders);

    }


}