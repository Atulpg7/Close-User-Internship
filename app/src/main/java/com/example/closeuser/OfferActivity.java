package com.example.closeuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class OfferActivity extends AppCompatActivity {

    ImageView top_bars;
    TextView top_heading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer);

        getReferences();
        setIntents();

        setBtnClicks();

    }


    private void setBtnClicks() {

        top_bars.setOnClickListener(view->{
            startActivity(new Intent(OfferActivity.this,ProfileActivity.class));
        });
    }

    private void getReferences() {

        top_bars = findViewById(R.id.top_bars);
        top_heading = findViewById(R.id.top_heading);
    }

    private void setIntents() {

        Intent intent = getIntent();
        String called_offer = intent.getStringExtra("calling_offer");
        top_heading.setText("No "+called_offer+" Rewards");

    }
}