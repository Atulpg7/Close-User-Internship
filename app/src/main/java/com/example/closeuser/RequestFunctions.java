package com.example.closeuser;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;



public class RequestFunctions {


    //Function for Sending OTP on Mobile Number
    static void sendOTP(String mob_no, String android_id, View view, Context context) {

        final String URL = ServerData.baseUrl + "/login-otp";

        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest request = new StringRequest(Request.Method.POST, URL, response -> {

            Log.e("Response == > ", "" + response);

            try {

                JSONObject responeObject = new JSONObject(response);

                if (responeObject.getString("message").equals("otp-sent")) {

                    Snackbar.make(view, "OTP Sent Successfully", Snackbar.LENGTH_LONG).show();

                    //Starting Activity After some delay....
                    new Timer().schedule(
                            new TimerTask() {
                                @Override
                                public void run() {

                                    //If Otp Requested from Login Class
                                    if (context.getClass().getSimpleName().equals("LoginActivity")) {
                                        Intent intent = new Intent(context, OTPActivity.class);
                                        intent.putExtra("mob_no", mob_no);
                                        intent.putExtra("android_id", android_id);
                                        context.startActivity(intent);
                                        ((Activity)context).finish();
                                    }
                                }
                            }, 1000);

                } else {
                    Toast.makeText(context, "Something went wrong !", Toast.LENGTH_SHORT).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            Log.e("Error == > ", "" + error);
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("phone", "91" + mob_no);
                params.put("androidId", android_id);
                return params;
            }
        };
        queue.add(request);
    }//OTP Send Method End


}
