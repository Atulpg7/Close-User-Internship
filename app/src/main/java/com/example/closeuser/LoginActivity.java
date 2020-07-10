package com.example.closeuser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.EditText;

import com.ebanx.swipebtn.SwipeButton;
import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {


    SwipeButton btn_swipe;
    EditText edt_enter_mob;
    Toolbar toolbar;

    String android_id;
    View contextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        getReferences();


        setToolbar();

        btn_swipe.setOnActiveListener(() -> {

            String mob_no = edt_enter_mob.getText().toString();

            if (mob_no.length() != 10) {

                btn_swipe.setText("          Swipe again to Continue");
                Snackbar.make(contextView, "Enter valid number", Snackbar.LENGTH_LONG).show();
                btn_swipe.toggleState();

            } else {

                Snackbar.make(contextView, "Sending OTP please wait...", Snackbar.LENGTH_LONG).show();
                btn_swipe.toggleState();
                RequestFunctions.sendOTP(mob_no,android_id,contextView,LoginActivity.this);
//                startActivity(new Intent(LoginActivity.this,OTPActivity.class));
//                finish();
            }
        });

    }


    private void getReferences() {
        toolbar = findViewById(R.id.toolbar);
        btn_swipe = findViewById(R.id.btn_swipe);
        edt_enter_mob = findViewById(R.id.edt_enter_mob);
        contextView = findViewById(R.id.layout_login);
        android_id = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
    }


    private void setToolbar() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(view -> finish());
    }
}