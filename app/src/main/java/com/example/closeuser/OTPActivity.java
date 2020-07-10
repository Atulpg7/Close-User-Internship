package com.example.closeuser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.poovam.pinedittextfield.LinePinField;
import com.poovam.pinedittextfield.PinField;

import org.jetbrains.annotations.NotNull;

import java.util.Timer;
import java.util.TimerTask;

public class OTPActivity extends AppCompatActivity {

    TextView resend_otp,enter_mob_again;
    String mob_no,android_id;
    View contextView;
    Toolbar toolbar;
    String entered_otp="";
    LinePinField otp_field;
    Button btn_proceed;

    //For resend Password
    Timer t;
    int seconds = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_t_p);

        getIntents();

        getReferences();

        setToolbar();

        setClickListners();

        startTimer();

    }


    //Taking all references of input fields etc
    private void getReferences() {
        resend_otp=findViewById(R.id.btn_resend);
        contextView=findViewById(R.id.otp_layout);
        toolbar=findViewById(R.id.toolbar);
        otp_field=findViewById(R.id.otp_field);
        enter_mob_again=findViewById(R.id.enter_mob_again);
        btn_proceed=findViewById(R.id.btn_proceed);
    }



    //Getting Mob No and Android Id from tLogin Activity
    private void getIntents() {
        Intent intent=getIntent();
        if (intent!=null) {
            mob_no = intent.getStringExtra("mob_no");
            android_id = intent.getStringExtra("android_id");
        }
    }
    
    
    

    //All Button Clicks
    private void setClickListners() {

        otp_field.setOnTextCompleteListener(enteredText -> {
            entered_otp=enteredText;
            return false;
        });


        resend_otp.setOnClickListener(view -> {
            Snackbar.make(contextView,"Resending OTP...",Snackbar.LENGTH_SHORT).show();
            RequestFunctions.sendOTP(mob_no,android_id,contextView,OTPActivity.this);
            resend_otp.setEnabled(false);
            startTimer();
        });

        enter_mob_again.setOnClickListener(view -> {
            startActivity(new Intent(OTPActivity.this,LoginActivity.class));
            finish();
        });
        
        btn_proceed.setOnClickListener(view -> {
            
            if (entered_otp.length()!=4){
                Snackbar.make(contextView,"Please Enter OTP",Snackbar.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Sucessfull...", Toast.LENGTH_SHORT).show();
            }
            
        });


    }

    
    
    
    //Timer for resend password
    public void startTimer() {
        
        seconds = 60;
        
        t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                runOnUiThread(() -> {
                    
                    if(seconds==0){
                        resend_otp.setText("Resend OTP");
                        resend_otp.setEnabled(true);
                        t.cancel();
                    }else {
                       // resend_otp.setText("Resend OTP in : " + seconds + " Secs");
                        resend_otp.setText("Resend OTP in : " +String.format("%02d",seconds)+ " Secs");
                        seconds--;
                    }
                });
            }
        }, 0, 1000);
    }

    
    

    //Setting Toolbar
    private void setToolbar() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(view -> {
            startActivity(new Intent(OTPActivity.this,LoginActivity.class));
            t.cancel();
            finish();
        });
    }
}