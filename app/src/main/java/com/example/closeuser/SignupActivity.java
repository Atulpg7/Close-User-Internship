package com.example.closeuser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import com.ebanx.swipebtn.OnActiveListener;
import com.ebanx.swipebtn.SwipeButton;
import com.example.closeuser.GeneralClasses.UserData;
import com.google.android.material.snackbar.Snackbar;


public class SignupActivity extends AppCompatActivity {

    Toolbar toolbar;

    EditText et_fname,et_lname,et_email,et_address,
            et_city,et_pincode,et_card_number,et_cvv,
            et_exmonth,et_exyear,et_dname,et_dno,et_referal;

    SwipeButton btn_signup;
    View contextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        getReferences();

        setToolbar();


        btn_signup.setOnActiveListener(new OnActiveListener() {
            @Override
            public void onActive() {

                btn_signup.toggleState();

                setUserData();

                /*if(checkUserData()){
                    Toast.makeText(SignupActivity.this, "Valid Data", Toast.LENGTH_SHORT).show();
                }*/

                Intent intent=new Intent(SignupActivity.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }


    //Function for checking entered data is correct or not
    private boolean checkUserData(){

        boolean flag=true;

        String fname=et_fname.getText().toString();
        String lname=et_lname.getText().toString();
        String email=et_email.getText().toString();
        String address=et_address.getText().toString();
        String city=et_city.getText().toString();
        String pincode=et_pincode.getText().toString();
        String card_no=et_card_number.getText().toString();
        String cvv=et_cvv.getText().toString();
        String ex_month=et_exmonth.getText().toString();
        String ex_yr=et_exyear.getText().toString();
        String d_name=et_dname.getText().toString();
        String d_mob=et_dno.getText().toString();



        if (checkLen(fname) && checkLen(lname) && checkLen(email) && checkLen(address) && checkLen(city)
                && checkLen(pincode)&& checkLen(card_no)&& checkLen(cvv)&& checkLen(ex_month)&& checkLen(ex_yr)
                && checkLen(d_name)&& checkLen(d_mob)){

            Snackbar.make(contextView,"Please fill all details",Snackbar.LENGTH_SHORT).show();
            flag = false;
        }

        else if(checkLen(fname)){
            Snackbar.make(contextView,"Enter First Name !",Snackbar.LENGTH_SHORT).show();
            flag=false;
        }

        else if(checkLen(lname)){
            Snackbar.make(contextView,"Enter Last Name !",Snackbar.LENGTH_SHORT).show();
            flag=false;
        }

        else if(checkLen(email)){
            Snackbar.make(contextView,"Enter Email Id !",Snackbar.LENGTH_SHORT).show();
            flag=false;
        }
        else if (!isValidEmail(email)){
            Snackbar.make(contextView,"Enter Correct Email Id !",Snackbar.LENGTH_SHORT).show();
            flag=false;
        }

        else if(checkLen(address)){
            Snackbar.make(contextView,"Enter Address  !",Snackbar.LENGTH_SHORT).show();
            flag=false;
        }

        else if(checkLen(city)){
            Snackbar.make(contextView,"Enter City !",Snackbar.LENGTH_SHORT).show();
            flag=false;
        }

        else if(checkLen(pincode)){
            Snackbar.make(contextView,"Enter Pincode !",Snackbar.LENGTH_SHORT).show();
            flag=false;
        }

        else if(pincode.length()!=6){
            Snackbar.make(contextView,"Enter Correct Pincode !",Snackbar.LENGTH_SHORT).show();
            flag=false;
        }

        else if(checkLen(card_no)){
            Snackbar.make(contextView,"Enter Card Number !",Snackbar.LENGTH_SHORT).show();
            flag=false;
        }

        else if(card_no.length()<16){
            Snackbar.make(contextView,"Enter Correct Card Number !",Snackbar.LENGTH_SHORT).show();
            flag=false;
        }

        else if(checkLen(cvv)){
            Snackbar.make(contextView,"Enter CVV !",Snackbar.LENGTH_SHORT).show();
            flag=false;
        }

        else if(cvv.length()<3){
            Snackbar.make(contextView,"Enter Correct CVV !",Snackbar.LENGTH_SHORT).show();
            flag=false;
        }

        else if(checkLen(ex_month)){
            Snackbar.make(contextView,"Enter Expiry Month !",Snackbar.LENGTH_SHORT).show();
            flag=false;
        }

        else if(ex_month.length()<2 || Integer.parseInt(ex_month)>12 || Integer.parseInt(ex_month)<=0){
            Snackbar.make(contextView,"Enter Correct Expiry Month !",Snackbar.LENGTH_SHORT).show();
            flag=false;
        }


        else if(checkLen(ex_yr)){
            Snackbar.make(contextView,"Enter Expiry Year !",Snackbar.LENGTH_SHORT).show();
            flag=false;
        }

        else if(ex_yr.length()<4 || Integer.parseInt(ex_month)==0){
            Snackbar.make(contextView,"Enter Correct Expiry Year !",Snackbar.LENGTH_SHORT).show();
            flag=false;
        }

        else if(checkLen(d_name)){
            Snackbar.make(contextView,"Enter Delivery Name!",Snackbar.LENGTH_SHORT).show();
            flag=false;
        }

        else if(checkLen(d_mob)){
            Snackbar.make(contextView,"Enter Delivery Number !",Snackbar.LENGTH_SHORT).show();
            flag=false;
        }

        else if(d_mob.length()<10){
            Snackbar.make(contextView,"Enter Correct Delivery number !",Snackbar.LENGTH_SHORT).show();
            flag=false;
        }
        else{
            setUserData();
        }
        

        return  flag;
    }




    //Functions for checking email address
    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }



    //Functions for checking Null Entries
    public boolean checkLen(String string){
        return string.length()==0;
    }



    //Setting User Data
    private void setUserData() {

        UserData.setFirst_name(et_fname.getText().toString());
        UserData.setLast_name(et_lname.getText().toString());
        UserData.setEmail_id(et_email.getText().toString());
        UserData.setAddress(et_address.getText().toString());
        UserData.setCity(et_city.getText().toString());
        UserData.setPincode(et_pincode.getText().toString());
        UserData.setCard_no(et_card_number.getText().toString());
        UserData.setCvv(et_cvv.getText().toString());
        UserData.setExp_month(et_exmonth.getText().toString());
        UserData.setExp_year(et_exyear.getText().toString());
        UserData.setDelivery_name(et_dname.getText().toString());
        UserData.setDelivery_mobileNo(et_dno.getText().toString());
        UserData.setRefer_code(et_referal.getText().toString());
    }




    //Getting  References
    private void getReferences() {

        toolbar = findViewById(R.id.toolbar);
        btn_signup=findViewById(R.id.btn_swipe);
        btn_signup.setText("      Swipe to SignUp");
        contextView=findViewById(R.id.signup_view);

        //User data
        et_fname=findViewById(R.id.et_fname);
        et_lname=findViewById(R.id.et_lname);
        et_email=findViewById(R.id.et_email);
        et_address=findViewById(R.id.et_address);
        et_city=findViewById(R.id.et_city);
        et_pincode=findViewById(R.id.et_pincode);
        et_card_number=findViewById(R.id.et_cardno);
        et_cvv=findViewById(R.id.et_cvv);
        et_exmonth=findViewById(R.id.et_exmonth);
        et_exyear=findViewById(R.id.et_exyear);
        et_dname=findViewById(R.id.et_dname);
        et_dno=findViewById(R.id.et_dno);
        et_referal=findViewById(R.id.et_ref);
    }



    //Setting Toolbar
    private void setToolbar() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(view -> {startActivity(new Intent(SignupActivity.this,StartUpActivity.class)); finish();});
    }
}