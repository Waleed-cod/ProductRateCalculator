package com.codembeded.productratecalculator.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codembeded.productratecalculator.R;
import com.codembeded.productratecalculator.activities.UpdateBagActivity;
import com.codembeded.productratecalculator.models.BagModel;
import com.codembeded.productratecalculator.models.ProductModel;

import java.util.ArrayList;

public class BagAdapter extends RecyclerView.Adapter<BagAdapter.ViewHolder> {

    private Context context;
    private ArrayList<BagModel> bag_list;
    private LayoutInflater mInflater;

    // data is passed into the constructor
    public BagAdapter(Context context, ArrayList<BagModel> bagList) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.bag_list = bagList;
    }

    // inflates the row layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.custom_layout_for_product_list, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.textView.setText(subCategories.get(position).getName());
        holder.bind(bag_list.get(position));
    }
    @Override
    public int getItemCount() {
        return bag_list.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView bag_name_tv, bag_rate_tv;

        ViewHolder(View itemView) {
            super(itemView);
            bag_name_tv = itemView.findViewById(R.id.product_name_tv);
            bag_rate_tv = itemView.findViewById(R.id.product_weight_tv);
        }

        public void bind(final BagModel bag) {
            bag_name_tv.setText(bag.getBagName());
            bag_rate_tv.setText(String.valueOf(bag.getBagPrice()));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, UpdateBagActivity.class);
                    intent.putExtra("bag_id", String.valueOf(bag_list.get(getAdapterPosition()).getBagId()));
                    intent.putExtra("bag_name", String.valueOf(bag_list.get(getAdapterPosition()).getBagName()));
                    intent.putExtra("bag_price", String.valueOf(bag_list.get(getAdapterPosition()).getBagPrice()));
                    context.startActivity(intent);
                }
            });
        }

    }
}
