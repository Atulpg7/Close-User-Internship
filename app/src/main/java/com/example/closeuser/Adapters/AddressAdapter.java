package com.example.closeuser.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.closeuser.Models.AddressModel;
import com.example.closeuser.R;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.viewHolder> {

    Context context;
    List<AddressModel> addressList;

    public AddressAdapter(Context context, List<AddressModel> addressList) {
        this.context = context;
        this.addressList = addressList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.address_layout,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        AddressModel model = addressList.get(position);

        holder.name.setText(model.getName());
        holder.address.setText(model.getAddress());
        holder.mob_no.setText("Mobile: +"+model.getMob_no());


    }

    @Override
    public int getItemCount() {
        return addressList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        TextView name,address,mob_no;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.address);
            mob_no = itemView.findViewById(R.id.mobile_no);
        }
    }
}
