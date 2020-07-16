package com.example.closeuser.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.closeuser.Adapters.AddressAdapter;
import com.example.closeuser.Adapters.CardAdapter;
import com.example.closeuser.Models.AddressModel;
import com.example.closeuser.Models.CardDetailsModel;
import com.example.closeuser.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView address_rv,cards_rv;
    View view;

    AddressAdapter addressAdapter;
    List<AddressModel> addressModelList;

    CardAdapter cardAdapter;
    List<CardDetailsModel> cardDetailsModelList;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);

        getReferences();
        setAddressData();

        setCardData();

        return view;
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

        cardAdapter = new CardAdapter(getContext(),cardDetailsModelList);

        cards_rv.setHasFixedSize(true);
        cards_rv.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
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


        addressAdapter = new AddressAdapter(getContext(),addressModelList);

        address_rv.setHasFixedSize(true);
        address_rv.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        address_rv.setAdapter(addressAdapter);
        addressAdapter.notifyDataSetChanged();

    }

    private void getReferences() {

        address_rv = view.findViewById(R.id.address_rv);
        cards_rv = view.findViewById(R.id.cards_rv);

        cardDetailsModelList = new ArrayList<>();
        addressModelList = new ArrayList<>();
    }
}