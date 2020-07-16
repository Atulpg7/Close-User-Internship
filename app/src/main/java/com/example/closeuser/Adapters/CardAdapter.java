package com.example.closeuser.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.closeuser.Models.CardDetailsModel;
import com.example.closeuser.R;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    Context context;
    List<CardDetailsModel> list;

    public CardAdapter(Context context, List<CardDetailsModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_layout,parent,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        CardDetailsModel model = list.get(position);

        holder.bank_name.setText(model.getBankName());

        holder.last_digits.setText(" "+model.getCardNumber().substring(12));

        holder.expiry_date.setText(model.getEx_month()+"/"+model.getEx_year().substring(2));

        holder.holder_name.setText(model.getHolderName());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class  ViewHolder extends RecyclerView.ViewHolder{

        TextView bank_name,last_digits,expiry_date,holder_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            bank_name=itemView.findViewById(R.id.bank_name);
            last_digits=itemView.findViewById(R.id.last_digits);
            expiry_date=itemView.findViewById(R.id.expiry_date);
            holder_name=itemView.findViewById(R.id.holder_name);
        }
    }
}
