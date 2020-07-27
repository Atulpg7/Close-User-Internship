package com.example.closeuser.Fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.closeuser.Adapters.AddressAdapter;
import com.example.closeuser.Adapters.CardAdapter;
import com.example.closeuser.Models.AddressModel;
import com.example.closeuser.Models.CardDetailsModel;
import com.example.closeuser.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView address_rv, cards_rv;
    View view;

    AddressAdapter addressAdapter;
    List<AddressModel> addressModelList;

    CardAdapter cardAdapter;
    List<CardDetailsModel> cardDetailsModelList;


    //Add Address
    ImageView btn_add_address;
    View address_popup;


    //Add Cards
    ImageView btn_add_card;
    View card_popup;


    //Add Location
    RelativeLayout add_your_location;
    double latitude;
    double longitude;

    Context context;
    Activity activity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);

        getReferences();
        //setAddressData();

        setCardData();

        btnClicks();

        return view;
    }

    private void btnClicks() {

        add_your_location.setOnClickListener(view -> {

            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {


                LocationManager lm = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
                List<String> providers = lm.getProviders(true);

                Location location = null;

                for (int i = providers.size() - 1; i >= 0; i--) {
                    location = lm.getLastKnownLocation(providers.get(i));
                    if (location != null) break;
                }

                if (location != null) {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();

                    Geocoder geocoder=new Geocoder(context);
                    List<Address> area = null;

                    try {
                        area = geocoder.getFromLocation(latitude, longitude, 1);
                        Log.e("Data: ", "" + area.get(0).getAddressLine(0));


                        BottomSheetDialog dialog = new BottomSheetDialog(context);
                        dialog.setContentView(R.layout.add_address_layout_popup);
                        dialog.show();

                    } catch (IOException e) {
                        Snackbar.make(view,"Try Again !",Snackbar.LENGTH_SHORT).show();
                    }
                }

            }
        });

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(activity,R.style.BottomSheetDialogTheme);
        bottomSheetDialog.setCanceledOnTouchOutside(false);
        bottomSheetDialog.setCancelable(false);

        btn_add_address.setOnClickListener(view->{
            bottomSheetDialog.setContentView(address_popup);
            bottomSheetDialog.show();

            address_popup.findViewById(R.id.close_btn_address).setOnClickListener(view1 -> {
                bottomSheetDialog.dismiss();
            });


            address_popup.findViewById(R.id.btn_save_address_popup).setOnClickListener(view1 -> {
                Toast.makeText(context, "Submitted", Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();
            });


        });

        btn_add_card.setOnClickListener(view -> {
            bottomSheetDialog.setContentView(card_popup);
            bottomSheetDialog.show();

            card_popup.findViewById(R.id.close_btn_card).setOnClickListener(view1 -> {
                bottomSheetDialog.dismiss();
            });


            card_popup.findViewById(R.id.btn_add_card_popup).setOnClickListener(view1 -> {
                Toast.makeText(context, "Submitted", Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();
            });

        });
    }


    private void setCardData() {

        CardDetailsModel model1 = new CardDetailsModel();

        model1.setBankName("HDFC");
        model1.setCardNumber("1234567890123456");
        model1.setEx_month("05");
        model1.setEx_year("2020");
        model1.setHolderName("Amit Rana");

        CardDetailsModel model2 = new CardDetailsModel();

        model2.setBankName("State Bank of India");
        model2.setCardNumber("5196190221807035");
        model2.setEx_month("05");
        model2.setEx_year("2035");
        model2.setHolderName("Atul Kumar");


        cardDetailsModelList.add(model1);
        cardDetailsModelList.add(model2);

        cardAdapter = new CardAdapter(getContext(), cardDetailsModelList);

        cards_rv.setHasFixedSize(true);
        cards_rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        cards_rv.setAdapter(cardAdapter);

        cardAdapter.notifyDataSetChanged();


    }

    private void setAddressData() {

        AddressModel address1 = new AddressModel();

        address1.setName("Atul Kumar");
        address1.setAddress("Baldevpuram, Aurangabad, Mathura");
        address1.setMob_no("8193800247");


        AddressModel address2 = new AddressModel();

        address2.setName("Aman");
        address2.setAddress("Aligarh");
        address2.setMob_no("1234567890");

        addressModelList.add(address1);
        addressModelList.add(address2);


        addressAdapter = new AddressAdapter(getContext(), addressModelList);

        address_rv.setHasFixedSize(true);
        address_rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        address_rv.setAdapter(addressAdapter);
        addressAdapter.notifyDataSetChanged();

    }

    private void getReferences() {

        address_rv = view.findViewById(R.id.address_rv);
        cards_rv = view.findViewById(R.id.cards_rv);
        add_your_location = view.findViewById(R.id.add_your_location);
        btn_add_address = view.findViewById(R.id.btn_add_address);
        btn_add_card = view.findViewById(R.id.btn_add_card);
        context = getContext();
        activity = getActivity();
        cardDetailsModelList = new ArrayList<>();
        addressModelList = new ArrayList<>();


        //Bottom Sheet Layouts
        address_popup = LayoutInflater.from(context).inflate(R.layout.add_address_layout_popup,null);
        card_popup = LayoutInflater.from(context).inflate(R.layout.add_card_layout_popup,null);
    }
}