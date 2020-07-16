package com.example.closeuser.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.closeuser.OfferActivity;
import com.example.closeuser.R;

public class RewardsFragment extends Fragment {

    ImageView all_rewards, claimed_rewards, active_offers;
    Intent intent;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_rewards, container, false);

        getReferences();

        setBtnClicks();

        return view;
    }

    private void setBtnClicks() {

        all_rewards.setOnClickListener(view -> {
            intent.putExtra("calling_offer","Available");
            startOfferActivity();
        });

        claimed_rewards.setOnClickListener(view -> {
            intent.putExtra("calling_offer","Claimed");
            startOfferActivity();
        });

        active_offers.setOnClickListener(view -> {
            intent.putExtra("calling_offer","Activated");
            startOfferActivity();
        });

    }

    private void startOfferActivity() {
        startActivity(intent);
    }

    private void getReferences() {

        intent = new Intent(getActivity(), OfferActivity.class);
        all_rewards = view.findViewById(R.id.all_rewards);
        claimed_rewards = view.findViewById(R.id.claimed_rewards);
        active_offers = view.findViewById(R.id.active_offers);
    }
}