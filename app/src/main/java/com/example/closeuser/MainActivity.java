package com.example.closeuser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.closeuser.Adapters.ViewPagerAdapter;
import com.example.closeuser.Fragments.StartScreen_Page_1;
import com.example.closeuser.Fragments.StartScreen_Page_2;
import com.example.closeuser.Fragments.StartScreen_Page_3;
import com.example.closeuser.Fragments.StartScreen_Page_4;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    ViewPagerAdapter adapter;
    WormDotsIndicator dotsIndicator;
    TextView txt_enter_mob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getReferences();
        setViewPager();

        txt_enter_mob.setOnClickListener(view -> {startActivity(new Intent(MainActivity.this,LoginActivity.class)); finish();});


    }


    //function for getting all references
    private void getReferences() {
        viewPager = findViewById(R.id.viewPager);
        dotsIndicator = findViewById(R.id.worm_dots_indicator);
        txt_enter_mob = findViewById(R.id.txt_enter_mob);
    }


    //Function for setting the ViewPager (slideshow).
    private void setViewPager() {

        List<Fragment> fragments = new ArrayList<>();

        fragments.add(new StartScreen_Page_1());
        fragments.add(new StartScreen_Page_2());
        fragments.add(new StartScreen_Page_3());
        fragments.add(new StartScreen_Page_4());

        adapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        dotsIndicator.setViewPager(viewPager);
    }
}