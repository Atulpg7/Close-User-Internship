package com.example.closeuser.GeneralClasses;

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
import com.example.closeuser.GeneralClasses.GlobalData;
import com.example.closeuser.GeneralClasses.UserData;
import com.example.closeuser.OTPActivity;
import com.example.closeuser.SignupActivity;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


public class RequestFunctions {


    //SSL Request
    public static void getSSL(Context context) {
        try {
            TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() {
                            X509Certificate[] myTrustedAnchors = new X509Certificate[0];
                            return myTrustedAnchors;
                        }

                        @Override
                        public void checkClientTrusted(X509Certificate[] certs, String authType) {}

                        @Override
                        public void checkServerTrusted(X509Certificate[] certs, String authType) {}
                    }
            };

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier((arg0, arg1) -> true);
        } catch (Exception e) {
            Toast.makeText(context, "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }//SSL Request Method End




    //Function for Sending OTP on Mobile Number
    public static void sendOTP(View view, Context context) {

        final String URL = GlobalData.baseUrl + "/login-otp";

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
                                        context.startActivity(intent);
                                        ((Activity)context).finish();
                                    }
                                }
                            }, 1000);

                } else {
                    Snackbar.make(view, "Something went wrong!", Snackbar.LENGTH_LONG).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            Snackbar.make(view,"Something went wrong ! Try again..",Snackbar.LENGTH_LONG).show();
            Log.e("Error == > ", "" + error);
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("phone", "91" + UserData.getMobileNo());
                params.put("androidId", UserData.getAndroidId());
                return params;
            }
        };
        queue.add(request);
    }//OTP Send Method End




    //Function for Verify OTP
    public static void verifyOTP(String otp, View view, Context context) {

        final String URL = GlobalData.baseUrl + "/login";

        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest request = new StringRequest(Request.Method.POST, URL, response -> {

            Log.e("Response == > ", "" + response);

            try {

                JSONObject responeObject = new JSONObject(response);

                if (!responeObject.getBoolean("done")){
                    Snackbar.make(view,"Entered OTP Incorrect",Snackbar.LENGTH_LONG).show();
                }else {
                    Intent intent=new Intent(context, SignupActivity.class);
                    context.startActivity(intent);
                    ((Activity)context).finish();
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            Snackbar.make(view,"Something went wrong ! Try again..",Snackbar.LENGTH_LONG).show();
            Log.e("Error == > ", "" + error);
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("phone", "91" + UserData.getMobileNo());
                params.put("androidId", UserData.getMobileNo());
                params.put("otp", otp);
                return params;
            }
        };
        queue.add(request);
    }//OTP Verify Method End





}
