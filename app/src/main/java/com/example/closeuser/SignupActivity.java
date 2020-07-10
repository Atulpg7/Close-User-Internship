package com.example.closeuser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;


public class SignupActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        getReferences();

        setToolbar();
    }




    //Getting  References
    private void getReferences() {
        toolbar = findViewById(R.id.toolbar);
    }


    //Setting Toolbar
    private void setToolbar() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(view -> {startActivity(new Intent(SignupActivity.this,StartUpActivity.class)); finish();});
    }
}